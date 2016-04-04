package com.dida.first.minepingou.presenter;

import com.dida.first.entity.BeanMinePingou;
import com.dida.first.minepingou.model.MinePingouModelImpl;
import com.dida.first.minepingou.view.MinePinGouView;

/**
 * Created by KingJA on 2016-1-27.
 */
public class MinePingouPresenterImpl implements MinePinGouPresenter ,OnLoadNetListener<BeanMinePingou>{

    private MinePinGouView minePinGouView;
    private final MinePingouModelImpl minePingouModel;

    public MinePingouPresenterImpl(MinePinGouView minePinGouView) {
        this.minePinGouView = minePinGouView;
        minePingouModel = new MinePingouModelImpl();
    }

    @Override
    public void loadNet(String userId, int page, String type, String status) {
        minePinGouView.showProgress();
        minePingouModel.loadNet(userId,  page,  type,  status,this);
    }

    @Override
    public void onLoadNetSuccess(BeanMinePingou data) {
        minePinGouView.hideProgress();
        minePinGouView.setData(data);
    }

    @Override
    public void onLoadNetErr(Exception e) {
        minePinGouView.hideProgress();
        minePinGouView.showError();
    }
}
