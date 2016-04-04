package com.dida.first.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageCacheUtil {
	private static final String tag = "ImageCacheUtil";
	//�ɹ�״̬
	public static final int SUCCESS = 100;
	//ʧ��״̬
	public static final int FAIL = 101;
	
	private LruCache<String, Bitmap> lruCache;
	private File cacheDir;
	private Handler handler;
	private ExecutorService newFixedThreadPool;
	
	public ImageCacheUtil(Context context,Handler handler) {
		this.handler = handler;
		int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
		//��ȡcache�ļ���
		cacheDir = context.getCacheDir();
		//ά���߳���Ϊ5�����̳߳�
		newFixedThreadPool = Executors.newFixedThreadPool(5);
		lruCache = new LruCache<String,Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				//��ǰ��Ӧkey��ͼƬ�ĵĴ�С��ͼƬ��С������
				//ÿһ��ռ�ô�С*������
				return value.getRowBytes()*value.getHeight();
			}
		};
	}
	//��ȡͼƬ�ķ���,ͼƬ����������ַ����ȡ����ͼƬӦ����ʾ���Ǹ�����tag�Ŀؼ�
	public Bitmap getBitmap(String url,int position){
		//1,�ڴ棨<key,value>���ڴ������LRU��
		Bitmap bitmap = null;
		bitmap = lruCache.get(url);
		if(bitmap!=null){
			Log.i(tag, "���ڴ��л�ȡ������ͼƬ");
			return bitmap;
		}
		//2,�ļ�
		bitmap = getBitmapFromLocal(url);
		if(bitmap!=null){
			Log.i(tag, "���ļ��л�ȡ������ͼƬ");
			return bitmap;
		}
		//3,���磬����������Ĳ�������ʱ�����̣߳�Bitmap��������Ϣhandler
		getBitmapFromNet(url,position);
		Log.i(tag, "�������л�ȡ��ͼƬ");
		return null;
	}
	
	private void getBitmapFromNet(String url, int position) {
		newFixedThreadPool.execute(new RunnablTask(url,position));
	}
	
	class RunnablTask implements Runnable{
		private String imageUrl;
		private int position;
		public RunnablTask(String imageUrl, int position) {
			this.imageUrl = imageUrl;
			this.position = position;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(imageUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				//���ӷ�����ʱ��5�볬ʱ
				connection.setConnectTimeout(5000);
				//��ȡ����5���ӳ�ʱ
				connection.setReadTimeout(5000);
				connection.setRequestMethod("POST");
				//
				InputStream inputStream = connection.getInputStream();
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				
				Message message = new Message();
				message.what = SUCCESS;
				message.obj = bitmap;
				//����tagֵ
				message.arg1 = position;
				handler.sendMessage(message);
				
				//����ͼƬ
				//�ڴ�
				lruCache.put(imageUrl, bitmap);
				//�ļ�
				writeBitmapToLocal(imageUrl,bitmap);
				return ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Դ�����γ�
			handler.obtainMessage(FAIL).sendToTarget();
		}
	}
	//��ȡͼƬ
	private Bitmap getBitmapFromLocal(String url) {
		String fileName;
		try {
			fileName = MD5Encoder.getMd5(url).substring(10);
			File file = new File(cacheDir, fileName);
			Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
			//�����ڴ�
			lruCache.put(url, bitmap);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void writeBitmapToLocal(String imageUrl, Bitmap bitmap) {
		String fileName;
		try {
			fileName = MD5Encoder.getMd5(imageUrl).substring(10);
			File file = new File(cacheDir, fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
			bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
