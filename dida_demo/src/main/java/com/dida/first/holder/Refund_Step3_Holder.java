/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import com.dida.first.R;
import com.dida.first.factory.ActivityFactory;

/**
 * @author		KingJA 
 * @data		2015-9-30 上午10:13:25 
 * @use			
 *
 */
public class Refund_Step3_Holder extends BaseHolder {

	@Override
	public View initView() {
		view=View.inflate(ActivityFactory.mRefundActivity, R.layout.refund_step3, null);
		return view;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
