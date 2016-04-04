package com.dida.first.favstore.presenter;

import com.dida.first.entity.BeanFavStore;
import com.dida.first.favstore.model.StoreListModelImpl;
import com.dida.first.favstore.view.StoreView;
import com.dida.first.interfaces.OnCommonResponseListener;

import java.util.List;

/**
 * Created by KingJA on 2016-1-18.
 */
public class StoreListPresenterImpl implements StoreListPresenter ,OnCommonResponseListener<BeanFavStore> {
    private StoreView storeView;
    private final StoreListModelImpl storeListModel;

    public StoreListPresenterImpl(StoreView storeView) {
        this.storeView = storeView;
        storeListModel = new StoreListModelImpl();
    }

    @Override
    public void loadNet(int page, String type, String userId) {
        storeView.showProgress();
        storeListModel.loadNet(page,type,userId,this);
    }

    @Override
    public void onSuccess(BeanFavStore mData) {
        List<BeanFavStore.ResEntity.ShopListEntity> shopList = mData.getRes().getShopList();
        storeView.hideProgress();
        storeView.addData(shopList);
    }

    @Override
    public void onFailure(Exception e) {
        storeView.hideProgress();
        storeView.showLoadErrMsg();
    }
}
