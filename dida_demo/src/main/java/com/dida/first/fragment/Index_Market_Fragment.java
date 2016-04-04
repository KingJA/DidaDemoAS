package com.dida.first.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.dida.first.R;
import com.dida.first.activity.Search_Market_Activity;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.view.MarketSwitchButton;

public class Index_Market_Fragment extends Base_First_Fragment implements MarketSwitchButton.OnSwitchListener{
    private Market_Real_Fragment mRealFragment;
    private Market_Service_Fragment mServiceFragment;
    private static final String REAL="1";
    private static final String SERVICE="2";
    private String mType=REAL;
    private MarketSwitchButton msb_market;
    private RelativeLayout rl_market_search;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fr_market, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        msb_market = (MarketSwitchButton) view.findViewById(R.id.msb_market);
        rl_market_search = (RelativeLayout) view.findViewById(R.id.rl_market_search);
    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentEvent() {
        msb_market.setOnSwitchListener(this);
        rl_market_search.setOnClickListener(this);
    }

    @Override
    public void initFragmentData() {
        setTab(REAL);
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()){
            case R.id.rl_market_search:
                Bundle bundle = new Bundle();
                bundle.putString("TYPE",mType);
                ActivityUtil.goActivityWithBundle(mActivity, Search_Market_Activity.class,bundle);
            default:
                break;
        }

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    @Override
    protected boolean ifLoadPager() {
        return false;
    }

    /**
     * 选择对应的Fragment
     */
    // TODO 需要重构
    private void setTab(String position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (position) {
            case REAL:
                if (mRealFragment == null) {
                    mRealFragment = new Market_Real_Fragment();
                    transaction.add(R.id.fl_market, mRealFragment);
                } else {
                    transaction.show(mRealFragment);
                }
                break;
            case SERVICE :
                if (mServiceFragment == null) {
                    mServiceFragment = new Market_Service_Fragment();
                    transaction.add(R.id.fl_market, mServiceFragment);
                } else {
                    transaction.show(mServiceFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mRealFragment != null) {
            transaction.hide(mRealFragment);
        }
        if (mServiceFragment != null) {
            transaction.hide(mServiceFragment);
        }
    }

    @Override
    public void onSwitch(boolean isReal) {
       mType=isReal?REAL:SERVICE;
        ToastUtil.showMyToast(mType);
        setTab(mType);

    }
}
