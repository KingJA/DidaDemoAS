package com.dida.first.popupwindow;

import java.util.List;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author		KingJA 
 * @data		2015-9-15 下午1:38:47 
 * @use			
 *
 */
public class PopupWindowShare extends PopupWindowBaseDown {

	/**
	 * @param parentView
	 * @param activity
	 * @param list
	 */
	public PopupWindowShare(View parentView, Activity activity, List list) {
		super(parentView, activity, list);
	}
	View popupView;

	/**
	 * @param parentView
	 * @param activity
	 */
	

	@Override
	public View setPopupView(Activity activity) {
		popupView = View.inflate(activity, R.layout.popup_share, null);
		return popupView;
	}

	@Override
	public void initChildView() {
		LinearLayout ll_weixin_frind = (LinearLayout) popupView.findViewById(R.id.ll_weixin_frind);
		LinearLayout ll_weixin_group = (LinearLayout) popupView.findViewById(R.id.ll_weixin_group);
		LinearLayout ll_xinlang = (LinearLayout) popupView.findViewById(R.id.ll_xinlang);
		LinearLayout ll_qq = (LinearLayout) popupView.findViewById(R.id.ll_qq);
		LinearLayout ll_qzone = (LinearLayout) popupView.findViewById(R.id.ll_qzone);
		LinearLayout ll_copy = (LinearLayout) popupView.findViewById(R.id.ll_copy);
		ll_weixin_frind.setOnClickListener(this);
		ll_weixin_group.setOnClickListener(this);
		ll_xinlang.setOnClickListener(this);
		ll_qq.setOnClickListener(this);
		ll_qzone.setOnClickListener(this);
		ll_copy.setOnClickListener(this);
		
	}

	@Override
	public void OnChildClick(View v) {
		//TODO
		switch (v.getId()) {
		case R.id.ll_weixin_frind:
			ToastUtil.showMyToast("微信朋友");
			break;
		case R.id.ll_weixin_group:
			ToastUtil.showMyToast("微信朋友圈");
			break;
		case R.id.ll_xinlang:
			ToastUtil.showMyToast("新浪");
			break;
		case R.id.ll_qq:
			ToastUtil.showMyToast("QQ");
			break;
		case R.id.ll_qzone:
			ToastUtil.showMyToast("QQ空间");
			break;
		case R.id.ll_copy:
			ToastUtil.showMyToast("复制链接");
			break;

		default:
			break;
		}
		dismiss();

	}
	@Override
	public int setPopupHeight() {
		return LayoutParams.WRAP_CONTENT;
	}

	

}
