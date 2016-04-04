package com.dida.first.favpingou.presenter;

/**
 * Created by KingJA on 2016-1-28.
 */
public interface FavPingouPresenter {
    void loadNet(String userId , int page, int type , int status);
    void loadMore(String userId , int page, int type , int status);
}
