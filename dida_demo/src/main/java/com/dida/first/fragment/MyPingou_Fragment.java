package com.dida.first.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.adapter.MyPingouAdapter;
import com.dida.first.entity.BeanMinePingou;
import com.dida.first.minepingou.presenter.MinePingouPresenterImpl;
import com.dida.first.minepingou.view.MinePinGouView;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KingJA on 2015-12-16.
 */
public class MyPingou_Fragment extends Base_Second_Fragment implements MinePinGouView,SwipeRefreshLayout.OnRefreshListener{
    private List<BeanMinePingou.ResEntity.GroupListEntity> myPingouList = new ArrayList<>();
    private ListView lv_single;
    private MinePingouPresenterImpl minePingouPresenter;
    private MyPingouAdapter myPingouAdapter;
    private SwipeRefreshLayout swipe_single;
    private int mType;

    public static MyPingou_Fragment getInstance(int type){
        MyPingou_Fragment myPingou__fragment = new MyPingou_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",type);
        myPingou__fragment.setArguments(bundle);
        return myPingou__fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("TYPE", 1);
    }

    @Override
    public View setFragmentView() {
     view=View.inflate(mActivity, R.layout.single_lv,null);
        return view;
    }

    @Override
    public void initFragmentView() {
        lv_single = (ListView) view.findViewById(R.id.lv_single);
        swipe_single = (SwipeRefreshLayout) view.findViewById(R.id.swipe_single);
        minePingouPresenter = new MinePingouPresenterImpl(this);
        myPingouAdapter = new MyPingouAdapter(myPingouList, mActivity);
    }

    @Override
    public void initFragmentNet() {


    }

    @Override
    public void initFragmentEvent() {
        swipe_single.setOnRefreshListener(this);
        swipe_single.setColorSchemeColors(0xFFFF5A5F);
        lv_single.setAdapter(myPingouAdapter);
    }

    @Override
    public void initFragmentData() {
        minePingouPresenter.loadNet("fb9a38d82cd3405a9b60ec54cdb5ecdf",1,"1",String.valueOf(mType));
    }

    @Override
    public void onChildClick(View v) {

    }


    @Override
    public void showProgress() {
        swipe_single.setProgressViewOffset(false, 0, UIUtils.dip2px(24));
       swipe_single.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_single.setRefreshing(false);
    }

    @Override
    public void setData(BeanMinePingou data) {
        myPingouList= data.getRes().getGroupList();
        myPingouAdapter.setData(myPingouList);
    }

    @Override
    public void addData(BeanMinePingou data) {

    }

    @Override
    public void showError() {
        ToastUtil.showMyToast("服务器君躲猫猫了！");
    }

    @Override
    public void onRefresh() {
        minePingouPresenter.loadNet("fb9a38d82cd3405a9b60ec54cdb5ecdf",1,"1",String.valueOf(mType));
    }
}
