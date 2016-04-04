package com.dida.first.utils;

/**
 * Created by KingJA on 2015-12-30.
 */
public class SizeUtil {
    public enum ImgSize{
        COMMENT
    }
    public static int getSize(ImgSize imgSize){
        int size=0;
        switch (imgSize){
            case COMMENT:
                size=(UIUtils.getScreenWidth()-UIUtils.dip2px(10)*3-UIUtils.dip2px(36)-UIUtils.dip2px(4)*6)/5;
                break;
            default:
                break;

        }
        return size;
    }
}
