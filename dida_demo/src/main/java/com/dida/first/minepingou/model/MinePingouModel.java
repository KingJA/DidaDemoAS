package com.dida.first.minepingou.model;

import com.dida.first.minepingou.presenter.OnLoadNetListener;

/**
 * Created by KingJA on 2016-1-27.
 */
public interface MinePingouModel<T> {
    void loadNet(String userId, int page, String type, String status,OnLoadNetListener<T> onloadNetListener);

}
