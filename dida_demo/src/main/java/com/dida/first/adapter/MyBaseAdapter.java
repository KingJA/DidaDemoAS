/**
 * 
 */
package com.dida.first.adapter;

import java.util.List;

import com.dida.first.holder.BaseHolder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author		KingJA 
 * @data		2015-8-14 上午9:56:29 
 * @use			
 *
 */
public abstract class MyBaseAdapter <T>extends BaseAdapter {
	public boolean b;
	protected int position;
	private BaseHolder<T> holder;
	protected List<T> list;
	public void setBoolean(boolean b){
		this.b=b;
	}
	public void setNotyfyData(List<T> list){
		this.list=list;
		b=false;
		this.notifyDataSetChanged();
	}
	public MyBaseAdapter(List<T> list) {
		this.list=list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public int getPosition(){
		return position;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		this.position=position;
		if (convertView==null) {
			holder=getHolder();
		}else {
			if (convertView.getTag()!=null) {
				holder=(BaseHolder<T>) convertView.getTag();
			}
		}
		holder.setBoolean(b);
		holder.setPosition(position);
		holder.setData(list.get(position));
		return holder.getRootView();
	}
	//根据不同的子界面需求，生成相应的holder
		public abstract BaseHolder<T> getHolder() ;
}
