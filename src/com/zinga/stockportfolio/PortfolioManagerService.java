package com.zinga.stockportfolio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zinga.stockportfolio.service.IStockService;

public class PortfolioManagerService extends Service{
	
	private static final String TAG = "PortfolioManagerService";
	
	private  StocksDb db;
	private long timestamp = 0L;
	
	/* limit czasu do zapisywania w pami�ci podr�cznej tzn. po jakim czasie zawarto�� pami�ci jest uznawana za przestarza�� i us�uga pobiera dane z serwera */
	
	private static final int MAX_CACHE_AGE = 15*60*1000; //15 minut
	private static final int HIGH_PRICE_NOTIFICATION = 1;
	
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 * 
	 * Za pomoc� tej metody mo�na przes�a� do poraz pierwszy uruchomionej us�ugi dodatkowe parametry 
	 * 
	 * 
	 */
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 * 
	 * metoda umozliwia komunikowanie si� aktywno�ciom oraz innym us�ugom na kontakt z t� us�ug�
	 * 
	 */
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		if(db == null){
			db = new StocksDb(this);
		}
		
		System.out.println("Bindowanie serwisu...");
		return new IStockService.Stub() {
			
			@Override
			public List<Stock> getPortfolio() throws RemoteException {
				// TODO Auto-generated method stub
				ArrayList<Stock> stocks = db.getStocks();
				long currTime = System.currentTimeMillis();
				if(currTime - timestamp <= MAX_CACHE_AGE){
					return stocks;
				}
				Stock[] currStocks = new Stock[stocks.size()];
				stocks.toArray(currStocks);
				try{
					Log.d(TAG,"Refreshing cache");
					ArrayList<Stock> newStocks = fetchStockData(currStocks);
					Log.d(TAG,"Got new stock data, updating cache");
					updateCachedStocks(newStocks);
					return newStocks;
				} catch(Exception e){
					Log.e(TAG,"Wyst�pi� wyj�tek podczas pobierania danych",e);
					throw new RemoteException();
				}
				
				
				
				
			}
			
			/*
			 * (non-Javadoc)
			 * @see com.zinga.stockportfolio.service.IStockService#addToPortfolio(com.zinga.stockportfolio.Stock)
			 * 
			 * dodaj do portfela akcji
			 */
			
			@Override
			public Stock addToPortfolio(Stock stock) throws RemoteException {
				// TODO Auto-generated method stub
				Log.d(TAG,"Adding stock: " + stock);
				Stock s = db.addStock(stock);
				Log.d(TAG,"Stock added to db");
				try{
					updateStockData();
					for(Stock x: db.getStocks()){
						if(x.getSymbol().equalsIgnoreCase(stock.getSymbol())){
							s = x;
						}
					}
					Log.d(TAG,"Stock data updated");
				} catch(IOException e){
					
				}
				return s;
			}
		};
	}
	
	private void updateCachedStocks(ArrayList<Stock> stocks){
		Log.d(TAG,"Get new stocks to update cache with");
		timestamp = System.currentTimeMillis();
		Stock[] currStocks = new Stock[stocks.size()];
		currStocks = stocks.toArray(currStocks);
		for(Stock stock : currStocks){
			Log.d(TAG,"Updating cache with stock = " + stock.toString());
			db.updateStockPrice(stock);
		}
		Log.d(TAG, "Cache updated, checking for alerts");
		checkForAlerts(stocks);
	}
	
	
	private void checkForAlerts(Iterable<Stock> stocks){
		try{
			for(Stock stock: stocks){
				double current = stock.getCurrentPrice();
				if(current > stock.getMaxPrice()){
					createHighPriceNotification(stock);
					continue;
				}
				if(current < stock.getMinPrice()){
					createLowPriceNotification(stock);
				}
			}
		} finally {
			
		}
	}
	
	private void updateStockData() throws IOException {
		 ArrayList<Stock> stocks = db.getStocks();
		 Stock[] currStocks = new Stock[stocks.size()];
		 currStocks = stocks.toArray(currStocks);
		 stocks = fetchStockData(currStocks);
	}
	
	/*
	 * Fetching stocks data from remote server
	 */
	private ArrayList<Stock> fetchStockData(Stock[] stocks) throws IOException {
		Log.d(TAG,"Fetching stock data");
		ArrayList<Stock> newStocks = new ArrayList<Stock>(stocks.length);
		if(stocks.length > 0) {
			StringBuilder sb = new StringBuilder();
			for(Stock stock: stocks){
				sb.append(stock.getSymbol());
				sb.append("+");
			}
			sb.deleteCharAt(sb.length() - 1);
			String urlStr = "adres konsoli sprz";
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(urlStr.toString());
			HttpResponse response = client.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = reader.readLine();
			int i = 0;
			Log.d(TAG,"Parsing stock data");
			while(line != null){
				Log.d(TAG,"Parsing line: " + line);
				String[] values = line.split(",");
				Stock stock = new Stock(stocks[i],stocks[i].getId());
				stock.setCurrentPrice(Double.parseDouble(values[1]));
				stock.setName(values[2]);
				Log.d(TAG,"Parsed stock: " + stock);
				newStocks.add(stock);
				line = reader.readLine();
				i++;	
			}	
		}
		return newStocks;
	}
	
	private void createHighPriceNotification(Stock stock){
		NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		String shortMsg = "Powiadomienie o przekroczeniu minimum: " + stock.getSymbol();
		long time = System.currentTimeMillis();
		Notification n = new Notification(R.drawable.ic_launcher,shortMsg,time);
		String title = stock.getName();
		String msg = "Obecna cena: " + stock.getCurrentPrice() + " jest wysoka";
		Intent i = new Intent(this,NotificationDetails.class);
		i.putExtra("stock", stock);
		PendingIntent pi = PendingIntent.getActivity(this, 0, 1, 0);
		n.setLatestEventInfo(this, title, msg, pi);
		n.defaults |= Notification.DEFAULT_SOUND;
		long[] steps = {0,500,100,200,100,200};
		n.vibrate = steps;
		n.ledARGB = 0x80009500;
		n.ledOffMS = 250;
		n.ledOnMS = 500;
		n.flags |= Notification.FLAG_SHOW_LIGHTS;
		mgr.notify(HIGH_PRICE_NOTIFICATION,n);
		
		
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
