package com.tarena.day2202;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tarena.biz.ContactBiz;
import com.tarena.entity.Contact;

public class Day22_02_ContactsActivity extends Activity {
	
	private ListView lvContacts;
	private ContactBiz biz;
	private ContactAdapter adapter;
	private ContactObserver observer;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				adapter.changeData(biz.getContacts());
				break;

			default:
				break;
			}
		};
	};

	private class ContactObserver extends ContentObserver {
		
		public ContactObserver() {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			handler.sendEmptyMessage(0);
		}

	}

	private void setupView() {
		lvContacts = (ListView) findViewById(R.id.lvContacts);
		adapter = new ContactAdapter(this, biz.getContacts());
		lvContacts.setAdapter(adapter);
	}

	private void addListener() {
		
		lvContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				Contact contact = (Contact) adapter.getItem(position);
				Intent intent = new Intent(Day22_02_ContactsActivity.this, DetailsActivity.class);
				intent.putExtra("contact", contact);
				startActivity(intent);

			}

		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		biz = new ContactBiz(this);
		setupView();
		addListener();
		observer = new ContactObserver();
		getContentResolver().registerContentObserver(Contacts.CONTENT_URI, true, observer);//
	}
}
