package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2016-1-19.
 */
public class BeanFavStore {


    /**
     * code : 1
     * res : {"shopList":[{"shopId":"2ea8fc255a274defaee939f47630efaa","thumb":"/frontend/images/recommend-head4.jpg","name":"随便买点东西","fansCount":1,"sellercredits":"level1_1"},{"shopId":"59c16220361e4ad99eaaa5b20246a796","thumb":"/frontend/images/recommend-head1.jpg","name":"老笨的杂货","fansCount":2,"sellercredits":"level1_1"},{"shopId":"998","thumb":"/frontend/images/recommend-head3.jpg","name":"西游记","fansCount":0,"sellercredits":"level1_1"},{"shopId":"1","thumb":"/frontend/images/recommend-head3.jpg","name":"三国演义","fansCount":1,"sellercredits":"level3_4"}],"pages":1,"rowCount":4}
     */

    private int code;
    /**
     * shopList : [{"shopId":"2ea8fc255a274defaee939f47630efaa","thumb":"/frontend/images/recommend-head4.jpg","name":"随便买点东西","fansCount":1,"sellercredits":"level1_1"},{"shopId":"59c16220361e4ad99eaaa5b20246a796","thumb":"/frontend/images/recommend-head1.jpg","name":"老笨的杂货","fansCount":2,"sellercredits":"level1_1"},{"shopId":"998","thumb":"/frontend/images/recommend-head3.jpg","name":"西游记","fansCount":0,"sellercredits":"level1_1"},{"shopId":"1","thumb":"/frontend/images/recommend-head3.jpg","name":"三国演义","fansCount":1,"sellercredits":"level3_4"}]
     * pages : 1
     * rowCount : 4
     */

    private ResEntity res;

    public void setCode(int code) {
        this.code = code;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public ResEntity getRes() {
        return res;
    }

    public static class ResEntity {
        private int pages;
        private int rowCount;
        /**
         * shopId : 2ea8fc255a274defaee939f47630efaa
         * thumb : /frontend/images/recommend-head4.jpg
         * name : 随便买点东西
         * fansCount : 1
         * sellercredits : level1_1
         */

        private List<ShopListEntity> shopList;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setShopList(List<ShopListEntity> shopList) {
            this.shopList = shopList;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<ShopListEntity> getShopList() {
            return shopList;
        }

        public static class ShopListEntity implements Serializable{
            private String shopId;
            private String thumb;
            private String name;
            private int fansCount;
            private int shopType;
            private int sellercredits;

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setFansCount(int fansCount) {
                this.fansCount = fansCount;
            }

            public void setSellercredits(int sellercredits) {
                this.sellercredits = sellercredits;
            }

            public String getShopId() {
                return shopId;
            }

            public String getThumb() {
                return thumb;
            }

            public String getName() {
                return name;
            }

            public int getFansCount() {
                return fansCount;
            }

            public int getSellercredits() {
                return sellercredits;
            }

            public int getShopType() {
                return shopType;
            }

            public void setShopType(int shopType) {
                this.shopType = shopType;
            }
        }
    }
}
