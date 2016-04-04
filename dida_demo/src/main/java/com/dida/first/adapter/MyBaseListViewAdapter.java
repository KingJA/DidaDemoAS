package com.dida.first.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseListViewAdapter<T> extends BaseAdapter {
	protected List<T> list;
	protected View view;
	public MyBaseListViewAdapter(List<T>list) {
		this.list=list;
	}
	public void setNotyfyData(List<T> list){
		this.list=list;
		this.notifyDataSetChanged();
	}
	public List<T> getList(){
			return list;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view=getItemView(position,convertView,parent);
		return view;
	}

	public abstract View getItemView(int position, View convertView, ViewGroup parent);

}
