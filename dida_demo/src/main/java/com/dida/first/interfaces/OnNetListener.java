package com.dida.first.interfaces;

import com.lidroid.xutils.exception.HttpException;

/**
 * Created by Administrator on 2015-11-23.
 */
public interface OnNetListener<T> {
    void onSuccess(T t);
    void onFail(HttpException error, String msg);
}
