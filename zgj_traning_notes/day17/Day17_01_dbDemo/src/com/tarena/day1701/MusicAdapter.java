package com.tarena.day1701;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class MusicAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public MusicAdapter(Context context, ArrayList<Music> musics) {
		this.inflater = LayoutInflater.from(context);
		this.setMusics(musics);
	}

	public void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	/*
	 * 变更数据集,并更新界面
	 */
	public void changeData(ArrayList<Music> musics) {
		this.setMusics(musics);
		this.notifyDataSetChanged();
	}

	/**
	 * 从数据集中移除数据并更新界面
	 */
	public void removeItem(int position) {
		if (position >= 0 && position < musics.size()) {
			musics.remove(position);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return musics.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return musics.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			holder.tvSinger = (TextView) convertView.findViewById(R.id.tvSinger);
			holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Music m = musics.get(position);

		holder.tvName.setText(m.getName());
		holder.tvAlbum.setText(m.getAlbum());
		holder.tvSinger.setText(m.getSinger());
		holder.tvDuration.setText(GlobalUtils.format(m.getDuration()));

		// TODO Auto-generated method stub
		return convertView;
	}

	class ViewHolder {
		private TextView tvName;
		private TextView tvSinger;
		private TextView tvDuration;
		private TextView tvAlbum;
	}

}
