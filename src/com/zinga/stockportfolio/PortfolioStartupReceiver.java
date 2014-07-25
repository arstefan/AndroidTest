package com.zinga.stockportfolio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PortfolioStartupReceiver extends BroadcastReceiver {
	private static final int FIFTEEN_MINUTES = 15*60*1000;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent stockService = new Intent(context,PortfolioManagerService.class);
		context.startService(stockService);
	}

}
