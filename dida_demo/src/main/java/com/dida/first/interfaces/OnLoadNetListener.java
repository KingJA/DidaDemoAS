package com.dida.first.interfaces;

/**
 * Created by KingJA on 2016-1-28.
 */
public interface OnLoadNetListener<T>  {
    void onLoadNetSuccess(T data);
    void onLoadNetFailure(Exception e);
}
