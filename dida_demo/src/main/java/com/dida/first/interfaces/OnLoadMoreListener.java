package com.dida.first.interfaces;

/**
 * Created by KingJA on 2016-1-28.
 */
public interface OnLoadMoreListener<T> {
    void onLoadMoreSuccess(T data);
    void onLoadMoreFailure(Exception e);
}
