package com.zinga.lifecycleexplorer;

import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends LifecycleActivity {
	
	private static final String COUNT_KEY = "cKey";
	
	private TextView numResumes;
	private int count;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity3);
		numResumes = (TextView) findViewById(R.id.numResumes);
		if(numResumes == null){
			System.out.println("jest null-em");
		} else System.out.println("nie jest null-em");
		count = 1;
		/*Date date = (Date) this.getL
		 if (date != null) {
	         Toast.makeText(this, "\"LastNonConfiguration\" object present: " + date, Toast.LENGTH_LONG).show();
	      } */  
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		numResumes.setText(String.valueOf(count));
		count++;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState!=null && savedInstanceState.containsKey(COUNT_KEY)){
			count = savedInstanceState.getInt(COUNT_KEY);
		}
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putInt(COUNT_KEY, count);
		super.onSaveInstanceState(outState);
	}
	
	
	

}
