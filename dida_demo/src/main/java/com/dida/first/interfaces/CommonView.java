package com.dida.first.interfaces;

/**
 * Created by KingJA on 2016-1-19.
 */
public interface CommonView<T> {
    void showProgress();
    void hideProgress();
    void setData(T data);
    void addData(T data);
    void showError();
}
