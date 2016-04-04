package com.dida.first.storedes.presenter;

/**
 * Created by KingJA on 2016-1-18.
 */
public interface StoreDesPresenter {
    void loadNet(String shopId,String userId);
    void loadCollect(int isCollection, int shopType, String userId , String shopId);
}
