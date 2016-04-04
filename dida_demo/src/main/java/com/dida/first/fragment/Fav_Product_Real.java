package com.dida.first.fragment;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanProduct;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.Product_Real_Holder;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-10-16 下午1:22:00 
 * @use			
 *
 */
public class Fav_Product_Real extends Base_First_Fragment {

	private PullToRefreshGridView mgv_proucet_real;
	private List<BeanProduct> productList=new ArrayList<BeanProduct>();

	@Override
	public View setFragmentView() {
		view=View.inflate(context, R.layout.fragment_product_real, null);
		return view;
	}

	@Override
	public void initFragmentView() {
		mgv_proucet_real = (PullToRefreshGridView) view.findViewById(R.id.mgv_proucet_real);
		mgv_proucet_real.setAdapter(new MyAdapter(productList));
	}

	@Override
	public void initFragmentNet() {
		for (int i = 0; i <20; i++) {
			productList.add(new BeanProduct());
		}

	}

	@Override
	public void initFragmentEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildClick(View v) {

	}

	@Override
	public void setMyRetryEvent(View retryView) {

	}

	class MyAdapter extends MyBaseAdapter<BeanProduct>{
		public MyAdapter(List<BeanProduct> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanProduct> getHolder() {
			// TODO Auto-generated method stub
			return new Product_Real_Holder();
		}
		
	}

}
