package com.zinga.stockportfolio;

import java.util.ArrayList;

import android.R;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;

import com.zinga.stockportfolio.service.IStockService;

public class ViewStocks extends ListActivity {
	
	protected static final String LOGGING_TAG = "View Stocks";
	private ArrayList<Stock> stocks;
	private IStockService stockService;
	
	
	public void onStart(Bundle savedInstanceState){
		super.onStart();
		bindService(new Intent(IStockService.class.getName()), connection, Context.BIND_AUTO_CREATE);
	}
	
	
	/*
	 * delegat informuj�cy o cyklu �ycia po��czenia ze zdaln� us�ug�
	 * -- pobieramy tu interfejs zdalnej us�ugi (reprezentowanej za pomoc� interfejsu IBinder)
	 * -- uzyskania implementacji lokalnego interfejsu
	 * 
	 */
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			stockService = null;
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			stockService = IStockService.Stub.asInterface(service);
			try {
				stocks = (ArrayList<Stock>) stockService.getPortfolio();
				if(stocks == null){
					stocks = new ArrayList<Stock>(0);
				}
				refresh();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				Log.e(LOGGING_TAG, "Wyst�pi� wyj�tek przy pobieraniu danych z us�ugi",e);
			}
			
		}
	};
	
	
	private void refresh(){
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_stocks, menu);
        return true;
    }  
}
