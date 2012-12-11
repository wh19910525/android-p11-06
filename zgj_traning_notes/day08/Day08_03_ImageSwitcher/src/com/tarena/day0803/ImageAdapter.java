package com.tarena.day0803;

import java.util.ArrayList;

import com.tarena.entity.ImageInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private ArrayList<ImageInfo> images;
	private LayoutInflater inflater;

	public ImageAdapter(Context context, ArrayList<ImageInfo> images) {
		if (images != null)
			this.images = images;
		else
			this.images = new ArrayList<ImageInfo>();

		this.inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}
	
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images.get(position);
	}
	
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageInfo img = images.get(position);

		holder.ivThumb.setImageBitmap(img.getThumbnail());
		holder.tvTitle.setText(img.getTitle());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivThumb;
		private TextView tvTitle;
	}
}
