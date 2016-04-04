/**
 * 
 */
package com.dida.first.listener;

import com.dida.first.activity.LoginActivity;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author		KingJA 
 * @data		2015-6-29 上午10:52:45 
 * @use			
 *
 */
public abstract class IfLoginClickListener implements OnClickListener {
	public Context context;
	

	@Override
	public void onClick(View v) {
		context=setContext();
		if (!UIUtils.getSP().getBoolean(CustomConstants.HASLOGIN, false)) {
			ToastUtil.showMyToast( "封装click");
			Intent intent=new Intent(context,LoginActivity.class);
			context.startActivity(intent);
		}else{
			onLoginedClick(v);
		}
		
	}


	/**
	 * 已经注册的触发事件
	 */
	public abstract void onLoginedClick(View v);


	/**
	 * @return
	 */
	public abstract Context setContext();

}
