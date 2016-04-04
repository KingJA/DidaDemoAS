package com.dida.first.utils;

import android.net.Uri;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by KingJA on 2016-1-11.
 * \Fresco显示工具类
 */
public class FrescoManager {
    private static final String TAG = "FrescoManager";

    public static void display(String url, SimpleDraweeView simpleDraweeView){
        Uri parse = Uri.parse(UrlUtil.getImgUrl(url));
        Log.i(TAG, "display: "+UrlUtil.getImgUrl(url));
        simpleDraweeView.setImageURI(parse);
    }
}
