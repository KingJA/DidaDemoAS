package com.dida.first.activity;

import com.dida.first.R;

import android.view.View;

/**
 * @author		KingJA 
 * @data		2015-10-10 下午2:33:27 
 * @use			
 *
 */
public class Setting_Report_Activity extends BackTitleActivity {

	@Override
	public View setView() {
		view=View.inflate(Setting_Report_Activity.this, R.layout.activity_setting_report, null);
		return view;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initDoNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		setBackTitle("意见反馈");
	}

	@Override
	public void onChildClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackClick() {
		finish();

	}

}
