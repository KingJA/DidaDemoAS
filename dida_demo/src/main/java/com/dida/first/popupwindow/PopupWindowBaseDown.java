package com.dida.first.popupwindow;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;

/**
 * @author Administrator 分享PopupWindow
 */
public abstract class PopupWindowBaseDown<T> extends PopupWindow implements OnDismissListener,
		OnClickListener{

	protected Activity activity;
	protected T data;
	protected View popupView;
	private PopupWindowBaseDown sharePopupWindow;
	private View parentView;
	protected int screenHeight;

	public PopupWindowBaseDown(View parentView,Activity activity,T data) {
		screenHeight = UIUtils.getScreenHeight();
		this.data=data;
		this.activity = activity;
		this.parentView = parentView;
		popupView = setPopupView(activity);
		// 查找点击事件的控件
		initChildView();
		// 设置PopupWindow布局
		this.setContentView(popupView);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(setPopupHeight());
		// 点击PopupWindow以外区域隐藏
		this.setFocusable(true);
		this.setBackgroundDrawable(new BitmapDrawable());
		// 监听PopupWindow关闭
		this.setOnDismissListener(this);
		// 可以PopupWindow点击
		this.setFocusable(true);
		// 设置PopupWindow出现和消失动画
		this.setAnimationStyle(R.style.PopupAnimation);
	}

	/**
	 * @return
	 */
	public abstract int setPopupHeight();

	/**
	 * @return
	 */
	public abstract View setPopupView(Activity activity);

	public abstract void initChildView();

	public void showPopupWindow() {
		if (!this.isShowing()) {
			setAlpha(activity, 0.7f);
			this.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
		}

	}
	public void showPopupWindowDown() {
		if (!this.isShowing()) {
			setAlpha(activity, 0.7f);
			this.showAsDropDown(parentView);
		}

	}

	public void closePopupWindow(Activity activity) {
		if (this.isShowing()) {
			this.dismiss();
			setAlpha(activity, 1f);
		}

	}

	private void setAlpha(Activity activity, float bgAlpha) {
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		activity.getWindow().setAttributes(lp);
	}

	@Override
	public void onDismiss() {
		setAlpha(activity, 1f);

	}

	@Override
	public void onClick(View v) {
		OnChildClick(v);
	}

	/**
	 * @param v
	 */
	public abstract void OnChildClick(View v);



}
