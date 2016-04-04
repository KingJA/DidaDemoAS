package com.dida.first.holder;

import android.view.View;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-14 上午9:54:08
 * @use
 */

public abstract class BaseHolder<T> {
    protected View view;
    public boolean b;
    public int position;
    private T mData;
    private List<T> mList;

    public BaseHolder() {
        view = initView();
        view.setTag(this);
    }


    public void setBoolean(boolean b) {
        this.b = b;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract View initView();

    public abstract void refreshView();

    public void setData(T mData) {
        this.mData = mData;
        refreshView();
    }

    public void setList(List<T> mList) {
        this.mList = mList;
        refreshView();
    }

    public T getData() {
        return mData;
    }

    public List<T> getList() {
        return mList;
    }

    public View getRootView() {
        return view;
    }
}