package com.dida.first.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dida.first.application.App;

/**
 * Created by KingJA on 2016-1-26.
 */
public class AppInfoUtil {
    private static Context context = App.getContext();

    private static PackageInfo getAppInfo() {
        PackageInfo packageInfo=null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static String getPackageName() {
        return context.getPackageName();
    }
    public static String getVersionName() {
        return getAppInfo().versionName;
    }

    public static int getVersionCode() {
        return  getAppInfo().versionCode;
    }

}
