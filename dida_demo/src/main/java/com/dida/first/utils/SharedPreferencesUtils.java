package com.dida.first.utils;


import android.content.SharedPreferences;

import com.dida.first.application.App;

public class SharedPreferencesUtils {
	private static SharedPreferences sharedPreferences=App.getSP();
	//sava
	public static SharedPreferences saveStringData(String key, String value) {
		sharedPreferences.edit().putString(key, value).commit();
		return sharedPreferences;
	}

	public static SharedPreferences saveIntData(String key, int value) {
		sharedPreferences.edit().putInt(key, value).commit();
		return sharedPreferences;
	}

	public static SharedPreferences saveBooleanData(String key, boolean value) {
		sharedPreferences.edit().putBoolean(key, value).commit();
		return sharedPreferences;
	}
	
	//get
	public static String getStringData(String key,String defValue){
		return sharedPreferences.getString(key, defValue);
	}
	public static int getIntData(String key,int defValue){
		return sharedPreferences.getInt(key, defValue);
	}
	public static boolean getBooleanData(String key,boolean defValue){
		return sharedPreferences.getBoolean(key, defValue);
	}
	//delete
	public static boolean removeData(String key){
		return sharedPreferences.edit().remove(key).commit();
	}
	//clear
	public static boolean clearData(String key){
		return sharedPreferences.edit().clear().commit();
	}
}
