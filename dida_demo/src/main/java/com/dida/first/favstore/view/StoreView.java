package com.dida.first.favstore.view;

import com.dida.first.entity.BeanFavStore;

import java.util.List;

/**
 * Created by KingJA on 2016-1-18.
 */
public interface StoreView {
    void showProgress();
    void addData(List<BeanFavStore.ResEntity.ShopListEntity> shopList);
    void hideProgress();
    void showLoadErrMsg();
}
