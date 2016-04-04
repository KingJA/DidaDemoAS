/**
 * 
 */
package com.dida.first.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanStores;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.Mine_Store_Holder;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author		KingJA 
 * @data		2015-10-13 下午4:44:05 
 * @use			我收藏的商家
 *
 */
public class Mine_FavStore_Activity extends BackTitleActivity implements OnItemClickListener {
	List<BeanStores> storeList=new ArrayList<BeanStores>();
	private PullToRefreshListView plv_store;

	@Override
	public View setView() {
		view=View.inflate(Mine_FavStore_Activity.this, R.layout.activity_mine_store, null);
		return view;
	}

	@Override
	public void initView() {
		plv_store = (PullToRefreshListView) view.findViewById(R.id.plv_store);

	}

	@Override
	public void initDoNet() {
		for (int i = 0; i < 20; i++) {
			storeList.add(new BeanStores());
		}

	}

	@Override
	public void initEvent() {
		plv_store.setAdapter(new MyAdapter(storeList));
		plv_store.setOnItemClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("收藏的店铺");
		

	}

	@Override
	public void onChildClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackClick() {
		finish();

	}
	class MyAdapter extends MyBaseAdapter<BeanStores>{

		/**
		 * @param list
		 */
		public MyAdapter(List<BeanStores> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanStores> getHolder() {
			// TODO Auto-generated method stub
			return new Mine_Store_Holder();
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent  intent=new Intent(Mine_FavStore_Activity.this,Store_Activity.class);
		startActivity(intent);
		
	}
	
}
