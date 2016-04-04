package com.dida.first.entity;

/**
 * Created by KingJA on 2015-12-2.
 */
public class BeanRes {

    /**
     * code : 1
     * msg : 删除商品收藏成功
     * res : 0
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
