package com.zinga.stockportfolio;

import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {
	
	private String symbol; 
	private double maxPrice;
	private double minPrice;
	private double pricePaid;
	private int quantity;
	
	// dynamic retrieved
	private String name ="";
	private double currentPrice=0D;
	
	// db assigned
	private int id;
	
	// constructors
	public Stock(String symbol, double pricePaid, int quantity) {
		this(symbol, pricePaid, quantity, 0);
	}
	public Stock(String symbol, double pricePaid, int quantity, int id) {
		this.symbol = symbol;
		this.pricePaid = pricePaid;
		this.quantity = quantity;
		this.id = id;
	}
	public Stock(Stock old, int id){
		this.symbol = old.symbol;
		this.maxPrice = old.maxPrice;
		this.minPrice = old.minPrice;
		this.pricePaid = old.pricePaid;
		this.quantity = old.quantity;
		this.name = old.name;
		this.currentPrice = old.currentPrice;
		this.id = id;
	}
	private Stock(Parcel parcel){
		this.readFromParcel(parcel);
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public double getPricePaid() {
		return pricePaid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (name != null){
			sb.append(name).append(' ');
		}
		sb.append('(').append(symbol.toUpperCase()).append(')')
			.append(" $").append(currentPrice);
		return sb.toString();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(symbol);
		dest.writeDouble(maxPrice);
		dest.writeDouble(minPrice);
		dest.writeDouble(pricePaid);
		dest.writeInt(quantity);
		
	}
	
	public void readFromParcel(Parcel parcel){
		symbol = parcel.readString();
		maxPrice = parcel.readDouble();
		minPrice = parcel.readDouble();
		pricePaid = parcel.readDouble();
		quantity = parcel.readInt();
	}
	
	
}