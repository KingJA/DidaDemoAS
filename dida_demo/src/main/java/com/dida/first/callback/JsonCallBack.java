package com.dida.first.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KingJA on 2016-1-8.
 */
public abstract class JsonCallBack<T>extends Callback<T> {

    private Class<T> clazz;
    public JsonCallBack(Class<T> clazz){
        super();
        this.clazz=clazz;
    }

    @Override
    public T parseNetworkResponse(Response response) throws IOException {
        String string = response.body().string();
        return  json2Bean(string,clazz);
    }

    @Override
    public abstract void onError(Request request, Exception e);

    @Override
    public abstract void onResponse(T bean);



    public  <T> T json2Bean(String json,Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
