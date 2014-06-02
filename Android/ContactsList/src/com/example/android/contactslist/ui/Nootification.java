package com.example.android.contactslist.ui;
import com.example.android.contactslist.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;




public class Nootification extends Activity {
    /** Called when the activity is first created. */
	TextView tvTime;
	Intent alarmIntent,mainactivity_intent_intent,database_intent,getTime,alarm_receiverclass;
   
	public static int eventID = 0;
	String hours;
	//public int flagss,date,month,year;
	Button btn;
public int input_hour,i,j;

	public int input_minute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        tvTime=(TextView)findViewById(R.id.textView1);
       
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/kalpurush.ttf");
		
        tvTime.setTypeface(face);
        String output ="";
        
        SQLiteDatabase db = openOrCreateDatabase("db_food_medicine_Alarm_receiver", 0, null);
     	db.execSQL("CREATE TABLE IF NOT EXISTS tbl_food_medicine_Alarm_receiver(output varchar(100));");
     	Cursor c=db.rawQuery("select * from tbl_food_medicine_Alarm_receiver ;", null);
		
     	if(c.getCount()==0)
     	{
     		//db.execSQL("delete from tbl_food_medicine_Alarm_receiver ;");
            db.close();
     		finish();
     	}
     	while(c.moveToNext())
			{
				output += (c.getString(c.getColumnIndex("output"))+"\n");
		     	
			}
        db.execSQL("delete from tbl_food_medicine_Alarm_receiver ;");
        db.close();
        tvTime.setText(output);
 // alarm_receiverclass=new Intent(this,food_medicine_Alarm_receiver.class);
 
  
 }}


