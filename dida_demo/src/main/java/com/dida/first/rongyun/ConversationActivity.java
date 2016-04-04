package com.dida.first.rongyun;


import com.dida.first.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 会话界面
 * 
 * @author Administrator
 * 
 */
public class ConversationActivity extends FragmentActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.conversation);
		/** 设置会话界面标题 **/
		setTitle();

	}

	/**
	 * 设置会话界面标题
	 */
	private void setTitle() {
		Uri uri = getIntent().getData();
		String targetId = uri.getQueryParameter("targetId");
		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		tv_name.setText(targetId);
	}

	/**
	 * 设置会话界返回按钮
	 */
	public void back(View v) {
		finish();
	}

}
