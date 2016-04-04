package com.dida.first.minepingou.presenter;

/**
 * Created by KingJA on 2016-1-27.
 */
public interface OnLoadNetListener<T> {
    void onLoadNetSuccess(T data);
    void onLoadNetErr(Exception e);
}
