package com.dida.first.activity;

import android.view.View;

import com.dida.first.R;

/**
 * Created by Administrator on 2015-11-16.
 */
public class Personal_Detail_Activity extends BackTitleActivity {
    @Override
    public View setView() {
        view=View.inflate(Personal_Detail_Activity.this, R.layout.activity_personal_detail,null);
        return view;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initDoNet() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        setBackTitle("个人详情");

    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setBackClick() {
        finish();
    }
}
