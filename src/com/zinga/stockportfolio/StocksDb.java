package com.zinga.stockportfolio;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class StocksDb {
	private static final String TAG = "StocksDb";
	// database metadata
	private static final String DB_NAME = "stocks.db";
	private static final int DB_VERSION = 1;
	
	private static final String TABLE_NAME = "stock";
	
	// column names
	private static final String ID = "id";
	private static final String SYMBOL = "symbol";
	private static final String MAX_PRICE = "max_price";
	private static final String MIN_PRICE = "min_price";
	private static final String PRICE_PAID = "price_paid";
	private static final String QUANTITY = "quantity";
	private static final String CURRENT_PRICE = "current_price";
	private static final String NAME = "name";
	
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
			" ("+ID+" INTEGER PRIMARY KEY, "+SYMBOL+" TEXT, "+
			MAX_PRICE+" DECIMAL(8,2), " + MIN_PRICE+" DECIMAL(8,2), " +
			PRICE_PAID+ " DECIMAL(8,2), " + QUANTITY + " INTEGER, " +
			CURRENT_PRICE + " DECIMAL(8,2), "+NAME+" TEXT)";
	private static final String INSERT_SQL = "INSERT INTO " + TABLE_NAME +
			" ("+SYMBOL+", "+MAX_PRICE+", "+MIN_PRICE+", "+PRICE_PAID+
			", "+QUANTITY+", " + CURRENT_PRICE+", "+NAME+") " +
			"VALUES (?,?,?,?,?,?,?)";
	private static final String READ_SQL = "SELECT "+ID+", "+SYMBOL+", " +
			MAX_PRICE+", " + MIN_PRICE +", "+PRICE_PAID+", "+ 
			QUANTITY+", " +CURRENT_PRICE+ ", "+NAME+" FROM " + 
			TABLE_NAME;
	private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME + 
		" SET "+CURRENT_PRICE+"=? WHERE "+ID+"=?";
	
	// The Context object that created this StocksDb
	private final Context context;
	private final SQLiteOpenHelper helper;
	private  SQLiteStatement stmt;
	private  SQLiteStatement updateStmt;
	private final SQLiteDatabase db;
	
	
	public StocksDb(Context ctx){
		context = ctx;
		
		// initialize the database helper
		helper = new SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL(CREATE_TABLE);
				Log.d(TAG, "Created table: \n" + CREATE_TABLE);
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, 
					int newVersion) {
				throw new UnsupportedOperationException();
			}
		};
		
		// open the database
		db = helper.getWritableDatabase();
		
		// pre-compile statements
		stmt = db.compileStatement(INSERT_SQL);
		updateStmt = db.compileStatement(UPDATE_SQL);
	}
	
	public Stock addStock(Stock stock){
		Log.d(TAG, "Adding stock to db, stock="+stock);
		stmt.bindString(1, stock.getSymbol());
		stmt.bindDouble(2, stock.getMaxPrice());
		stmt.bindDouble(3, stock.getMinPrice());
		stmt.bindDouble(4, stock.getPricePaid());
		stmt.bindLong(5, stock.getQuantity());
		stmt.bindDouble(6, stock.getCurrentPrice());
		stmt.bindString(7, stock.getName());
		int id = (int) stmt.executeInsert();
		return new Stock (stock, id);
	}
	
	public void updateStockPrice(Stock stock){
		Log.d(TAG, "Updating stock price in DB stock="+stock.toString());
		updateStmt.bindDouble(1, stock.getCurrentPrice());
		updateStmt.bindLong(2, stock.getId());
		updateStmt.execute();
	}
	
	public ArrayList<Stock> getStocks() {
		Log.d(TAG, "Getting stocks form DB");
		Cursor results = db.rawQuery(READ_SQL, null);
		ArrayList<Stock> stocks = new ArrayList<Stock>(results.getCount());
		if (results.moveToFirst()){
			int idCol = results.getColumnIndex(ID);
			int symbolCol = results.getColumnIndex(SYMBOL);
			int maxCol = results.getColumnIndex(MAX_PRICE);
			int minCol = results.getColumnIndex(MIN_PRICE);
			int priceCol = results.getColumnIndex(PRICE_PAID);
			int quanitytCol = results.getColumnIndex(QUANTITY);
			int currentPriceCol = results.getColumnIndex(CURRENT_PRICE);
			int nameCol = results.getColumnIndex(NAME);
			do {
				Stock stock = new Stock(results.getString(symbolCol), 
						results.getDouble(priceCol), 
						results.getInt(quanitytCol), results.getInt(idCol));
				stock.setMaxPrice(results.getDouble(maxCol));
				stock.setMinPrice(results.getDouble(minCol));
				stock.setCurrentPrice(results.getDouble(currentPriceCol));
				stock.setName(results.getString(nameCol));
				Log.d(TAG, "Stock from db = " + stock.toString());
				stocks.add(stock);
			} while (results.moveToNext());
		}
		if (!results.isClosed()){
			results.close();
		}
		return stocks;
	}
	
	public void close(){
		helper.close();
	}	
	
	
}
