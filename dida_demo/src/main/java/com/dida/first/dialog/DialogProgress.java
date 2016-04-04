package com.dida.first.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.dida.first.R;

public class DialogProgress extends AlertDialog {

	/**
	 * @param context
	 */
	public DialogProgress(Context context) {
		super(context,R.style.dialog_progress);
		this.setCancelable(true);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);
	}
	
}
