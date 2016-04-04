package com.dida.first.activity;

import android.content.Intent;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanUserInfo;
import com.dida.first.textwatcher.MyTextWatcher;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.CheckUtil;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.regex.Pattern;

import okhttp3.Request;

/**
 * 
 * @author KingJA
 * @data 2015-6-24 上午9:50:26
 * @use 登录Activity，可跳转到注册RegisterActivity,
 * 
 */
public class LoginActivity extends BackTitleActivity {

	private static final String TAG = "LoginActivity";
	private EditText et_login_name;
	private EditText et_login_password;
	private String phone = "";
	private String password = "";
	private ImageView iv_login_regex_phone;
	private ImageView iv_login_regex_password;
	private TextView tv_login_register;
	private TextView tv_login_remberber;
	private Button btn_login;

	@Override
	public View setView() {
		view = View.inflate(LoginActivity.this, R.layout.activity_login, null);

		return view;
	}

	@Override
	public void initView() {
		ll_login_focus = (LinearLayout) view.findViewById(R.id.ll_login_focus);
		tv_login_register = (TextView) view
				.findViewById(R.id.tv_login_register);
		tv_login_remberber = (TextView) view
				.findViewById(R.id.tv_login_remberber);
		iv_login_regex_phone = (ImageView) view
				.findViewById(R.id.iv_login_regex_phone);
		iv_login_regex_password = (ImageView) view
				.findViewById(R.id.iv_login_regex_password);
		et_login_name = (EditText) view.findViewById(R.id.et_login_name);
		et_login_password = (EditText) view
				.findViewById(R.id.et_login_password);
		btn_login = (Button) view.findViewById(R.id.btn_login);

	}

	@Override
	public void initEvent() {
		btn_login.setOnClickListener(this);
		tv_login_register.setOnClickListener(this);
		tv_login_remberber.setOnClickListener(this);
		et_login_name.addTextChangedListener(phoneTextWatcher);
		et_login_password.addTextChangedListener(passwordTextWatcher);
	}

	private TextWatcher phoneTextWatcher = new MyTextWatcher() {

		@Override
		public void onAfterTextChanged() {
			phone = et_login_name.getText().toString().trim();
			if (Pattern.matches("^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}$",
					phone)) {
				iv_login_regex_phone.setVisibility(View.VISIBLE);
			} else {
				iv_login_regex_phone.setVisibility(View.GONE);
			}
			if (phone.length() > 0 && password.length() > 0) {
				setClickTrue();
			} else {
				setClickFalse();
			}
		}
	};

	private TextWatcher passwordTextWatcher = new MyTextWatcher() {

		@Override
		public void onAfterTextChanged() {
			password = et_login_password.getText().toString().trim();
			if (Pattern.matches("[a-zA-Z0-9]{6,12}", password)) {
				iv_login_regex_password.setVisibility(View.VISIBLE);
			} else {
				iv_login_regex_password.setVisibility(View.GONE);
			}
			if (phone.length() > 0 && password.length() > 0) {
				setClickTrue();
			} else {
				setClickFalse();
			}
		}
	};
	private LinearLayout ll_login_focus;

	private void setClickFalse() {
		btn_login.setBackgroundResource(R.drawable.shape_lnull_bgray_r4);
		btn_login.setClickable(false);
	}

	private void setClickTrue() {
		btn_login.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
		btn_login.setClickable(true);
	}

	@Override
	public void initData() {
		setBackTitle("登录");
		setClickFalse();
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.tv_login_register:
			ToastUtil.showMyToast( "跳转注册页面");
			Intent lIntent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(lIntent);
			break;
		case R.id.tv_login_remberber:
			ToastUtil.showMyToast("跳转忘记密码页面");
			Intent rIntent = new Intent(LoginActivity.this,
					FindPsdCodeActivity.class);
			startActivity(rIntent);
			break;
		case R.id.btn_login:
			ToastUtil.showMyToast("登录");
			readyLogin();
			checkLogin();
			break;

		default:
			break;
		}
	}

	/**
	 * 验证登录
	 */
	private void checkLogin() {
		if (CheckUtil.checkPhoneFormat(phone)&& CheckUtil.checkPasswordFormat(password)) {
			loadNet(phone,password);
		}

	}
	public void loadNet(String userName,String passWord ){
		Log.i(TAG, "userName: "+userName+"passWord: "+passWord);
		Log.i(TAG, "url: "+UrlUtil.getIUrl(UrlUtil.InterfaceName.I_LOGIN));
		OkHttpUtils
				.post()
				.url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_LOGIN))
				.addParams("userName", userName)
				.addParams("passWord",passWord)
				.addParams("app", "1")
				.build()
				.execute(new JsonCallBack<BeanUserInfo>(BeanUserInfo.class) {
					@Override
					public void onError(Request request, Exception e) {

					}

					@Override
					public void onResponse(BeanUserInfo bean) {
						if (bean.getCode()==1){
							saveSharedPreferences(bean);
							ActivityUtil.goActivityAndFinish(LoginActivity.this, MainActivity.class);
							ToastUtil.showMyToast("登录成功！");
						}else{
							ToastUtil.showMyToast("账号或密码错误！");
						}
					}
				});
	}

	private void saveSharedPreferences(BeanUserInfo bean) {
		SharedPreferencesUtils.saveStringData("USER_ID",bean.getRes().getUserId());
		SharedPreferencesUtils.saveStringData("USER_TOKEN",bean.getRes().getToken());
		SharedPreferencesUtils.saveStringData("USER_NAME",bean.getRes().getNickName());
		SharedPreferencesUtils.saveIntData("USER_GENDER",bean.getRes().getSex());
		SharedPreferencesUtils.saveStringData("USER_ICON",bean.getRes().getThumb());
	}

	/**
	 * 去掉删除按钮，关闭键盘，失去焦点
	 */
	private void readyLogin() {
		ll_login_focus.setFocusable(true);
		ll_login_focus.setFocusableInTouchMode(true);
		ll_login_focus.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void setBackClick() {
		finish();
	}

}
