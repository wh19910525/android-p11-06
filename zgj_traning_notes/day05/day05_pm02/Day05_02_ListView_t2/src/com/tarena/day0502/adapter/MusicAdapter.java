package com.tarena.day0502.adapter;

import java.util.ArrayList;

import com.tarena.day0502.R;
import com.tarena.entity.Music;
import com.tarena.utils.TimeFormatter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	public MusicAdapter(Context context, ArrayList<Music> musics) {
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
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
		// TODO Auto-generated method stub
		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvSinger = (TextView) convertView
					.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tvDuration);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 根据position 获取item数据
		Music music = musics.get(position);

		// 数据绑定到item界面
		holder.tvName.setText(music.getName());
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getSinger());
		holder.tvDuration.setText(TimeFormatter.format(music.getDuration()));

		// 返回item界面
		return convertView;
	}

	class ViewHolder {
		private TextView tvName;
		private TextView tvAlbum;
		private TextView tvSinger;
		private TextView tvDuration;
	}
}
