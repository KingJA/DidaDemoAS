/**
 * 
 */
package com.dida.first.activity;

import com.dida.first.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-9-7 上午10:00:02
 * @use
 * 
 */
public class ShaiDanSelectActivity extends BackTitleActivity {

	private View inflate;
	private RelativeLayout rl_info_aa;
	private RelativeLayout rl_info_group;
	private ImageView iv_info_group;
	private ImageView iv_info_aa;
	private TextView tv_info_aa;
	private TextView tv_info_group;
	private Button btn_info_confirm;
	private LinearLayout ll_aa_info;
	private LinearLayout ll_group_info;

	@Override
	public void setBackClick() {
		finish();
	}
	public void reSet(){
		iv_info_aa.setVisibility(View.GONE);
		iv_info_group.setVisibility(View.GONE);
		ll_aa_info.setVisibility(View.GONE);
		ll_group_info.setVisibility(View.GONE);
		tv_info_aa.setTextColor(getResources().getColor(R.color.black));
		tv_info_group.setTextColor(getResources().getColor(R.color.black));
	}
	@Override
	public void onChildClick(View v) {
		
		switch (v.getId()) {
		case R.id.rl_info_group:
			reSet();
			iv_info_group.setVisibility(View.VISIBLE);
			ll_group_info.setVisibility(View.VISIBLE);
			tv_info_group.setTextColor(getResources().getColor(R.color.red));
			break;
		case R.id.rl_info_aa:
			reSet();
			iv_info_aa.setVisibility(View.VISIBLE);
			ll_aa_info.setVisibility(View.VISIBLE);
			tv_info_aa.setTextColor(getResources().getColor(R.color.red));
			
			break;
		case R.id.btn_info_confirm:
			//TODO 发布拼购的逻辑
		Intent intent=new Intent(this,BuyActivity.class);
		startActivity(intent);
		finish();
			break;

		default:
			break;
		}

	}

	@Override
	public View setView() {
		inflate = View.inflate(this, R.layout.activity_shaidan_select, null);
		return inflate;
	}

	@Override
	public void initView() {
		ll_aa_info = (LinearLayout) inflate.findViewById(R.id.ll_aa_info);
		ll_group_info = (LinearLayout) inflate.findViewById(R.id.ll_group_info);
		rl_info_aa = (RelativeLayout) inflate.findViewById(R.id.rl_info_aa);
		rl_info_group = (RelativeLayout) inflate.findViewById(R.id.rl_info_group);
		iv_info_group = (ImageView) inflate.findViewById(R.id.iv_info_group);
		iv_info_aa = (ImageView) inflate.findViewById(R.id.iv_info_aa);
		tv_info_aa = (TextView) inflate.findViewById(R.id.tv_info_aa);
		tv_info_group = (TextView) inflate.findViewById(R.id.tv_info_group);
		btn_info_confirm = (Button) inflate.findViewById(R.id.btn_info_confirm);
	}

	@Override
	public void initEvent() {
		rl_info_aa.setOnClickListener(this);
		rl_info_group.setOnClickListener(this);
		btn_info_confirm.setOnClickListener(this);

	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initData() {
		setBackTitle("晒单");

	}

}
