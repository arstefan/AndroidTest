package com.zinga.lifecycleexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends LifecycleActivity {

	private Button finish;
	private Button activity2;
	private Button resetClock;
	private Chronometer chrono;
	private long timeWhenStopped = 0;
	
	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetClock = (Button) findViewById(R.id.resetClock);
       
        /* reset chronometer */
        resetClock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chrono.setBase(SystemClock.elapsedRealtime());
				chrono.stop();
				chrono.start();
				timeWhenStopped = 0;
				
			}
		});
        
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
        activity2 = (Button) findViewById(R.id.activity2);
        activity2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,Activity2.class));
				
			}
		});
        chrono = (Chronometer) findViewById(R.id.chrono);
        
        
        
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
    	chrono.start();
    	
    	
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
    	chrono.stop();
    	super.onPause();
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
