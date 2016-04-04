package com.dida.first.favpingou.presenter;

import com.dida.first.entity.BeanFavPingou;
import com.dida.first.favpingou.model.FavPingouModelImpl;
import com.dida.first.favpingou.view.FavPingouView;
import com.dida.first.interfaces.OnLoadMoreListener;
import com.dida.first.interfaces.OnLoadNetListener;

/**
 * Created by KingJA on 2016-1-28.
 */
public class FavPingouPresenterImpl implements FavPingouPresenter ,OnLoadNetListener<BeanFavPingou>,OnLoadMoreListener<BeanFavPingou>{
    private FavPingouView favPingouView;
    private final FavPingouModelImpl favPingouModel;

    public FavPingouPresenterImpl(FavPingouView favPingouView) {
        this.favPingouView = favPingouView;
        favPingouModel = new FavPingouModelImpl();
    }

    @Override
    public void loadNet(String userId, int page, int type, int status) {
        favPingouView.showProgress();
        favPingouModel.loadNet( userId,  1,  page,  status,this);
    }

    @Override
    public void loadMore(String userId, int page, int type, int status) {
        favPingouModel.loadMore(userId, page, type, status,this);
    }


    @Override
    public void onLoadMoreSuccess(BeanFavPingou data) {
        favPingouView.addData(data);
    }

    @Override
    public void onLoadMoreFailure(Exception e) {
        favPingouView.showError();
    }

    @Override
    public void onLoadNetSuccess(BeanFavPingou data) {
        favPingouView.hideProgress();
        favPingouView.setData(data);
    }

    @Override
    public void onLoadNetFailure(Exception e) {
        favPingouView.hideProgress();
        favPingouView.showError();
    }
}
