package com.dida.first.favstore.model;

import com.dida.first.interfaces.OnCommonResponseListener;

/**
 * Created by KingJA on 2016-1-18.
 */
public interface StoreListModel<T> {
    void loadNet(int page, String type, String userId, OnCommonResponseListener<T> listener);
}
