package com.dida.first.holder;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanFootPrint;
import com.dida.first.entity.BeanFootPrint.ItemFootPrint;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyGridView;

/**
 * @author		KingJA 
 * @data		2015-9-24 下午3:49:41 
 * @use			
 *
 */
public class Foot_Real_Holder extends BaseHolder<BeanFootPrint> implements OnCheckedChangeListener{

	private MyGridView mgv_foot_real;
	private int outPosition;
	public Foot_Real_Holder(int outPosition) {
	this.outPosition=outPosition;
	}
	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.holder_foot_real);
		return view;
	}

	@Override
	public void refreshView() {
		BeanFootPrint beanFootPrint = getData();
		cb_foot_real = (CheckBox) view.findViewById(R.id.cb_foot_real);
		cb_foot_real.setOnCheckedChangeListener(this);
		cb_foot_real
		.setVisibility(beanFootPrint.ifShow == true ? View.VISIBLE
				: View.GONE);
		mgv_foot_real = (MyGridView) view.findViewById(R.id.mgv_foot_real);
		mgv_foot_real.setOnItemClickListener(onItemClickListener);
		List<ItemFootPrint> itemFootPrintList = beanFootPrint.itemFootPrintList;
		Log.i("itemFootPrintList", itemFootPrintList.size()+"");
		myAdapter = new MyAdapter(itemFootPrintList);
		mgv_foot_real.setAdapter(myAdapter);

	}
	private OnItemClickListener onItemClickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ToastUtil.showMyToast(outPosition+"||"+position);
			
		}
	};
	private CheckBox cb_foot_real;
	private MyAdapter myAdapter;
	class MyAdapter extends MyBaseAdapter<ItemFootPrint>{

		public MyAdapter(List<ItemFootPrint> list) {
			super(list);
		}

		@Override
		public BaseHolder<ItemFootPrint> getHolder() {
			return new Foot_Real_GV_Holder();
		}
		public void checkAll(boolean ifCheckAll){
				for (ItemFootPrint itemFootPrint : list) {
					itemFootPrint.ifCheck=ifCheckAll;
				}
				notifyDataSetChanged();
		}
		
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		myAdapter.checkAll(isChecked);
		
	}

}
