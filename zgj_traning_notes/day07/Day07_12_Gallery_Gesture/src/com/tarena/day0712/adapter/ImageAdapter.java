package com.tarena.day0712.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarena.day0712.R;
import com.tarena.entity.ImageInfo;

public class ImageAdapter extends BaseAdapter {
	private ArrayList<ImageInfo> images;
	private LayoutInflater inflater;

	public void setImages(ArrayList<ImageInfo> images) {
		if (images != null)
			this.images = images;
		else
			this.images = new ArrayList<ImageInfo>();
	}

	public ImageAdapter(Context context, ArrayList<ImageInfo> images) {
		this.setImages(images);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivThumb = (ImageView) convertView.findViewById(R.id.ivThumbnail);
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
