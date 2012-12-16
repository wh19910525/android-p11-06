package com.tarena.day1005.adapter;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

import com.tarena.day1005.R;
import com.tarena.entity.Music;
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.HttpUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
	
	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public MusicAdapter(Context context, ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
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
			convertView.setTag(holder);//
		} else {
			holder = (ViewHolder) convertView.getTag();//
		}

		// 获取数据
		Music music = musics.get(position);

		// 绑定到界面
		holder.tvName.setText(music.getName());
		holder.tvDuration.setText(music.getDuration());
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getSinger());

		// 联网，加载图片
		Bitmap bm = null;
		try {
			// 获取专辑图片的路径
			String uri = HttpUtils.BASE_URL + music.getAlbumPath();
			HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
			byte[] data = EntityUtils.toByteArray(entity);//把 实体对象转换为字节；
			bm = BitmapUtils.loadBitmap(data, 100, 100);
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//显示图片
		if (bm == null)
			holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
		else
			holder.ivAlbum.setImageBitmap(bm);

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
