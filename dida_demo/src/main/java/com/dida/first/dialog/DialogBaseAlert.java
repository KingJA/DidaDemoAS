package com.dida.first.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dida.first.R;

public abstract class DialogBaseAlert extends AlertDialog implements android.view.View.OnClickListener {
    protected Context context;

    protected DialogBaseAlert(Context context) {
        super(context, R.style.CustomDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initView();
        initNet();
        initEvent();
        initData();

    }

    public abstract void initView();

    public abstract void initNet();

    public abstract void initEvent();

    public abstract void initData();

    public abstract void childClick(View v);

    @Override
    public void onClick(View v) {
        childClick(v);

    }

}
