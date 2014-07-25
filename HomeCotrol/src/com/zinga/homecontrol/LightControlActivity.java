package com.zinga.homecontrol;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LightControlActivity extends ListActivity {
	
	private static final int MENU_REPARSE = 0;
	
	private Spinner sectionSpinner;
	private HomeControlApp app;
	
	//private ArrayAdapter<Section> spinnerAdapter;
	
	private List<Item> items;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ustawianie pliku uk³adu. Przekszta³canie uk³adu na klasy nazywane jest inflanting
		setContentView(R.layout.activity_light_control);
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		//progressDialog.setMessage(getString(R.string.))
		
		items = new ArrayList<Item>();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light_control, menu);
		return true;
	}
	
	private class LightControlAdapter extends ArrayAdapter<Item>{
		
		public LightControlAdapter(List<Item> items){
			super(LightControlActivity.this,R.layout.activity_light_control,items);
		}
		
	}
	

}
