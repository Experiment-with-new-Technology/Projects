package com.example.android.contactslist.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contactslist.R;
import com.example.android.contactslist.R.string;

public class EditActivity extends Activity implements OnItemClickListener {
	List<String> name1 = new ArrayList<String>();
	List<String> phno1 = new ArrayList<String>();
	MyAdapter ma;
	Button select;
	String delete_name,delete_nu;
	
	boolean bool;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        getAllContacts(this.getContentResolver());
        ListView lv= (ListView) findViewById(R.id.lv);
            ma = new MyAdapter();
            lv.setAdapter(ma);
            lv.setOnItemClickListener(this); 
            lv.setItemsCanFocus(false);
            lv.setTextFilterEnabled(true);
            // adding
           select = (Button) findViewById(R.id.button1);
        select.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v) {
                    StringBuilder checkedcontacts= new StringBuilder();

                for(int i = 0; i < name1.size(); i++)

                    {
                    if(ma.mCheckStates.get(i)==true)
                    {
                         checkedcontacts.append(name1.get(i).toString());
                         delete_nu=phno1.get(i).toString();
                         delete_name= name1.get(i).toString();
                        
                         checkedcontacts.append("\n");
                         bool= deleteContact(getApplicationContext(), delete_nu, delete_name);
                         if(bool==true)
                        	 Toast.makeText(EditActivity.this, delete_name,Toast.LENGTH_SHORT).show();
                        	 
                         
                        

                    }
                    else
                    {

                    }


                }

                Toast.makeText(EditActivity.this, checkedcontacts,1000).show();
            }       
        });


    }
	public static boolean deleteContact(Context ctx, String phone, String name) {
	    Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
	    Cursor cur = ctx.getContentResolver().query(contactUri, null, null, null, null);
	    try {
	        if (cur.moveToFirst()) {
	            do {
	                if (cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
	                    String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
	                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
	                    ctx.getContentResolver().delete(uri,null, null);
	                    return true;
	                }

	            } while (cur.moveToNext());
	        }

	    } catch (Exception e) {
	        System.out.println(e.getStackTrace());
	    }
	    return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		ma.toggle(arg2);
	}

	public void getAllContacts(ContentResolver cr) {

		Cursor phones = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {
			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			name1.add(name);
			phno1.add(phoneNumber);
		}

		phones.close();
	}

	class MyAdapter extends BaseAdapter implements
			CompoundButton.OnCheckedChangeListener {
		private SparseBooleanArray mCheckStates;
		LayoutInflater mInflater;
		TextView tv1, tv;
		CheckBox cb;

		MyAdapter() {
			mCheckStates = new SparseBooleanArray(name1.size());
			mInflater = (LayoutInflater) EditActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name1.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub

			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View vi = convertView;
			if (convertView == null)
				vi = mInflater.inflate(R.layout.row, null);
			tv = (TextView) vi.findViewById(R.id.textView1);
			tv1 = (TextView) vi.findViewById(R.id.textView2);
			cb = (CheckBox) vi.findViewById(R.id.checkBox1);
			tv.setText("Name :" + name1.get(position));
			tv1.setText("Phone No :" + phno1.get(position));
			cb.setTag(position);
			cb.setChecked(mCheckStates.get(position, false));
			cb.setOnCheckedChangeListener(this);

			return vi;
		}

		public boolean isChecked(int position) {
			return mCheckStates.get(position, false);
		}

		public void setChecked(int position, boolean isChecked) {
			mCheckStates.put(position, isChecked);
		}

		public void toggle(int position) {
			setChecked(position, !isChecked(position));
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

			mCheckStates.put((Integer) buttonView.getTag(), isChecked);
		}
	}
}
