package com.dida.first.storedes.model;

import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanSimple;
import com.dida.first.entity.BeanStoreDes;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-19.
 */
public class StoreDesModelImpl implements StoreDesModel {
    @Override
    public void loadNet(String shopId, String userId, final OnLoadNetListener listener) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_STORE_DETAIL))
                .addParams("shopId", shopId)
                .addParams("userId", userId)
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanStoreDes>(BeanStoreDes.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        listener.onLoadNetFailure(e);
                    }

                    @Override
                    public void onResponse(BeanStoreDes bean) {
                        listener.onLoadNetSuccess(bean);
                    }
                });
    }

    @Override
    public void loadCollect(int isCollection, int shopType, String userId, String shopId, final OnLoadCollectListener listener) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_STORE_COLLECT))
                .addParams("isCollection", String.valueOf(isCollection))
                .addParams("shopType", String.valueOf(shopType))
                .addParams("userId", userId)
                .addParams("shopId", shopId)
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanSimple>(BeanSimple.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        listener.onLoadCollectFailure(e);
                    }

                    @Override
                    public void onResponse(BeanSimple bean) {
                        listener.onLoadCollectSuccess(bean);
                    }
                });
    }



    public interface OnLoadNetListener{
        void onLoadNetSuccess(BeanStoreDes data);
        void onLoadNetFailure(Exception e);
    }
    public interface OnLoadCollectListener{
        void onLoadCollectSuccess(BeanSimple data);
        void onLoadCollectFailure(Exception e);
    }
}
