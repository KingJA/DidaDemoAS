/**
 * 
 */
package com.dida.first.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dida.first.factory.ActivityFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author KingJA
 * @data 2015-8-25 下午4:46:31
 * @use
 * 
 */
public class ImageUtils {
	private static String newPath;

	// 思路如下：
	// 首先判断SD卡是否插入-->
	public static String getSDPath() {
		File SDdir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			SDdir = Environment.getExternalStorageDirectory();
		}
		if (SDdir != null) {
			return SDdir.toString();
		} else {
			return null;
		}
	}

	// 然后创建文件夹-->
	public static void createSDCardDir() {
		if (getSDPath() == null) {
			Toast.makeText(UIUtils.getContext(), "未找到SD卡", 0).show();
		} else {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				// 创建一个文件夹对象，赋值为外部存储器的目录
				File sdcardDir = Environment.getExternalStorageDirectory();
				newPath = sdcardDir.getPath() + "/aamai/aamaiImages/";
				File path1 = new File(newPath);
				if (!path1.exists()) {
					// 若不存在，创建目录，可以在应用启动的时候创建
					path1.mkdirs();
					System.out.println("paht ok,path:" + newPath);
				}
			} else {
				System.out.println("false");
			}
		}
	}

	// 创建好文件夹之后就可以保存图片了-->
	public static void saveMyBitmap(String bitName, int percent,
			ImageView picView) throws IOException {
		createSDCardDir();
		Bitmap bmp = drawable2Bitmap(picView.getDrawable());// 这里的drawable2Bitmap方法是我把ImageView中
															// 的drawable转化成bitmap，当然实验的时候可以自己创建bitmap
		File f = new File(newPath + bitName + ".jpg");
		Log.i("newPath", f.toString());
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean compress = bmp.compress(Bitmap.CompressFormat.JPEG, percent,
				fOut);
		if (compress) {
			ToastUtil.showMyToast( "保存成功");
		}
		/**
		 * 刷新相册
		 */
		MediaScannerConnection.scanFile(ActivityFactory.mainActivity, new String[] { f.toString()
				 }, null, null);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 附加drawable2Bitmap方法
	public static Bitmap drawable2Bitmap(Drawable d) {
		int width = d.getIntrinsicWidth();
		int height = d.getIntrinsicHeight();
		Bitmap.Config config = d.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, width, height);
		d.draw(canvas);
		return bitmap;
	}
}
