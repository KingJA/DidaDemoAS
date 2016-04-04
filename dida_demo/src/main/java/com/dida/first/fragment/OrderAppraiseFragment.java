package com.dida.first.fragment;

import android.view.View;
import android.widget.TextView;


public class OrderAppraiseFragment extends Base4Fragment {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public View initFragmentView() {
		TextView tv=new TextView(context);
		tv.setText("待评价");
		return tv;
	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doNet() {
		// TODO Auto-generated method stub

	}

}
