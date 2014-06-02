package com.example.android.contactslist.ui;
import com.example.android.contactslist.R;

import java.util.Calendar;


import android.support.v4.app.Fragment;
import android.text.Editable;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;

public class SceduleSMS extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scedulesms);
		
		final String name, number;
		String sms;
		name = getIntent().getExtras().getString("Name");
		number = getIntent().getExtras().getString("Number");
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(name);
		final EditText et1 = (EditText) findViewById(R.id.editText1);
		Button b= (Button) findViewById(R.id.btncan);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
			
		});
		Button s= (Button) findViewById(R.id.btnsave);
		s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePicker dp= (DatePicker) findViewById(R.id.datePicker1);
				TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);
				
				
				Editable sms = et1.getText();
				
				int day=dp.getDayOfMonth();
				int month=dp.getMonth();
				int Year=dp.getYear();
				int hour=tp.getCurrentHour();
				int min=tp.getCurrentMinute();
				
				//Toast.makeText(getApplicationContext(), sms+" "+Year+" "+month+" "+day+" "+hour+" "+min, Toast.LENGTH_SHORT).show();
			    	 
				//Intent alarm_receiver_doctor_class;
				
				if((sms+"").equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please Write something", Toast.LENGTH_LONG).show();					
					return;
				}
				Intent intent = new Intent(SceduleSMS.this,SMSreceiver.class); 
				intent.putExtra("Name", name); 
				intent.putExtra("Number",number); 
				intent.putExtra("SMS",sms +""); 
				
				int eventID = 1;
				
			    Calendar cal = Calendar.getInstance();
			    	/* cal.set(Calendar.HOUR_OF_DAY,+hour);
		         	 cal.set(Calendar.MINUTE,+min);
		         	 
		         	 cal.set(Calendar.YEAR,+Year);
		         	 cal.set(Calendar.MONTH,+month);
		         	 cal.set(Calendar.DAY_OF_MONTH,+day);
		         	 */
		         	PendingIntent sender = PendingIntent.getBroadcast(SceduleSMS.this, eventID,
		        	 		intent,PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);
		        	eventID+=1; 
		        	Toast.makeText(getApplicationContext(), "An SMS will be sent to "+name, Toast.LENGTH_LONG).show();
					   
		        	
		        		 /* get the Alarm service and set the alarm with the time to go off and what to do when the
		        		  * alarm is received */

		        		 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		        		 am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
		        		
		        			// am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 365*24*60*60*1000, sender);
		        		// }
		        		 am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),0, sender);
		        		 finish();
			}
			
		});
		
	}

	
	
	

	


}
