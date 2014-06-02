package com.example.android.contactslist.ui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class Util {

	public String getContactPhoneNumber(Context context, String contactId) {
		String phoneNumber = null;
		int type = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
	    String[] whereArgs = new String[] { String.valueOf(contactId), String.valueOf(type) };

	    //Log.d(TAG, String.valueOf(contactId));

	    Cursor cursor = context.getContentResolver().query(
	            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	            null,
	            ContactsContract.CommonDataKinds.Phone._ID + " = ? and "
	                    + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?", whereArgs, null);

	    int phoneNumberIndex = cursor
	            .getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

	    //Log.d(TAG, String.valueOf(cursor.getCount()));

	    if (cursor != null) {
	        //Log.v(TAG, "Cursor Not null");
	        try {
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

	    //Log.v(TAG, "Returning phone number");
	    return phoneNumber;
		}
	
	public String phnNumber(Uri uri, Context context){
		//Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    	String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone._ID,
    			ContactsContract.CommonDataKinds.Email.DATA,
    			ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
    			//ContactsContract.CommonDataKinds.BaseTypes,
		                ContactsContract.CommonDataKinds.Phone.NUMBER};

    	
		Cursor people = context.getContentResolver().query(uri, projection, null, null, null);


		int indexid = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
		int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
		int indexEmail = people.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
		//Toast.makeText(getApplicationContext(),indexid+"", Toast.LENGTH_LONG).show();
		int num=-1;
		
		String ContactNumber="0";
		people.moveToFirst();
		String _id;
		
			_id   = people.getString(indexid);
		    String name   = people.getString(indexName);
		    String number = people.getString(indexNumber);
		    String email =  people.getString(indexEmail);
		    
		    	ContactNumber	=  	number;
		    	//contactEmail	=	email;
		    	
		    	
		   return ContactNumber; 	
		    	
		    
	}
}
