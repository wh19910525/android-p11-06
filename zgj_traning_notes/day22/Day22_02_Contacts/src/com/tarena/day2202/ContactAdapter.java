package com.tarena.day2202;

import java.util.ArrayList;

import com.tarena.biz.ContactBiz;
import com.tarena.entity.Contact;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
	
	private ArrayList<Contact> contacts;
	private LayoutInflater inflater;
	private ContactBiz biz;

	public void setContacts(ArrayList<Contact> contacts) {
		if (contacts != null)
			this.contacts = contacts;
		else
			this.contacts = new ArrayList<Contact>();
	}

	public ContactAdapter(Context context, ArrayList<Contact> contacts) {
		this.setContacts(contacts);
		this.inflater = LayoutInflater.from(context);
		this.biz = new ContactBiz(context);
	}

	public void changeData(ArrayList<Contact> contacts) {
		this.setContacts(contacts);
		this.notifyDataSetChanged();//¸üÐÂlistview
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Contact contact = contacts.get(position);

		Bitmap bm = biz.getPhoto(contact.getId());
		if (bm == null)
			holder.ivPhoto.setImageResource(R.drawable.ic_launcher);
		else
			holder.ivPhoto.setImageBitmap(bm);

		holder.tvName.setText(contact.getName());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivPhoto;
		private TextView tvName;
	}

}
