package com.dida.first.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.dida.first.application.App;

public class UIUtils {

	public static boolean getHasLogin() {
		return App.getHasLogin();
	}
	public static SharedPreferences getSP() {
		return App.getSP();
	}

	public static Context getContext() {
		return App.getContext();
	}

	public static int getMainThreadId() {
		return App.getMainThreadId();
	}

	public static Thread getMainThread() {
		return App.getMainThread();
	}

	public static Handler getHandler() {
		return App.getHandler();
	}

	// string
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	// drawable
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	// stringArray
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	// dip--->px 1dp = 1px 1dp = 2px
	public static int dip2px(int dip) {
		// dp和px的转换关系比例值
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * density + 0.5);
	}

	// px---->dp
	public static int px2dip(int px) {
		// dp和px的转换关系比例值
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5);
	}
	public static int getScreenWidth() { 
	    WindowManager manager = (WindowManager) getContext() 
	            .getSystemService(Context.WINDOW_SERVICE); 
	    Display display = manager.getDefaultDisplay();


	    return display.getWidth();
	}
	public static int getScreenHeight() { 
		WindowManager manager = (WindowManager) getContext() 
				.getSystemService(Context.WINDOW_SERVICE); 
		Display display = manager.getDefaultDisplay(); 
		return display.getHeight(); 
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
	public static float getScreenRatio() { 
		return getScreenHeight()*1.0f/getScreenWidth(); 
	}

	//设定颜色
	public static int getTextColor( int color) {
		return ContextCompat.getColor(getContext(), color);
	}
	// 判断是否是主线的方法
	public static boolean isRunInMainThread() {
		return getMainThreadId() == android.os.Process.myTid();
	}

	// 保证当前的UI操作在主线程里面运行
	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			// 如果现在就是在珠现场中，就直接运行run方法
			runnable.run();
		} else {
			// 否则将其传递到主线程中运行
			getHandler().post(runnable);
		}
	}

	// java代码区设置颜色选择器的方法
	public static ColorStateList getColorStateList(int mTabTextColorResId) {
		return getContext().getResources()
				.getColorStateList(mTabTextColorResId);
	}

	public static View inflate(int id) {
		return View.inflate(getContext(), id, null);
	}

	public static int getDimens(int id) {
		// 根据dimens中提供的id，将其对应的dp值转换成相应的像素值大小
		return UIUtils.getContext().getResources().getDimensionPixelSize(id);
	}

	public static void postDelayed(Runnable runnable, long delayTime) {
		getHandler().postDelayed(runnable, delayTime);
	}

	public static void removeCallback(Runnable runnable) {
		// 移除在当前handler中维护的任务(传递进来的任务)
		getHandler().removeCallbacks(runnable);
	}

	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}
	public static final int NETTYPE_WIFI = 1;
	public static final int NETTYPE_CMWAP = 2;
	public static final int NETTYPE_CMNET = 3;

	public static int  getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!TextUtils.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}
}
