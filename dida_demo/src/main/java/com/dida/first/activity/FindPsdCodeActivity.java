package com.dida.first.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dida.first.R;
import com.dida.first.textwatcher.MyTextWatcher;
import com.dida.first.utils.ToastUtil;
import com.dida.first.dialog.DialogDouble;
import com.dida.first.dialog.DialogDouble.OnDoubleClickListener;

import java.util.regex.Pattern;

/**
 * @author		KingJA 
 * @data		2015-8-27 下午5:26:52 
 * @use			
 *
 */
public class FindPsdCodeActivity extends BackTitleActivity {

	private EditText et_findpsd_phone;
	private EditText et_findpsd_code;
	private Button btn_findpsd_get_code;
	private Button btn_findpsd_check;
	private ImageView iv_findpsd_regex_phone;

	@Override
	public void setBackClick() {
		finish();
	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.btn_findpsd_get_code:
			if (checkPhone()) {
				btn_findpsd_get_code.setBackgroundResource(R.drawable.shape_lnull_bgray_r4);
				timeCount = new TimeCount(60 * 1000, 1000);
				timeCount.start();
				// 截取输入框焦点
				ll_findpsd_code_focus.setFocusable(true);
				ll_findpsd_code_focus.setFocusableInTouchMode(true);
				ll_findpsd_code_focus.requestFocus();
				// 关闭键盘
				closeKeyBoard();
			}
			break;
		case R.id.btn_findpsd_check:
			//TODO 验证逻辑
			break;
		default:
			break;
		}

	}
	/**
	 * 关闭键盘
	 */
	private void closeKeyBoard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	@Override
	public void initDoNet() {

	}

	@Override
	public void initData() {
		setBackTitle("1/2手机验证");

	}

	private String phone="";
	private String code="";
	private TextWatcher phoneWatcher=new MyTextWatcher() {
		
		@Override
		public void onAfterTextChanged() {
			phone = et_findpsd_phone.getText().toString().trim();
			if (Pattern.matches("^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}$",
					phone)) {
				iv_findpsd_regex_phone.setVisibility(View.VISIBLE);
				btn_findpsd_get_code.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
			} else {
				iv_findpsd_regex_phone.setVisibility(View.GONE);
				btn_findpsd_get_code.setBackgroundResource(R.drawable.shape_lnull_bgray_r4);
			}
		}
	};
	
	private TextWatcher codeWatcher=new MyTextWatcher() {
		
		@Override
		public void onAfterTextChanged() {
			code = et_findpsd_code.getText().toString().trim();
			if (code.length() > 0) {
				btn_findpsd_check.setClickable(true);
				btn_findpsd_check
						.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
			} else {
				btn_findpsd_check.setClickable(false);
				btn_findpsd_check
						.setBackgroundResource(R.drawable.shape_lnull_bgray_r4);
			}
		}
	};
	private LinearLayout ll_findpsd_code_focus;
	
	@Override
	public void initEvent() {
		btn_findpsd_get_code.setOnClickListener(this);
		btn_findpsd_check.setOnClickListener(this);
		iv_findpsd_regex_phone.setOnClickListener(this);
		et_findpsd_phone.addTextChangedListener(phoneWatcher);
		et_findpsd_code.addTextChangedListener(codeWatcher);
	}

	@Override
	public void initView() {
		ll_findpsd_code_focus = (LinearLayout) view.findViewById(R.id.ll_findpsd_code_focus);
		et_findpsd_phone = (EditText) view.findViewById(R.id.et_findpsd_phone);
		et_findpsd_code = (EditText) view.findViewById(R.id.et_findpsd_code);
		btn_findpsd_get_code = (Button) view.findViewById(R.id.btn_findpsd_get_code);
		btn_findpsd_check = (Button) view.findViewById(R.id.btn_findpsd_check);
		iv_findpsd_regex_phone = (ImageView) view.findViewById(R.id.iv_findpsd_regex_phone);

	}

	@Override
	public View setView() {
		view = View.inflate(FindPsdCodeActivity.this, R.layout.activity_findpsd_code, null);
		return view;
	}
	/**
	 * 验证手机号码
	 */
	private boolean checkPhone() {
		// 判断非空
		if (TextUtils.isEmpty(phone)) {
			ToastUtil.showMyToast( "手机号码不能为空");
			return false;
		}
		
		// 判断手机号格式
		if (!Pattern
				.matches("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$", phone)) {
			ToastUtil.showMyToast("手机号码格式不对");
			return false;
		}
		// 判断手机号是否注册过
		if ("18888888888".equals(phone)) {
			final DialogDouble doubleDialog = new DialogDouble(FindPsdCodeActivity.this, "这个号码已经被注册过了，请换一个手机号码注册或者直接登录", "去登录", "换个手机");
			doubleDialog.show();
			doubleDialog.setOnDoubleClickListener(new OnDoubleClickListener() {

				@Override
				public void onLeft() {

					doubleDialog.dismiss();
					finish();

				}

				@Override
				public void onRight() {
					doubleDialog.dismiss();
					et_findpsd_phone.setText("");
				}
			});
			return false;
		}
		return true;
		
	}
	private TimeCount timeCount;


	class TimeCount extends CountDownTimer {

		/**
		 * @param millisInFuture
		 * @param countDownInterval
		 */
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			btn_findpsd_get_code.setClickable(false);
			btn_findpsd_get_code.setText(millisUntilFinished / 1000 + "秒后重发");
		}

		@Override
		public void onFinish() {
			btn_findpsd_get_code.setText("重新发送");
			btn_findpsd_get_code.setClickable(true);
			btn_findpsd_get_code.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
		}

	}
}
