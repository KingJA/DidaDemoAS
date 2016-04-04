package com.dida.first.entity;

/**
 * Created by KingJA on 2016-1-19.
 */
public class BeanSimple {

    /**
     * code : 0
     * msg : 已经是收藏店铺，不能重复收藏
     * res : 1
     */

    private int code;
    private String msg;
    private int res;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getRes() {
        return res;
    }
}
