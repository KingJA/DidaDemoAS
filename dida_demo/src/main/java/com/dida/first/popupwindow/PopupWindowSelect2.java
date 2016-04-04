package com.dida.first.popupwindow;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator 分享PopupWindow
 */
public class PopupWindowSelect2 extends PopupWindow implements OnDismissListener,
		OnClickListener{

	private Activity activity;
	private View popupView;
	private PopupWindowSelect2 sharePopupWindow;
	private View parentView;
	private TextView tv_market_select;
	private ImageView iv_mark_detail_close;

	/**
	 * 
	 */
	public PopupWindowSelect2(View parentView,Activity activity) {
		int screenHeight = UIUtils.getScreenHeight();
		this.activity = activity;
		this.parentView = parentView;
		popupView = View.inflate(activity, R.layout.popup_select, null);
		// 查找点击事件的控件
		initClickView();
		// 设置PopupWindow布局
		this.setContentView(popupView);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(screenHeight*3/5);
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

	private void initClickView() {
		tv_market_select = (TextView) popupView.findViewById(R.id.tv_market_select);
		tv_market_select.setOnClickListener(this);
		iv_mark_detail_close.setOnClickListener(this);
	}

	public void showSharePopupWindow() {
		if (!this.isShowing()) {
			setAlpha(activity, 0.7f);
			this.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			// this.update();
		}

	}

	public void closeSharePopupWindow(Activity activity) {
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
		Toast.makeText(activity, "关闭", 0).show();
		setAlpha(activity, 1f);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_market_select:
			break;
		case R.id.iv_mark_detail_close:
			break;

		default:
			break;
		}
		dismiss();
		
	}



}
