package com.dida.first.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dida.first.R;

/**
 * @author KingJA
 * @data 2015-10-22 下午5:23:21
 * @use
 * 
 */
public class Setting_Help_Activity extends BackTitleActivity {

	private WebView wb_setting_help;
	private ProgressBar pb_setting_help;

	@Override
	public View setView() {
		view = View.inflate(Setting_Help_Activity.this,
				R.layout.activity_setting_help, null);
		return view;
	}

	@Override
	public void initView() {
		wb_setting_help = (WebView) view.findViewById(R.id.wb_setting_help);
		pb_setting_help = (ProgressBar) view.findViewById(R.id.pb_setting_help);

	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initEvent() {
		pb_setting_help.setMax(100);

	}

	@Override
	public void initData() {
		
		// wb_setting_help.loadUrl("http://m.service.taobao.com/pocket/cate.htm?spm=0.0.0.0&psc=1&id=1&cateName=\u8D26\u6237\u95EE\u9898");
//		wb_setting_help.loadUrl("http://www.baidu.com");
		wb_setting_help.loadUrl("file:///android_asset/index.html");
		wb_setting_help.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		//自适应屏幕
		wb_setting_help.getSettings().setJavaScriptEnabled(true);
//		wb_setting_help.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//		wb_setting_help.getSettings().setLoadWithOverviewMode(true);
		wb_setting_help.getSettings().setUseWideViewPort(true);
		wb_setting_help.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				setBackTitle(title);
			}
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				Log.i("newProgress", newProgress+"");
				if (newProgress==100) {
					pb_setting_help.setVisibility(View.GONE);
				}else {
					pb_setting_help.setVisibility(View.VISIBLE);
					pb_setting_help.setProgress(newProgress);
				}
			}
		});
		wb_setting_help.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
			
		});

	}

	@Override
	public void onChildClick(View v) {

	}

	@Override
	public void setBackClick() {
		finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (wb_setting_help.canGoBack()) {
				wb_setting_help.goBack();// 返回上一页面
				return true;
			} else {
				finish();// 退出程序
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
