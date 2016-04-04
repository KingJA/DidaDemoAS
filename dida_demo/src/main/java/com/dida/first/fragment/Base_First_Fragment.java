package com.dida.first.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dida.first.view.TitleMoveImgView;
import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;

/**
 * @author KingJA
 * @data 2015-10-14 下午2:11:29
 * @use
 */
public abstract class Base_First_Fragment extends Fragment implements View.OnClickListener {
    static final String TAG = "Base_First_Fragment";
    protected int mInitPager = 1;//初始化页面Position
    protected static final int RES_REFRESH = 0;//刷新
    protected static final int RES_MORE = 1;//加载更多
    protected static final int RES_ERROR = -1;//错误
    protected static final int RES_NOMORE = -3;//没有更多
    protected boolean mHasMore = true;//有无更多
    protected Context context;
    protected View view;
    protected RequestQueue mQueue;
    protected FragmentManager mFragmentManager;
    protected Activity mActivity;
    protected LoadingAndRetryManager mLoadingAndRetryManager;
    protected TitleMoveImgView adsLunBoTu;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
        mActivity = getActivity();
        mFragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = setFragmentView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initFragmentView();
        initFragmentNet();
        initFragmentEvent();
        initFragmentData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ifLoadPager()) {
            Log.i(TAG, "ifLoadPager: ");
            mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener() {
                @Override
                public void setRetryEvent(View retryView) {
                    setMyRetryEvent(retryView);
                }
            });
            mLoadingAndRetryManager.showContent();
        }

    }

    protected boolean ifLoadPager() {
        return true;
    }

    public abstract View setFragmentView();

    public abstract void initFragmentView();

    public abstract void initFragmentNet();

    public abstract void initFragmentEvent();

    public abstract void initFragmentData();

    public abstract void onChildClick(View v);

    public abstract void setMyRetryEvent(View retryView);

    @Override
    public void onClick(View v) {
        onChildClick(v);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setMoveImg(hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adsLunBoTu != null&&!adsLunBoTu.isRunning()) {
            adsLunBoTu.startRoll();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adsLunBoTu != null&&adsLunBoTu.isRunning()) {
            adsLunBoTu.stopRoll();
        }
    }

    private void setMoveImg(boolean hidden) {
        if (adsLunBoTu != null) {
            if (hidden&&adsLunBoTu.isRunning()) {
                adsLunBoTu.stopRoll();
            } else {
                adsLunBoTu.startRoll();
            }
        }
    }
}
