/**
 * 
 */
package com.dida.first.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-10-16 下午1:22:47 
 * @use			
 *
 */
public class Fav_Product_Service extends Base_First_Fragment {

	@Override
	public View setFragmentView() {
		TextView tv = new TextView(context);
		tv.setText("服务");
		return tv;
	}

	@Override
	public void initFragmentView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFragmentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildClick(View v) {

	}

	@Override
	public void setMyRetryEvent(View retryView) {

	}

}
