package com.zinga.homecontrol;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import com.zinga.homecontrol.model.Floor;
import com.zinga.homecontrol.model.Section;
import com.zinga.homecontrol.model.Space;

public class HomeControlApp extends Application{
	private ConnectivityManager cMgr;
	private List<Floor> floorList;
	private Space currentSpace;
	
	
	public List<Floor> getFloorList() {
		return floorList;
	}


	public void setFloorList(List<Floor> floorList) {
		this.floorList = floorList;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.cMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		this.floorList = new ArrayList<Floor>();
		
	}
	
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	public boolean connectionPresent(){
		NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
		if ((netInfo != null) && (netInfo.getState() != null)) {
			return netInfo.getState().equals(State.CONNECTED);
		} 
		return false;
	}
	
	public Space getCurrentSpace() {
		return currentSpace;
	}
	
	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}

}
