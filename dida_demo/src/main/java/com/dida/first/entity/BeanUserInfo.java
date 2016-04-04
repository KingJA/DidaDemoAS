package com.dida.first.entity;

/**
 * Created by KingJA on 2016-1-26.
 */
public class BeanUserInfo {


    /**
     * code : 1
     * msg : 登录成功
     * res : {"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","nickName":"Wuuuuu丶Jer","sex":1,"thumb":"/upload/image/20160128/20160128091754_734.png","token":"jBP9vf5C0lX2V6iHQ3QSBvLKz1s+3Sapo4f8mDDVyQBV/jU5+p4MGfseWZbYZj7PIOIe9Y7h+RFKZa9P8wFXi+HsPanaAzDkBILYPvUh3pL3WEejPWalU3wCkDdxEcpN"}
     */

    private int code;
    private String msg;
    /**
     * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
     * nickName : Wuuuuu丶Jer
     * sex : 1
     * thumb : /upload/image/20160128/20160128091754_734.png
     * token : jBP9vf5C0lX2V6iHQ3QSBvLKz1s+3Sapo4f8mDDVyQBV/jU5+p4MGfseWZbYZj7PIOIe9Y7h+RFKZa9P8wFXi+HsPanaAzDkBILYPvUh3pL3WEejPWalU3wCkDdxEcpN
     */

    private ResEntity res;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResEntity getRes() {
        return res;
    }

    public static class ResEntity {
        private String userId;
        private String nickName;
        private int sex;
        private String thumb;
        private String token;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public String getNickName() {
            return nickName;
        }

        public int getSex() {
            return sex;
        }

        public String getThumb() {
            return thumb;
        }

        public String getToken() {
            return token;
        }
    }
}
