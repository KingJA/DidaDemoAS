package com.dida.first.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dida.first.R;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanSimple;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * @author KingJA
 * @data 2015-9-21 上午10:05:58
 * @use
 * 
 */
public class Personal_EditGenderk_Activity extends BackTitleActivity {

	private RelativeLayout rl_personal_gender_man;
	private RelativeLayout rl_personal_gender_woman;
	private ImageView iv_personal_gender_man;
	private ImageView iv_personal_gender_woman;
	private Button btn_personal_editgender_confirm;
	private int gender=1;

	@Override
	public View setView() {
		view=View.inflate(this, R.layout.activity_mine_personal_gender, null);
		return view;
	}

	@Override
	public void initView() {
		rl_personal_gender_man = (RelativeLayout) view.findViewById(R.id.rl_personal_gender_man);
		rl_personal_gender_woman = (RelativeLayout) view.findViewById(R.id.rl_personal_gender_woman);
		iv_personal_gender_man = (ImageView) view.findViewById(R.id.iv_personal_gender_man);
		iv_personal_gender_woman = (ImageView) view.findViewById(R.id.iv_personal_gender_woman);
		btn_personal_editgender_confirm = (Button) view.findViewById(R.id.btn_personal_editgender_confirm);
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initEvent() {
		rl_personal_gender_man.setOnClickListener(this);
		rl_personal_gender_woman.setOnClickListener(this);
		btn_personal_editgender_confirm.setOnClickListener(this);
	}

	@Override
	public void initData() {
		setBackTitle("修改性别");
		initGender();
	}

	private void initGender() {
		gender =  SharedPreferencesUtils.getIntData("gender", 1);
		setGender(gender);
	}

	@Override
	public void onChildClick(View v) {
		reSet();
		switch (v.getId()) {
		case R.id.rl_personal_gender_man:
			setGender(1);
			break;
		case R.id.rl_personal_gender_woman:
			setGender(0);
			break;
		case R.id.btn_personal_editgender_confirm:
			updataGender("fb9a38d82cd3405a9b60ec54cdb5ecdf",gender);
			break;
		default:
			break;
		}
		
	}

	private void setGender(int gender) {
		iv_personal_gender_man.setVisibility(gender==1?View.VISIBLE:View.GONE);
		iv_personal_gender_woman.setVisibility(gender==0?View.VISIBLE:View.GONE);
		SharedPreferencesUtils.saveIntData("gender", gender);
		this.gender=gender;
	}

	/**
	 * 返回性别
	 */
	private void resultGender() {
		Intent intent=new Intent();
		intent.putExtra("gender", gender);
		setResult(20, intent);
		finish();
	}


	private void updataGender(String userId, int sex) {
		mDialogProgress.show();
		OkHttpUtils
				.post()
				.url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_EDIT_USERINFO))
				.addParams("userId", userId)
				.addParams("sex", String.valueOf(sex))
				.addParams("app", "1")
				.build()
				.execute(new JsonCallBack<BeanSimple>(BeanSimple.class) {
					@Override
					public void onError(Request request, Exception e) {
						mDialogProgress.dismiss();
						ToastUtil.showMyToast("艾玛，别改来改去了！");
					}

					@Override
					public void onResponse(BeanSimple bean) {
						mDialogProgress.dismiss();
						resultGender();
					}
				});
		
	}

	private void reSet() {
		iv_personal_gender_man.setVisibility(View.GONE);
		iv_personal_gender_woman.setVisibility(View.GONE);
		
	}

	@Override
	public void setBackClick() {
		finish();
	}}
