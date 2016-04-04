package com.dida.first.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dida.first.dialog.DialogProgress;
import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;

/**
 * Created by Administrator on 2015-11-16.
 */
public abstract class BaseNomalActivity extends Activity implements View.OnClickListener{
    protected View view;
    protected RequestQueue mQueue;
    protected LoadingAndRetryManager mLoadingAndRetryManager;
    protected int mIsCollection;
    protected DialogProgress mDialogProgress;
    protected static final int RES_OK = 1;
    protected static final int RES_ERR = -1;
    protected static final int RES_COLLECT_OK = 2;
    protected static final int RES_COLLECT_ERR = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(this);
        view=setView();
        setContentView(view);
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener()
        {
            @Override
            public void setRetryEvent(View retryView)
            {

            }
        });
        mLoadingAndRetryManager.showContent();
        initView();
        initNet();
        initEvent();
        initData();
    }

    @Override
    public void onClick(View v) {
        onChildClick(v);
    }

    protected abstract void onChildClick(View v);
    protected abstract View setView();
    protected abstract void initView();
    protected abstract void initNet();
    protected abstract void initEvent();
    protected abstract void initData();








}
