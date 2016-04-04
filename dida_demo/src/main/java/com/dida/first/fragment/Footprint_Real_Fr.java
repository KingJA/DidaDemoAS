/**
 * 
 */
package com.dida.first.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.BeanFootPrint;
import com.dida.first.entity.BeanFootPrint.ItemFootPrint;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.Foot_Real_Holder;
import com.dida.first.utils.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import android.view.View;
import android.widget.ImageButton;

/**
 * @author KingJA
 * @data 2015-9-24 下午2:16:25
 * @use
 * 
 */
public class Footprint_Real_Fr extends FragmentBaseFoot {
	private List<BeanFootPrint> footPrintList=new ArrayList<BeanFootPrint>();
	private PullToRefreshListView lv_foot_real;
	private ImageButton ib_foot_real_button;
	private boolean isOpen;
	private MyAdapter myAdapter;
	@Override
	public View initCreateView() {
		view=View.inflate(context, R.layout.fragment_foot_real, null);
		return view;
	}

	@Override
	public void initFragmentView() {
		lv_foot_real = (PullToRefreshListView) view.findViewById(R.id.lv_foot_real);
		ib_foot_real_button = (ImageButton) view.findViewById(R.id.ib_foot_real_button);
		
	}

	@Override
	public void initFragmentNet() {
		for (int i = 0; i < 10; i++) {
			BeanFootPrint beanFootPrint = new BeanFootPrint();
			beanFootPrint.itemFootPrintList= new ArrayList<ItemFootPrint>();
			for (int j = 0; j < new Random().nextInt(6)+3; j++) {
				beanFootPrint.itemFootPrintList.add(beanFootPrint.new ItemFootPrint());
			}
			footPrintList.add(beanFootPrint);
		}
		
	}

	@Override
	public void initFragmentEvent() {
		myAdapter = new MyAdapter(footPrintList);
		lv_foot_real.setAdapter(myAdapter);
		ib_foot_real_button.setOnClickListener(this);
	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub
		
	}
	class MyAdapter extends MyBaseAdapter<BeanFootPrint>{

		public MyAdapter(List<BeanFootPrint> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<BeanFootPrint> getHolder() {
			// TODO Auto-generated method stub
			return new Foot_Real_Holder(position);
		}
		public void showDelete(boolean ifShow){
			for (BeanFootPrint beanFootPrint : list) {
				beanFootPrint.ifShow=ifShow;
				List<ItemFootPrint> itemFootPrintList = beanFootPrint.itemFootPrintList;
				for (ItemFootPrint itemFootPrint : itemFootPrintList) {
					itemFootPrint.ifShow=ifShow;
				}
			}
			notifyDataSetChanged();
		}
		
	}
	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.ib_foot_real_button:
			if (!isOpen) {
				ToastUtil.showMyToast("开始删除");
				myAdapter.showDelete(true);
			}else {
				ToastUtil.showMyToast("删除完成");
				myAdapter.showDelete(false);
			}
			
			ib_foot_real_button.setBackgroundResource(isOpen==false?R.drawable.delete_open:R.drawable.delete_close);
			isOpen=(isOpen==false?true:false);
			
			break;

		default:
			break;
		}
		
	}


}
