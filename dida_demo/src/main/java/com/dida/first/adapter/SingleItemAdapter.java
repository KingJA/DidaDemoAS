/**
 * 
 */
package com.dida.first.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author		KingJA 
 * @data		2015-9-10 下午1:01:47 
 * @use			
 *
 */
public abstract class SingleItemAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getSingleView( position,  convertView,  parent);
	}

	public abstract View getSingleView(int position, View convertView, ViewGroup parent);

}
