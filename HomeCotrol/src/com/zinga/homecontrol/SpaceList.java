package com.zinga.homecontrol;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zinga.homecontrol.model.Floor;
import com.zinga.homecontrol.model.Space;

public class SpaceList extends ListActivity {
	
	private static final int MENU_REPARSE = 0;
	
	HomeControlApp app;
	private List<Space> spaces;
	private SpaceAdapter spacesAdapter;
	private Spinner floorSpinner;
	private ArrayAdapter<Floor> spinnerAdapter;
	private int currentSelectedFloor;
	private ProgressDialog progressDialog;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_space_list);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(true);
		progressDialog.setMessage("�adowanie listy pomieszcze�");
		
		app = (HomeControlApp) getApplication();
		spaces = new ArrayList<Space>();
		spacesAdapter = new SpaceAdapter(spaces);
		setListAdapter(spacesAdapter);
		if(app.getFloorList().isEmpty()){
			if(app.connectionPresent()){
				/*pobierz list� pokoi */
			} else{
				Toast.makeText(this, 
						getString(resId, formatArgs), 
						Toast.LENGTH_LONG);
			}
		} else {
			/* reset listy pokoi */
		}
		floorSpinner = (Spinner) findViewById(R.id.floor_spinner);
		spinnerAdapter = new ArrayAdapter<Floor>(SpaceList.this, android.R.layout.simple_spinner_item,app.getFloorList());
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		floorSpinner.setAdapter(spinnerAdapter);
		floorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				if(currentSelectedFloor != position){
					currentSelectedFloor = position;
					resetListSpaces(app.getFloorList().get(0).getSpaces());
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(progressDialog.isShowing()){
			progressDialog.dismiss();
		}
		super.onPause();
	}
	
	private void resetListSpaces(List<Space> newSpaces){
		spaces.clear();
		spaces.addAll(newSpaces);
		spacesAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.space_list, menu);
		menu.add(0, SpaceList.MENU_REPARSE, 0, R.string.space_list_reparse_menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_REPARSE:
			if(app.connectionPresent()){
				/* lista pi�ter w menu */
			} else {
				Toast.makeText(this, 
						getString(R.string.space_list_network_unavailable), 
						Toast.LENGTH_LONG);
			}
			return true;

		return super.onOptionsItemSelected(item);
	}
	
	
	private class SpaceAdapter extends ArrayAdapter<Space> {

		public SpaceAdapter(List<Space> spaces) {
			super(SpaceList.this, R.layout.activity_space_list,spaces);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null){
				  LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		            convertView = inflater.inflate(R.layout.activity_space_list, parent, false);
			}
			
			
			return super.getView(position, convertView, parent);
		}

		
		
	}
	
	

}
