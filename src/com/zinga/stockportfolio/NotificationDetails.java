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

public class NotificationDetails extends ListActivity {
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detials);
        Stock stock = (Stock) this.getIntent().getParcelableExtra("stock");  
    }

   
}
