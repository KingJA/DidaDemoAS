package com.dida.first.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.adapter.FavStoreAdapter;
import com.dida.first.entity.BeanFavStore;
import com.dida.first.favstore.presenter.StoreListPresenterImpl;
import com.dida.first.favstore.view.StoreView;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KingJA on 2016-1-18.
 */
public class StoreListActivity extends BackTitleActivity implements StoreView,AdapterView.OnItemClickListener{

    private SwipeRefreshLayout swipe_single;
    private ListView lv_single;
    private StoreListPresenterImpl storeListPresenter;
    private List<BeanFavStore.ResEntity.ShopListEntity> shopList=new ArrayList<>();
    private FavStoreAdapter favStoreAdapter;


    @Override
    public View setView() {
        view=View.inflate(this, R.layout.single_lv,null);
        return view;
    }

    @Override
    public void initView() {
        swipe_single = (SwipeRefreshLayout) view.findViewById(R.id.swipe_single);
        lv_single = (ListView) view.findViewById(R.id.lv_single);
        storeListPresenter = new StoreListPresenterImpl(this);
        favStoreAdapter = new FavStoreAdapter(shopList,this);

    }

    @Override
    public void initDoNet() {
        storeListPresenter.loadNet(1,"","fb9a38d82cd3405a9b60ec54cdb5ecdf");
    }

    @Override
    public void initEvent() {
        lv_single.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setBackTitle("收藏的店铺");
        lv_single.setAdapter(favStoreAdapter);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setBackClick() {
        finish();

    }

    @Override
    public void showProgress() {

        swipe_single.setRefreshing(true);
    }

    @Override
    public void addData(List<BeanFavStore.ResEntity.ShopListEntity> shopList) {
        favStoreAdapter.setData(shopList);

    }

    @Override
    public void hideProgress() {
        swipe_single.setRefreshing(false);
    }

    @Override
    public void showLoadErrMsg() {
        ToastUtil.showMyToast("服务器君失态了！");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BeanFavStore.ResEntity.ShopListEntity itemAtPosition = (BeanFavStore.ResEntity.ShopListEntity) parent.getItemAtPosition(position);
        String shopId = itemAtPosition.getShopId();
        int shopType = itemAtPosition.getShopType();
        Bundle bundle = new Bundle();
        bundle.putString("SHOP_ID",shopId);
        bundle.putInt("SHOP_TYPE",shopType);
        ToastUtil.showMyToast(shopId);
        ActivityUtil.goActivityWithBundle(this, Detail_Store_Activity.class,bundle);
    }
}
