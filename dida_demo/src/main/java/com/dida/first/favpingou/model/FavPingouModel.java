package com.dida.first.favpingou.model;

import com.dida.first.interfaces.OnLoadMoreListener;
import com.dida.first.interfaces.OnLoadNetListener;

/**
 * Created by KingJA on 2016-1-28.
 */
public interface FavPingouModel<T> {
    void loadNet(String userId , int page, int type , int status, OnLoadNetListener<T> listener);
    void loadMore(String userId , int page, int type , int status, OnLoadMoreListener<T> listener);
}
