package com.example.android.contactslist.ui;

import android.app.Activity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;

import com.example.android.contactslist.R;

public class ExportContact extends Activity {
	Cursor cursor;
	ArrayList<String> vCard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);

	}

	public void ImportContact(View view) {

		Intent importcon = new Intent(getApplication(), MainActivity.class);
		startActivity(importcon);
	}

	public void getVcardString(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ExportContact.this);
		builder.setTitle("Export Contact");
		builder.setMessage("Want to Export Contacts?");
		builder.setCancelable(false);

		final String vfile = "Contacts.vcf";
		// slected contact

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Cursor phones = getApplicationContext()
						.getContentResolver()
						.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
								null, null, null, null);

				phones.moveToFirst();
				for (int i = 0; i < phones.getCount(); i++) {
					String lookupKey = phones.getString(phones
							.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
					Uri uri = Uri.withAppendedPath(
							ContactsContract.Contacts.CONTENT_VCARD_URI,
							lookupKey);

					AssetFileDescriptor fd;
					try {
						fd = getApplicationContext().getContentResolver()
								.openAssetFileDescriptor(uri, "r");
						FileInputStream fis = fd.createInputStream();
						byte[] buf = new byte[(int) fd.getDeclaredLength()];
						fis.read(buf);
						String VCard = new String(buf);
						String path = Environment.getExternalStorageDirectory()
								.toString() + File.separator + vfile;
						@SuppressWarnings("resource")
						FileOutputStream mFileOutputStream = new FileOutputStream(
								path, true);
						mFileOutputStream.write(VCard.toString().getBytes());
						phones.moveToNext();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	public void Share(View view) {
		getVcardString2();
		Intent shareIntent = new Intent();
		shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		shareIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(
				Intent.EXTRA_STREAM,
				Uri.fromFile(new File(Environment.getExternalStorageDirectory()
						.toString() + "/contacts.vcf")));
		shareIntent.setType("text/x-vcard");
		shareIntent.setPackage("com.android.bluetooth");
		startActivity(Intent.createChooser(shareIntent, "Share contact"));

	}

	private void getVcardString2() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ExportContact.this);
		builder.setTitle("Export Contact");
		builder.setMessage("Want to Export Contacts?");
		builder.setCancelable(false);

		final String vfile = "Contacts.vcf";
		// slected contact

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Cursor phones = getApplicationContext()
						.getContentResolver()
						.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
								null, null, null, null);

				phones.moveToFirst();
				for (int i = 0; i < phones.getCount(); i++) {
					String lookupKey = phones.getString(phones
							.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
					Uri uri = Uri.withAppendedPath(
							ContactsContract.Contacts.CONTENT_VCARD_URI,
							lookupKey);

					AssetFileDescriptor fd;
					try {
						fd = getApplicationContext().getContentResolver()
								.openAssetFileDescriptor(uri, "r");
						FileInputStream fis = fd.createInputStream();
						byte[] buf = new byte[(int) fd.getDeclaredLength()];
						fis.read(buf);
						String VCard = new String(buf);
						String path = Environment.getExternalStorageDirectory()
								.toString() + File.separator + vfile;
						@SuppressWarnings("resource")
						FileOutputStream mFileOutputStream = new FileOutputStream(
								path, true);
						mFileOutputStream.write(VCard.toString().getBytes());
						phones.moveToNext();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}
}
