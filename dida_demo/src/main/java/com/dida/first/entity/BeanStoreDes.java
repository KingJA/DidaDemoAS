package com.dida.first.entity;

/**
 * Created by KingJA on 2016-1-19.
 */
public class BeanStoreDes {

    /**
     * code : 1
     * msg : 加载店铺简介成功
     * res : {"shopImformation":{"shopId":"59c16220361e4ad99eaaa5b20246a796","name":"老笨的杂货","fansCount":2,"thumb":"/frontend/images/mobile/recommend-head1.jpg","createTime":"2015-09-29 15:56:09","phone":"","address":"北京市九江市","sellercredits":"level1_1","sellerName":"老笨"},"isCollection":1}
     */

    private int code;
    private String msg;
    /**
     * shopImformation : {"shopId":"59c16220361e4ad99eaaa5b20246a796","name":"老笨的杂货","fansCount":2,"thumb":"/frontend/images/mobile/recommend-head1.jpg","createTime":"2015-09-29 15:56:09","phone":"","address":"北京市九江市","sellercredits":"level1_1","sellerName":"老笨"}
     * isCollection : 1
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
        /**
         * shopId : 59c16220361e4ad99eaaa5b20246a796
         * name : 老笨的杂货
         * fansCount : 2
         * thumb : /frontend/images/mobile/recommend-head1.jpg
         * createTime : 2015-09-29 15:56:09
         * phone :
         * address : 北京市九江市
         * sellercredits : level1_1
         * sellerName : 老笨
         */

        private ShopImformationEntity shopImformation;
        private int isCollection;

        public void setShopImformation(ShopImformationEntity shopImformation) {
            this.shopImformation = shopImformation;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public ShopImformationEntity getShopImformation() {
            return shopImformation;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public static class ShopImformationEntity {
            private String shopId;
            private String name;
            private int fansCount;
            private String thumb;
            private String createTime;
            private String phone;
            private String address;
            private int sellercredits;
            private String sellerName;

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setFansCount(int fansCount) {
                this.fansCount = fansCount;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setSellercredits(int sellercredits) {
                this.sellercredits = sellercredits;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public String getShopId() {
                return shopId;
            }

            public String getName() {
                return name;
            }

            public int getFansCount() {
                return fansCount;
            }

            public String getThumb() {
                return thumb;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getPhone() {
                return phone;
            }

            public String getAddress() {
                return address;
            }

            public int getSellercredits() {
                return sellercredits;
            }

            public String getSellerName() {
                return sellerName;
            }
        }
    }
}
