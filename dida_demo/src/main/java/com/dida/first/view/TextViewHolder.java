/**
 * 
 */
package com.dida.first.view;

import android.view.View;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.holder.BaseHolder;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-9-28 下午1:56:51 
 * @use			
 *
 */
public class TextViewHolder extends BaseHolder<String> {

	private MyGridViewTextView tv;

	@Override
	public View initView() {
		tv = (MyGridViewTextView) UIUtils.inflate(R.layout.textview_flowlayout);
		return tv;
	}

	@Override
	public void refreshView() {
		String paramString = getData();
		tv.setText(paramString);

	}

}
