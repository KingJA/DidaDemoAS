package com.dida.first.utils;

import android.os.Environment;

import com.dida.first.application.App;

public class CustomConstants {

	// SharedPreferences文件名称
	public static final String APPLICATION_NAME = "APPLICATION_NAME";
	// 随机名字文件名
	public static final String RANDOMNAMES_FILE = "name.txt";
	// 敏感词汇文件名
	public static final String MINGANWORDS_FILE = "mingan.txt";
	// SP中是否登录标记
	public static final String HASLOGIN = "HASLOGIN";
	// 单次最多发送图片数
	public static final int MAX_IMAGE_SIZE = 9;
	// 首选项:临时图片
	public static final String PREF_TEMP_IMAGES = "PREF_TEMP_IMAGES";
	// 享购页面缓存
	public static final String YAOYUE_GROUP_CACHE = "YAOYUE_GROUP_CACHE";
	// 用户昵称
	public static final String USER_NAME = "USER_NAME";
	// 用户ID
	public static final String USER_ID = "USER_ID";
	// 图片缓存地址
	public static final String CACHE_IMG = App.getContext()
			.getExternalCacheDir() + "/ImgCache";
	public static final String CACHE_ROOT_URL =Environment.getExternalStorageDirectory().getPath() + "/aamai/";

}
