package com.dida.first.interfaces;

/**
 * Created by Administrator on 2015-11-5.
 */
public interface OnCommonResponseListener<T> {
    void onSuccess(T mData);
    void onFailure(Exception e);
}
