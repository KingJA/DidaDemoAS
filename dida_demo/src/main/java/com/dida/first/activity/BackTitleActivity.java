package com.dida.first.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dida.first.R;
import com.dida.first.dialog.DialogProgress;
import com.dida.first.utils.ToastUtil;
import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;

/**
 * 
 * @author KingJA
 * @data 2015-8-20 下午4:43:41
 * @use (返回+标题)的基类Activity
 * 
 */
public abstract class BackTitleActivity extends FragmentActivity  implements
		OnClickListener {
	protected static final int RES_REFRESH = 0;//刷新
	protected static final int RES_MORE = 1;//加载更多
	protected static final int RES_ERROR = -1;//错误
	protected static final int RES_NOMORE = -3;//没有更多
	protected boolean mHasMore = true;//有无更多
	protected View view;
	private ImageView iv_top_back_left;
	private ImageView iv_top_back_right;
	private TextView tv_top_back_title;
	private TextView tv_title_num;
	private TextView tv_top_back_right_title;
	private LinearLayout ll_title_num;
	private OnTextClickListener onTextClickListener;
	protected RequestQueue mQueue;
	protected LoadingAndRetryManager mLoadingAndRetryManager;
	protected Handler mHandler;
	protected DialogProgress mDialogProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mQueue = Volley.newRequestQueue(this);
		doBeforeSetContentView();
		setContentView(R.layout.top_back);
		mDialogProgress = new DialogProgress(this);
		initLoadPager();
		view = setView();
		initTopView();
		initView();
		initDoNet();
		initEvent();
		initData();

	}

	private void initLoadPager() {
		mLoadingAndRetryManager =  LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener()
		{
			@Override
			public void setRetryEvent(View retryView)
			{
				setMyRetryEvent(retryView);
			}
		});
		mLoadingAndRetryManager.showContent();
	}

	protected void doBeforeSetContentView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		onChildClick(v);
		switch (v.getId()) {
		case R.id.iv_top_back_left:
			setBackClick();
		case R.id.tv_top_back_right_title:
			if (onTextClickListener!=null) {
				onTextClickListener.onTextClick();
			}
			break;
		case R.id.iv_top_back_right:
			ToastUtil.showMyToast("聊天界面");
			break;
		default:
			break;
		}
	}

	

	private void initTopView() {
		tv_top_back_right_title = (TextView) findViewById(R.id.tv_top_back_right_title);
		tv_title_num = (TextView) findViewById(R.id.tv_title_num);
		ll_title_num = (LinearLayout) findViewById(R.id.ll_title_num);
		iv_top_back_left = (ImageView) findViewById(R.id.iv_top_back_left);
		iv_top_back_right = (ImageView) findViewById(R.id.iv_top_back_right);
		tv_top_back_title = (TextView) findViewById(R.id.tv_top_back_title);
		iv_top_back_left.setOnClickListener(this);
		iv_top_back_right.setOnClickListener(this);
		tv_top_back_right_title.setOnClickListener(this);
		FrameLayout fl_my_order_body = (FrameLayout) findViewById(R.id.fl_my_order_body);
		View child = setView();
		if (child != null) {
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			fl_my_order_body.addView(child, params);
		}
	}

	/**
	 * 设置标题
	 */
	public void setBackTitle(String title) {
		tv_top_back_title.setText(title);
	}
	
	/**
	 * 设置聊天秃瓢
	 */
	public void setChatClickVisibility() {
		iv_top_back_right.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置数量
	 */
	public void setBackNum(String num) {
		ll_title_num.setVisibility(View.VISIBLE);
		tv_title_num.setText(num);
	}
	/**
	 * 右侧文字按钮触发事件接口
	 */
	interface OnTextClickListener{
		void onTextClick();
	}
	/**
	 * 右侧文字按，显示，设置文字，设置点击事件。
	 */
	public void setOnTextClickListener(String doWhat,OnTextClickListener onTextClickListener){
		tv_top_back_right_title.setVisibility(View.VISIBLE);
		tv_top_back_right_title.setText(doWhat);
		this.onTextClickListener=onTextClickListener;
	}
	public abstract View setView();

	public abstract void initView();

	public abstract void initDoNet();

	public abstract void initEvent();

	public abstract void initData();

	public abstract void onChildClick(View v);

	public abstract void setBackClick();
	public  void setMyRetryEvent(View retryView){}
}
