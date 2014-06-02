package com.example.android.contactslist.ui;


import com.example.android.contactslist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity{



		@Override
		protected void onCreate(Bundle abcd) {
			super.onCreate(abcd);
			setContentView(R.layout.main_page);
			
			

			Thread timer=new Thread(){
				public void run(){
					try{
						sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}finally{
						Intent openStartingPoint=new Intent(Main.this, ContactsListActivity.class);
						startActivity(openStartingPoint);
					}
				}
			};
			timer.start();
		}

		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
		
		

	}


