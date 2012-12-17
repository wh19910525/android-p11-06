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
import android.util.Log;
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
	private boolean isLoop;
	private ArrayList<String> tasks;
	private Thread workThread;

	public MusicAdapter(Context context, ArrayList<Music> musics, ListView lvMusics) {
		this.setMusics(musics);
		this.inflater = LayoutInflater.from(context);
		this.lvMusics = lvMusics;
		this.handler = new Handler() {//这里没有参数
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 0:// 图片加载完成
					Bitmap bm = (Bitmap) msg.obj;
					String path = msg.getData().getString("path");
					ImageView iv = (ImageView) MusicAdapter.this.lvMusics.findViewWithTag(path);
					// 如果该imageview未被复用，则在iamgeview上显示图片
					if (bm != null && iv != null) {
						iv.setImageBitmap(bm);
					}
					break;
				}
			};
		};

		this.isLoop = true;
		this.tasks = new ArrayList<String>();//
		
		this.workThread = new Thread() {
			@Override
			public void run() {
				while (isLoop) {
					// 如果任务集合有任务，则循环执行这些任务
					while (isLoop && tasks.size() > 0) {
						// 获取并从集合中移除第一条任务
						String path = tasks.remove(0);//
						// 加载
						try {
							// 获取专辑图片的路径
							String uri = HttpUtils.BASE_URL + path;
							HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
							byte[] data = EntityUtils.toByteArray(entity);
							Bitmap bm = BitmapUtils.loadBitmap(data, 100, 100);

							// 发送消息回主线程
							Message msg = Message.obtain();
							msg.what = 0;
							Bundle bundle = new Bundle();
							bundle.putString("path", path);
							msg.setData(bundle);
							msg.obj = bm;

							handler.sendMessage(msg);
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (isLoop == false)
						break;

					// 如果所有任务都执行完成，则线程等待
					synchronized (this) {
						try {
							this.wait();//
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

	/**
	 * 结束工作线程
	 */
	public void quit() {
		isLoop = false;
		synchronized (workThread) {//启动线程
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
	 * 更新adapter中的数据集
	 * 
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics) {
		this.setMusics(musics);
		this.notifyDataSetChanged();// 更新 adapter
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
			holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
			holder.tvSinger = (TextView) convertView.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		 Music music = musics.get(position);

		// 绑定到界面
		holder.tvName.setText(music.getName());
		holder.tvDuration.setText(music.getDuration());
		holder.tvAlbum.setText(music.getAlbum());
		holder.tvSinger.setText(music.getSinger());

		// 设置音乐专辑路径为imageview对象的tag
		holder.ivAlbum.setTag(music.getAlbumPath());//
		// 设置默认图片
		holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
		// 将图片路径添加到任务集合
		tasks.add(music.getAlbumPath());
		
		// 唤醒线程开始加载
		synchronized (workThread) {//他 会 调用 线程？
			workThread.notify();
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
