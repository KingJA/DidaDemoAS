package com.dida.first.baseactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public abstract class LeftSlidingMenuBaseActivity extends Activity {
	public String title;
	public View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view=mSetContentView();
		setContentView(view);
//		getMyTitle();
		initView();
		initData();
		initListener();
	}

	public abstract View mSetContentView();

	private void getMyTitle() {
	Intent intent=getIntent();
	String title = intent.getStringExtra("activity_title");
	this.title=title;
	}

	public abstract void initListener();

	public abstract void initData();

	public abstract void initView();

}
