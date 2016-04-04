package com.dida.first.activity;

import android.view.View;

import com.dida.first.utils.ToastUtil;

/**
 * Created by KingJA on 2016-1-13.
 */
public class Search_Pingou_Activity extends BaseSearchActivity {




    @Override
    protected View setView() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onChildClick(View v) {

    }

    @Override
    protected void onSearch(String name) {
        ToastUtil.showMyToast(name);
    }
    @Override
    protected void onSalesSort(String name) {
        ToastUtil.showMyToast("searchContent = "+ name +" 销量领先 ");
    }

    @Override
    protected void onMainSort(String name, int mainSortType) {
        ToastUtil.showMyToast("searchContent = "+ name +" mainSortType = "+mainSortType);
    }

    @Override
    protected void onPriceFromToSort(String name, String lowPrice, String maxPrice) {
        ToastUtil.showMyToast("searchContent = "+ name +" priceFrom = "+ lowPrice +" priceTo = "+ maxPrice);
    }
}
