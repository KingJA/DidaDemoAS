/**
 * 
 */
package com.dida.first.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-9-28 下午2:17:52
 * @use
 * 
 */
public class MyGridViewTextView extends TextView {
	public MyGridViewTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyGridViewTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
