package com.tarena.day1103.adapter;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tarena.day1103.R;
import com.tarena.entity.Music;
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.HttpUtils;

public class MusicAdapter extends BaseAdapter {
	
	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	private Handler handler;//
	private ListView lvMusics;//

	public MusicAdapter(Context context, ArrayList<Music> musics, ListView lvMusics) {
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
		this.lvMusics = lvMusics;
		this.handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 0:// ͼƬ�������
					Bitmap bm = (Bitmap) msg.obj;
					String path = msg.getData().getString("path");//������ msg.setData(bundle)�����ģ��ο� �±�
					ImageView iv = (ImageView) MusicAdapter.this.lvMusics.findViewWithTag(path);//ʹ�� setTag(music.getAlbumPath())�����ã�
					if (bm != null && iv != null) {
						iv.setImageBitmap(bm);
					}
					break;
				}
			};
		};
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
		this.notifyDataSetChanged();// ���� adapter
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
		return musics.get(position).getId();//
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
			convertView.setTag(holder);//
		} else {
			holder = (ViewHolder) convertView.getTag();//
		}

		// ��ȡ����
		final Music music = musics.get(position);

		// �󶨵�����
		holder.tvName.setText(music.getName());
		holder.tvDuration.setText(music.getDuration());
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getSinger());

		// ��������ר��·��Ϊimageview�����tag
		holder.ivAlbum.setTag(music.getAlbumPath());//
		// ����������ͼƬ
		new Thread() {
			public void run() {
				try {
					// ��ȡר��ͼƬ��·��
					String uri = HttpUtils.BASE_URL + music.getAlbumPath();
					HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
					byte[] data = EntityUtils.toByteArray(entity);
					Bitmap bm = BitmapUtils.loadBitmap(data, 100, 100);

					// ������Ϣ�����߳�
					Message msg = Message.obtain();
					msg.what = 0;
					Bundle bundle = new Bundle();//�ο� android ��ʶ �ʼ�
					bundle.putString("path", music.getAlbumPath());//
					msg.setData(bundle);//
					msg.obj = bm;

					handler.sendMessage(msg);
				} catch (ConnectTimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();

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
