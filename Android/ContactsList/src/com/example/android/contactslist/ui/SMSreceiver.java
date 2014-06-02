package com.example.android.contactslist.ui;
import com.example.android.contactslist.R;

import java.util.Date;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;



public class SMSreceiver extends BroadcastReceiver{

	String name,number,sms;
	 public void onReceive(Context context, Intent intent) {		         
		number="12";
		name = intent.getExtras().getString("Name");
		number =intent.getExtras().getString("Number");
		sms =intent.getExtras().getString("SMS");
		sendsms(number, sms);
		//Toast.makeText(context,"FS "+name +" "+sms, Toast.LENGTH_LONG).show();
		    
		
	int sdk =  android.os.Build.VERSION.SDK_INT;
	//Toast.makeText(context, ""+sdk,Toast.LENGTH_SHORT).show();
   
	if(sdk<10)
	{
		Intent intent1 = new Intent();
       intent1.setClassName("com.astuetz.viewpager.extensions.sample", "com.astuetz.viewpager.extensions.sample.Nootification");
       intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.startActivity(intent1);

	}
	else
	buildNotification(context);
		 
		
		 
		 
	 }
	 
	 
	//to send sms
	  private void sendsms(String phn,String sms){
	    	
	    	//PendingIntent pi=PendingIntent.getActivity(this, 0,  , 0);
	        SmsManager message=SmsManager.getDefault();
	    	message.sendTextMessage(phn, null, sms, null, null);
	  }
	 
	 private void buildNotification(Context context){
		  NotificationManager notificationManager 
		  = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		  NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		  
		  
		  
		  
		  
		  Intent intent = new Intent(context,Nootification.class);
		 // intent.putExtra("output", "hdry");
		 // intent.putExtra("hour", ""+hours);
		  PendingIntent pendingIntent 
		  = PendingIntent.getActivity(context, 0, intent, 0);
		  Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		  builder
		  .setSmallIcon(R.drawable.ic_launcher)
		  .setContentTitle("Contact Manager")
		  .setContentText("A SMS is sent to "+name)
		  .setContentInfo("ContentInfo")
		  .setTicker("SMS sentÂ�")
		  .setSound(soundUri)
		  .setLights(0xFFFF0000, 500, 500) //setLights (int argb, int onMs, int offMs)
		 .setContentIntent(pendingIntent)
		  .setAutoCancel(true);
		  
		  Notification notification = builder.getNotification();
		  
		  notificationManager.notify(R.drawable.ic_launcher, notification);
		 
	 	}
}