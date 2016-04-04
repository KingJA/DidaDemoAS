package com.dida.first.minepingou.model;

import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanMinePingou;
import com.dida.first.minepingou.presenter.OnLoadNetListener;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-27.
 */
public class MinePingouModelImpl implements MinePingouModel<BeanMinePingou> {

    @Override
    public void loadNet(String userId, int page, String type, String status, final OnLoadNetListener<BeanMinePingou> onloadNetListener) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_MINE_PINGOU))
                .addParams("userId", userId)
                .addParams("page", String.valueOf(page))
                .addParams("type", type)
                .addParams("status", status)
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanMinePingou>(BeanMinePingou.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        onloadNetListener.onLoadNetErr(e);
                    }

                    @Override
                    public void onResponse(BeanMinePingou bean) {
                        onloadNetListener.onLoadNetSuccess(bean);
                    }
                });
    }
}
