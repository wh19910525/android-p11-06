package com.tarena.day1803.adapter;

import java.util.ArrayList;

import com.tarena.biz.ImageBiz;
import com.tarena.day1803.R;
import com.tarena.entity.ImageInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	
	private ArrayList<ImageInfo> images;
	private LayoutInflater inflater;
	private ImageBiz biz;

	public void setImages(ArrayList<ImageInfo> images) {
		if (images != null)
			this.images = images;
		else
			this.images = new ArrayList<ImageInfo>();
	}

	public void changeData(ArrayList<ImageInfo> images) {
		this.setImages(images);
		this.notifyDataSetChanged();
	}

	public ImageAdapter(Context context, ArrayList<ImageInfo> images) {
		
		this.setImages(images);
		this.inflater = LayoutInflater.from(context);
		this.biz = new ImageBiz(context);//”– ≤√¥”√
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
		return images.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivThumb = (ImageView) convertView.findViewById(R.id.ivPic);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageInfo img = images.get(position);

		holder.ivThumb.setImageBitmap(biz.getThumbnail(img.getId()));
		holder.tvTitle.setText(img.getTitle());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivThumb;
		private TextView tvTitle;
	}

}
