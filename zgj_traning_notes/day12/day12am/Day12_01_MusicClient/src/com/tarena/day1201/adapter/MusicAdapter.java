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
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.HttpUtils;

public class MusicAdapter extends BaseAdapter {
	private class Task {
		private String path;
		private Bitmap bitmap;

		public Task(String path, Bitmap bitmap) {
			super();
			this.path = path;
			this.bitmap = bitmap;
		}

		public Task() {
		}

	}

	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	private Handler handler;
	private Thread workThread;
	private boolean isLoop;
	private ArrayList<Task> tasks;
	private HashMap<String, SoftReference<Bitmap>> caches;//

	public MusicAdapter(Context context, ArrayList<Music> musics, final ListView lvMusics) {
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
		this.caches = new HashMap<String, SoftReference<Bitmap>>();

		this.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:// ͼƬ�������
						// ��ȡ������������
					Task task = (Task) msg.obj;
					// ����ͼƬ·��Ϊtag����listview�в���imageView
					ImageView iv = (ImageView) lvMusics.findViewWithTag(task.path);
					// �����imageviewδ�����ã�����iamgeview����ʾͼƬ
					if (iv != null && task.bitmap != null) {
						iv.setImageBitmap(task.bitmap);
					}
					break;
				}

			}
		};
		this.isLoop = true;
		this.tasks = new ArrayList<MusicAdapter.Task>();
		this.workThread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isLoop) {
					// ������񼯺ϲ�Ϊ��,��������
					while (tasks.size() > 0 && isLoop) {
						try {
							// ��ȡ���Ƴ���һ������
							Task task = tasks.remove(0);
							// ����ͼƬ
							HttpEntity entity = HttpUtils.getEntity(HttpUtils.BASE_URL + task.path, null, HttpUtils.METHOD_GET);
							byte[] data = EntityUtils.toByteArray(entity);
							task.bitmap = BitmapUtils.loadBitmap(data, 100, 100);
							// �򻺴漯�������ͼƬ
							caches.put(task.path, new SoftReference<Bitmap>(task.bitmap));
							// ���ļ��������ͼƬ
							BitmapUtils.save(task.bitmap, "/mnt/sdcard/" + task.path);
							
							// ������Ϣ�����߳�
							Message msg = Message.obtain(handler, 0, task);
							msg.sendToTarget();
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (!isLoop)
						break;

					// �̵߳ȴ�
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		this.workThread.start();
	}

	public void quit() {
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
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

		// 1.������漯���ڸ�·����ͼƬ����ֱ����ʾͼƬ��
		// 2.��� sd������ ��Ӧ��ͼƬ����ô ����sd�����ͼƬ
		// 3.�����������������ʾĬ��ͼƬ
		Bitmap bm = null;
		if (caches.containsKey(path)) {//
			holder.ivAlbum.setImageBitmap(caches.get(path).get());
		} else if((bm=BitmapUtils.loadBitmap("/mnt/sdcard/"+path))!=null){
			holder.ivAlbum.setImageBitmap(bm);
		}else {
			// ����Ĭ��ͼƬ
			holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
			// ������������񼯺ϣ����ѹ����߳̿�ʼ����ͼƬ
			Task task = new Task(music.getAlbumPath(), null);
			tasks.add(task);
			synchronized (workThread) {
				workThread.notify();
			}
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
