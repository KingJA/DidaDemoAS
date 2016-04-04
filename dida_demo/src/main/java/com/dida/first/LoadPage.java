package com.dida.first;

import com.dida.first.utils.UIUtils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author		KingJA 
 * @data		2015-8-6 下午1:49:49 
 * @use			根据参数返回不同页面
 * 				状态：
 * 				0.未加载
 * 				1.正在加载(页面)
 * 				2.无网络(页面)
 * 				3.错误(页面)
 * 				4.空数据(页面)
 *
 */
public abstract class LoadPage extends FrameLayout {
	private int state;
	private int STATE_UNLOAD=0;
	private int STATE_LOADING=1;
	private int STATE_SUCCESS=2;
	private int STATE_NONET=3;
	private int STATE_ERROR=4;
	private int STATE_EMPTY=5;
	private View loadView;
	private View successView;
	private View nonetView;
	private View errorView;
	private View emptyView;
	private FrameLayout.LayoutParams layoutParams;

	/**
	 * @param context
	 */
	public LoadPage(Context context) {
		super(context);
		layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		initView();
	}

	/**
	 * 初始化4个状态页面，并加到LoadPager上。
	 */
	private void initView() {
		//正在加载页面
		if (loadView==null) {
			loadView=UIUtils.inflate(R.layout.page_loading);
			this.addView(loadView, layoutParams);
		}
		//正在无网络页面
		if (nonetView==null) {
			nonetView=UIUtils.inflate(R.layout.page_nonet);
			this.addView(nonetView, layoutParams);
		}
		//正在错误页面
		if (errorView==null) {
			errorView=UIUtils.inflate(R.layout.page_error);
			this.addView(errorView, layoutParams);
		}
		//正在空页面
		if (emptyView==null) {
			emptyView=UIUtils.inflate(R.layout.page_empty);
			this.addView(emptyView, layoutParams);
		}
		showPage();
		
	}
	class RunnableTask implements Runnable{
		@Override
		public void run() {
			//请求网络操作
			final ResultState onLoad = onLoad();
			UIUtils.runInMainThread(new Runnable() {
				@Override
				public void run() {
					if(onLoad!=null){
						state = onLoad.getValue();
						//根据请求网络的状态，做页面的选择展示
						showPage();
					}
				}
			});
		}
	}
	
	public void show(){
		if(state == STATE_EMPTY || state == STATE_ERROR || state == STATE_SUCCESS){
			state = STATE_UNLOAD;
		}
		//准备做请求网络操作
		if(state == STATE_UNLOAD){
			ResultState onLoad = onLoad();
			if(onLoad!=null){
				state = onLoad.getValue();
				//根据请求网络的状态，做页面的选择展示
				showPage();
			}
		}
	}

	/**
	 * @return
	 */
	public abstract ResultState onLoad();

	/**
	 * 加载页面
	 * 
	 */
	private void showPage() {
		//展示加载页面
		if (loadView!=null) {
			loadView.setVisibility((state==STATE_UNLOAD||state==STATE_LOADING)?View.VISIBLE:View.GONE);
		}
		//展示无网络页面
		if (nonetView!=null) {
			nonetView.setVisibility((state==STATE_NONET)?View.VISIBLE:View.GONE);
		}
		//展示错误页面
		if (errorView!=null) {
			errorView.setVisibility((state==STATE_ERROR)?View.VISIBLE:View.GONE);
		}
		//展示空页面
		if (emptyView!=null) {
			emptyView.setVisibility((state==STATE_EMPTY)?View.VISIBLE:View.GONE);
		}
		
		//加载成功的页面
		if(successView == null && state == STATE_SUCCESS){
			successView = onCreateSuccessedView();
			addView(successView,layoutParams);
		}
		
		//展示成功页面
		if (successView!=null) {
			successView.setVisibility((state==STATE_SUCCESS)?View.VISIBLE:View.GONE);
		}
		
	}

	/**
	 * @return 成功页面
	 */
	public abstract View onCreateSuccessedView();
	public enum ResultState{
		STATE_SUCCESSED(2),
		STATE_NONET(3),
		STATE_ERROR(4),
		STATE_EMPTY(5);
		private int value;
		private ResultState(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
}
