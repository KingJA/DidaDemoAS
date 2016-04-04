package com.dida.first.activity;

import com.dida.first.R;

import android.view.View;

/**
 * @author		KingJA 
 * @data		2015-10-9 下午1:11:36 
 * @use			
 *
 */
public class Setting_About_Activity extends BackTitleActivity {

	@Override
	public View setView() {
		view=View.inflate(Setting_About_Activity.this, R.layout.activity_setting_about, null);
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
		setBackTitle("关于我们");

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
