package com.dida.first.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.dida.first.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by KingJA on 2015-11-30.
 */
public class UImageLoaderUitl {
    private static ImageLoader mImageLoader=ImageLoader.getInstance();
    private static DisplayImageOptions mSmallOptions;
    private static DisplayImageOptions mMidLvOptions;
    private static DisplayImageOptions mMidGvOptions;

    static {
        mSmallOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_head_72)
                .showImageForEmptyUri(R.drawable.default_head_72)
                .showImageOnFail(R.drawable.default_head_72)
                .cacheInMemory(false)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
        mMidGvOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_img)
//                .showImageForEmptyUri(R.drawable.default_head_72)
//                .showImageOnFail(R.drawable.default_head_72)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
        mMidLvOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_img_large)
//                .showImageForEmptyUri(R.drawable.default_img)
//                .showImageOnFail(R.drawable.default_img)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .delayBeforeLoading(100)//载入图片前稍做延时可以提高整体滑动的流畅度
                .build();
    }

    /**
     * 加载小图
     * @param view
     * @param url
     */
    public static void displaySmallImage(String url,ImageView view) {
        if(!url.startsWith("http")){
            url=UrlUtil.HOST+url;
        }
        mImageLoader.displayImage(url, view, mSmallOptions);
    }

    /**
     * ListView加载中图
     * @param imageView
     * @param url
     */
    public static void displayLvMidImage(String url,ImageView imageView) {
        if(!url.startsWith("http")){
            url=UrlUtil.HOST+url;
        }
        mImageLoader.displayImage(url, imageView, mMidLvOptions);
    }

    /**
     * GistView加载中图
     *
     * @param imageView
     * @param url
     */
    public static void displayGvMidImage(String url,ImageView imageView) {
        if (!url.startsWith("http")) {
            url = UrlUtil.HOST + url;
        }
        mImageLoader.displayImage(url, imageView, mMidGvOptions);
    }
    public static ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
