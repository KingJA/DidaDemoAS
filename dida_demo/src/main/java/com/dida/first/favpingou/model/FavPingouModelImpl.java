package com.dida.first.favpingou.model;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dida.first.application.App;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanFavPingou;
import com.dida.first.interfaces.OnLoadMoreListener;
import com.dida.first.interfaces.OnLoadNetListener;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-28.
 */
public class FavPingouModelImpl implements FavPingouModel<BeanFavPingou> {
    private RequestQueue mQueue = Volley.newRequestQueue(App.getContext());
    @Override
    public void loadNet(final String userId, final int page, final int type, final int status, final OnLoadNetListener<BeanFavPingou> listener) {
//        OkHttpUtils
//                .post()
//                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_FAV_PINGOU))
//                .addParams("userId", userId)
//                .addParams("page", String.valueOf(page))
//                .addParams("type", String.valueOf(type))
//                .addParams("status", String.valueOf(status))
//                .addParams("app", "1")
//                .build()
//                .execute(new JsonCallBack<BeanFavPingou>(BeanFavPingou.class) {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        listener.onLoadNetFailure(e);
//                    }
//
//                    @Override
//                    public void onResponse(BeanFavPingou bean) {
//                        listener.onLoadNetSuccess(bean);
//                    }
//                });
        VolleyGsonRequest<BeanFavPingou> pingouRequest = new VolleyGsonRequest<BeanFavPingou>(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_FAV_PINGOU), BeanFavPingou.class, new Response.Listener<BeanFavPingou>() {
            @Override
            public void onResponse(BeanFavPingou res) {
                listener.onLoadNetSuccess(res);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                listener.onLoadNetFailure(e);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", userId);
                map.put("page", String.valueOf(page));
                map.put("type", String.valueOf(type));
                map.put("status", String.valueOf(status));
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(pingouRequest);
    }

    @Override
    public void loadMore(String userId, int page, int type, int status, final OnLoadMoreListener<BeanFavPingou> listener) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_FAV_PINGOU))
                .addParams("userId", userId)
                .addParams("page", String.valueOf(page))
                .addParams("type", String.valueOf(type))
                .addParams("status", String.valueOf(status))
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanFavPingou>(BeanFavPingou.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        listener.onLoadMoreFailure(e);
                    }

                    @Override
                    public void onResponse(BeanFavPingou bean) {
                        listener.onLoadMoreSuccess(bean);
                    }
                });
    }
}
