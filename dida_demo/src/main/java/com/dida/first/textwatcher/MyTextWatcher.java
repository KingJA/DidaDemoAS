/**
 * 
 */
package com.dida.first.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author		KingJA 
 * @data		2015-6-24 下午2:30:17 
 * @use			
 *
 */
public abstract class MyTextWatcher implements TextWatcher{

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		onAfterTextChanged();
		
	}


	public abstract void onAfterTextChanged();

}
