package com.dida.first.favstore.model;

import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanFavStore;
import com.dida.first.interfaces.OnCommonResponseListener;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-18.
 */
public class StoreListModelImpl implements StoreListModel<BeanFavStore> {

    @Override
    public void loadNet(int page, String type, String userId, final OnCommonResponseListener<BeanFavStore> listener) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_STORE_LIST))
                .addParams("userId", userId)
                .addParams("page", String.valueOf(page))
                .addParams("type", String.valueOf(type))
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanFavStore>(BeanFavStore.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        listener.onFailure(e);
                    }

                    @Override
                    public void onResponse(BeanFavStore bean) {
                        listener.onSuccess(bean);
                    }
                });
    }
}
