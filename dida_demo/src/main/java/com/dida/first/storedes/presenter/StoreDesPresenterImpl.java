package com.dida.first.storedes.presenter;

import com.dida.first.entity.BeanSimple;
import com.dida.first.entity.BeanStoreDes;
import com.dida.first.storedes.model.StoreDesModelImpl;
import com.dida.first.storedes.view.StoreDesView;

/**
 * Created by KingJA on 2016-1-19.
 */
public class StoreDesPresenterImpl implements StoreDesPresenter ,StoreDesModelImpl.OnLoadNetListener,StoreDesModelImpl.OnLoadCollectListener{

    private StoreDesView storeDesView;
    private final StoreDesModelImpl storeDesModel;

    public StoreDesPresenterImpl(StoreDesView storeDesView) {
        this.storeDesView = storeDesView;
        storeDesModel = new StoreDesModelImpl();
    }

    @Override
    public void loadNet(String shopId, String userId) {
        storeDesView.showProgress();
        storeDesModel.loadNet(shopId,userId,  this);
    }

    @Override
    public void loadCollect(int isCollection, int shopType, String userId, String shopId) {
        storeDesView.showSmallProgress();
        storeDesModel.loadCollect(isCollection,shopType,userId,shopId,this);
    }


    @Override
    public void onLoadCollectSuccess(BeanSimple data) {
        storeDesView.hideSmallProgress();
        storeDesView.showCollectedState(data);
    }

    @Override
    public void onLoadCollectFailure(Exception e) {
        storeDesView.hideSmallProgress();
        storeDesView.showCollectErr();
    }

    @Override
    public void onLoadNetSuccess(BeanStoreDes data) {
        storeDesView.setData(data);
        storeDesView.hideProgress();
    }

    @Override
    public void onLoadNetFailure(Exception e) {
        storeDesView.hideProgress();
        storeDesView.showError();
    }
}
