package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.List;

public abstract class BaseLvGvAdapter<T>  extends BaseAdapter{
    protected RelativeLayout.LayoutParams layoutParams;
    protected List<T> list;
    protected Activity activity;
    protected int imgWidth= RelativeLayout.LayoutParams.MATCH_PARENT;
    protected int imgHeight=RelativeLayout.LayoutParams.MATCH_PARENT;

    public BaseLvGvAdapter(List<T> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        layoutParams = new RelativeLayout.LayoutParams(imgWidth, imgHeight);
    }

    protected void setImgSize(int width,int height){
        imgWidth= width;
        imgHeight = height;
        layoutParams = new RelativeLayout.LayoutParams(imgWidth, imgHeight);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addData(List<T> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setData(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      return baseGetView( position, convertView, parent);
    }

    protected abstract View baseGetView(int position, View convertView, ViewGroup parent);

}
