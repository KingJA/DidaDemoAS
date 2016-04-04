package com.dida.first.adapter;

import java.util.List;

import com.dida.first.R;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftSindingMenuAdapter extends BaseAdapter {
	private List list;
	private Context context;
	public LeftSindingMenuAdapter(Context context,List list) {
		this.list=list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
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
		ViewHolder viewHolder=null;
		if (convertView==null) {
			viewHolder=new ViewHolder();
			convertView=View.inflate(context, R.layout.item_left_slidingmenu, null);
			viewHolder.iv_left_slidingmenu=(ImageView) convertView.findViewById(R.id.iv_left_slidingmenu);
			viewHolder.tv_left_slidingmenu=(TextView) convertView.findViewById(R.id.tv_left_slidingmenu);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.iv_left_slidingmenu.setBackgroundResource(R.drawable.xiangg);
		String title=(String) list.get(position);
		viewHolder.tv_left_slidingmenu.setText(title);
		return convertView;
	}
	class ViewHolder{
		ImageView iv_left_slidingmenu;
		TextView tv_left_slidingmenu;
		
	}

}
