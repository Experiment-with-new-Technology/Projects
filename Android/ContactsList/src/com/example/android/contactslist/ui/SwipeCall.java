package com.example.android.contactslist.ui;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

public class SwipeCall {

	public String getContactPhoneNumberByPhoneType(Context context, String contactId) {
	    String phoneNumber = null;

	    String[] whereArgs = new String[] { contactId };

	   // Log.d(TAG, String.valueOf(contactId));

	    Cursor cursor = context.getContentResolver().query(
	            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	            null,
	            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
	                    , whereArgs, null);

	    //cursor.moveToFirst();
	    

	   // Log.d(TAG, String.valueOf(cursor.getCount()));

	    if (cursor != null) {
	       // Log.v(TAG, "Cursor Not null");
	        try {	        	
	        	int phoneNumberIndex = cursor
	            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
	        	//cursor.moveToFirst();
	            if (cursor.moveToNext()) {
	                //Log.v(TAG, "Moved to first");
	                //Log.v(TAG, "Cursor Moved to first and checking");
	            	
	                phoneNumber = cursor.getString(phoneNumberIndex);
	            }
	        } finally {
	            //Log.v(TAG, "In finally");
	            cursor.close();
	        }
	    }
	    else
	    	Toast.makeText(context, "noo", Toast.LENGTH_LONG).show();

	    //Log.v(TAG, "Returning phone number");
	    return phoneNumber;
	}
}
