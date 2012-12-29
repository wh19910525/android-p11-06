package com.tarena.day2001;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class MusicAdapter extends BaseAdapter {

	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public MusicAdapter(Context context, ArrayList<Music> musics) {
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
	}

	private void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	/**
	 * 更新adapter中的数据集
	 * 
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics) {
		this.setMusics(musics);
		this.notifyDataSetChanged();// 更新listView
	}

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
		// TODO Auto-generated method stub
		// 加载和复用convertView
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.ivAlbum);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tvDuration);
			holder.tvSinger = (TextView) convertView
					.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		final Music music = musics.get(position);

		// 绑定到界面
		holder.tvName.setText(music.getName());
		holder.tvDuration.setText(GlobalUtils.format(music.getDuration()));
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getArtist());

		Bitmap bm = BitmapFactory.decodeFile(music.getAlbumPath());
		if (bm == null) {
			holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
		} else {
			holder.ivAlbum.setImageBitmap(bm);
		}

		return convertView;
	}

	class ViewHolder {
		private ImageView ivAlbum;
		private TextView tvName;
		private TextView tvDuration;
		private TextView tvSinger;
		private TextView tvAlbum;
	}
}
