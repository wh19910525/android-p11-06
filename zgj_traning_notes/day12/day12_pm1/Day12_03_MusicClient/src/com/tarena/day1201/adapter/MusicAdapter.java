package com.tarena.day1201.adapter;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tarena.day1201.R;
import com.tarena.entity.Music;
import com.tarena.entity.Task;
import com.tarena.utils.AsyncImageLoader;
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.HttpUtils;

public class MusicAdapter extends BaseAdapter {

	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	private Handler handler;
	private AsyncImageLoader loader;//

	public MusicAdapter(Context context, ArrayList<Music> musics, final ListView lvMusics) {//
		
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
		this.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case AsyncImageLoader.MSG_TAG_FINISHED:// ͼƬ�������
					// ��ȡ������������
					Task task = (Task) msg.obj;
					// ����ͼƬ·��Ϊtag����listview�в���imageView
					ImageView iv = (ImageView) lvMusics.findViewWithTag(task.getPath());
					// �����imageviewδ�����ã�����iamgeview����ʾͼƬ
					if (iv != null && task.getBitmap() != null) {
						iv.setImageBitmap(task.getBitmap());
					}
					break;
				}

			}
		};
		this.loader = new AsyncImageLoader(handler);//
	}

	private void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	/**
	 * ����adapter�е����ݼ�
	 * 
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics) {
		this.setMusics(musics);
		this.notifyDataSetChanged();// ����listView
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
		// ���غ͸���convertView
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.ivAlbum);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
			holder.tvSinger = (TextView) convertView.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// ��ȡ����
		final Music music = musics.get(position);

		// �󶨵�����
		holder.tvName.setText(music.getName());
		holder.tvDuration.setText(music.getDuration());
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getSinger());

		String path = music.getAlbumPath();
		// ����ͼƬ·��Ϊiamgeview��tag
		holder.ivAlbum.setTag(path);//
		// ����ͼƬ
		Bitmap bm = loader.loadBitmap(path);//
		// ������ڻ���ͼƬ����ʾ��������ʾĬ��ͼƬ
		if (bm != null)
			holder.ivAlbum.setImageBitmap(bm);
		else
			holder.ivAlbum.setImageResource(R.drawable.ic_launcher);

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
