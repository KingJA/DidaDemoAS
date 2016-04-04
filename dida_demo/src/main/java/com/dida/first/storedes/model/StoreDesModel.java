package com.dida.first.storedes.model;

/**
 * Created by KingJA on 2016-1-19.
 */
public interface StoreDesModel<T> {
    void loadNet(String shopId,String userId, StoreDesModelImpl.OnLoadNetListener listener);
    void loadCollect(int isCollection, int shopType, String userId , String shopId,StoreDesModelImpl.OnLoadCollectListener listener);
}
