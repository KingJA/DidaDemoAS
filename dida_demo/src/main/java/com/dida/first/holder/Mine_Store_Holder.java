/**
 * 
 */
package com.dida.first.holder;

import android.view.View;

import com.dida.first.R;
import com.dida.first.entity.BeanStores;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-10-14 上午9:12:06 
 * @use			
 *
 */
public class Mine_Store_Holder extends BaseHolder<BeanStores> {

	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.item_mine_stores);
		return view;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
