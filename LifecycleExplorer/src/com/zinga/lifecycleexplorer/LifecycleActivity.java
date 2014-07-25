package com.zinga.lifecycleexplorer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public abstract class LifecycleActivity extends Activity {

	private NotificationManager notifyMgr;
	private static final String LOG_TAG = "Lifecycle Explorer";
	private boolean enableNotifications = true;
	private final String className;
	
	public LifecycleActivity(){
		super();
		this.className = this.getClass().getName();
	}
	
	public LifecycleActivity(final boolean enableNotifications){
		this();
		this.enableNotifications = enableNotifications;
	}
	
	@Override
	   public void onCreate(Bundle savedInstanceState) {      
	      super.onCreate(savedInstanceState);
	      System.out.println("asdasdsad");
	      notifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	      debugEvent("onCreate");
	   }

	   @Override
	   protected void onStart() {      
	      debugEvent("onStart");
	      super.onStart();      
	   }

	   @Override
	   protected void onResume() {      
	      debugEvent("onResume");
	      super.onResume();      
	   }

	   @Override
	   protected void onPause() {
	      debugEvent("onPause");
	      super.onPause();
	   }

	   @Override
	   protected void onStop() {
	      debugEvent("onStop");
	      super.onStop();
	   }

	   @Override
	   protected void onDestroy() {
	      debugEvent("onDestroy");
	      super.onDestroy();
	   }

	   //
	   // state related
	   //
	   @Override
	   protected void onRestoreInstanceState(Bundle savedInstanceState) {      
	      debugEvent("onRestoreInstanceState");
	      super.onRestoreInstanceState(savedInstanceState);      
	   }

	   @Override
	   protected void onSaveInstanceState(Bundle outState) {      
	      debugEvent("onSaveInstanceState");
	      super.onSaveInstanceState(outState);      
	   }

	   //
	   // configuration related 
	   //
	   @Override
	   public void onConfigurationChanged(Configuration newConfig) {      
	      debugEvent("onConfigurationChanged");
	      super.onConfigurationChanged(newConfig);      
	   }

	   @Override
	   public Object onRetainNonConfigurationInstance() {
	      debugEvent("onRetainNonConfigurationInstance");
	      return super.onRetainNonConfigurationInstance();
	   }

	   //
	   // other handy Activity methods
	   //
	   @Override
	   public boolean isFinishing() {
	      debugEvent("isFinishing");
	      return super.isFinishing();
	   }

	   @Override
	   public void finish() {
	      super.finish();
	   }

	   @Override
	   public void onLowMemory() {
	      Toast.makeText(this, "onLowMemory", Toast.LENGTH_SHORT).show();
	      super.onLowMemory();
	   }

	 private void debugEvent(final String method) {
		 System.out.println("method: " + method);
	      long ts = System.currentTimeMillis();
	      Log.d(LOG_TAG, " *** " + method + " " + className + " " + ts);
	      if (enableNotifications) {
	    	  System.out.println("this: " + this.className);
	    	  
	    	  //Intent intent = new Intent(this, NotificationReceiver.class);
		      PendingIntent pIntent = PendingIntent.getActivity(this,0,new Intent("asdadas"),Intent.FLAG_ACTIVITY_NEW_TASK);
		      RemoteViews notificationRemoteView = new RemoteViews(this.getPackageName(),R.layout.activity_lifecycle);
		    
		      notificationRemoteView.setImageViewResource(R.id.image, android.R.drawable.btn_star);
		      notificationRemoteView.setTextViewText(R.id.lifecycle_class, getClass().getName());
		      notificationRemoteView.setTextViewText(R.id.lifecycle_method, method);
		         //notificationRemoteView.setTextColor(R.id.lifecycle_method, R.color.black);
		      notificationRemoteView.setTextViewText(R.id.lifecycle_timestamp, Long.toString(ts));
		      Notification n = new NotificationCompat.Builder(this)
		      .setSmallIcon(R.drawable.ic_launcher)
              .setContentTitle("Custom View")
              .setAutoCancel(true)
	          .setContentIntent(pIntent)
	          .setContent(notificationRemoteView)
		      .build();
		      n.flags |= Notification.FLAG_AUTO_CANCEL;
		      
		      
		      
		    
		    
	    	
	    	  notifyMgr.notify((int) System.currentTimeMillis(), n);
	    	 
	    	  
	    	  
	      /*   Notification notification = new Notification(android.R.drawable.star_big_on, "Lifeycle Event: " + method, 0L);
	         RemoteViews notificationContentView = new RemoteViews(getPackageName(), R.layout.activity_lifecycle);
	         notification.contentView = notificationContentView;
	         notification.contentIntent = PendingIntent.getActivity(this, 0, null, 0);
	         notification.flags |= Notification.FLAG_AUTO_CANCEL;
	         notificationContentView.setImageViewResource(R.id.image, android.R.drawable.btn_star);
	         notificationContentView.setTextViewText(R.id.lifecycle_class, getClass().getName());
	         notificationContentView.setTextViewText(R.id.lifecycle_method, method);
	         //notificationContentView.setTextColor(R.id.lifecycle_method, R.color.black);
	         notificationContentView.setTextViewText(R.id.lifecycle_timestamp, Long.toString(ts));
	         notifyMgr.notify((int) System.currentTimeMillis(), notification);*/
	      }
	   }

}
