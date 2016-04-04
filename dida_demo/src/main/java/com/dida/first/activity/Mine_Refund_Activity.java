/**
 * 
 */
package com.dida.first.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanRefund;
import com.dida.first.holder.BaseHolder;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * @author		KingJA 
 * @data		2015-9-29 下午1:47:40 
 * @use			
 *
 */
public class Mine_Refund_Activity extends BackTitleActivity {

	private PullToRefreshListView lv_mine_refund;
	private List<BeanRefund> refundList=new ArrayList<BeanRefund>();

	@Override
	public View setView() {
		view=View.inflate(Mine_Refund_Activity.this, R.layout.activity_mine_refund, null);
		return view;
	}

	@Override
	public void initView() {
		lv_mine_refund = (PullToRefreshListView) view.findViewById(R.id.lv_mine_refund);

	}

	@Override
	public void initDoNet() {
		for (int i = 0; i < 12; i++) {
			refundList.add(new BeanRefund());
		}

	}

	@Override
	public void initEvent() {
		lv_mine_refund.setAdapter(new MyAdapter(refundList));

	}

	@Override
	public void initData() {
		setBackTitle("退款/售后");

	}

	@Override
	public void onChildClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackClick() {
		finish();

	}
	class MyAdapter extends MyBaseAdapter<BeanRefund>{
		public MyAdapter(List<BeanRefund> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanRefund> getHolder() {
			// TODO Auto-generated method stub
			return new Refund_Holder(Mine_Refund_Activity.this);
		}
		
	}

}
