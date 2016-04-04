package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2015-12-15.
 */
public class PrepayBean {

    /**
     * code : 1
     * msg : 数据加载成功
     * res : {"total":"0.01,0.01,","prepayOrderList":[{"id":"1449815692950499c435a83554fecb23","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815692950499c435a83554fecb23","productId":"3365a01c228f47aaac4f86aedaa2b790","productThumb":"/upload/image/20151128/20151128112539_751.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"青少年2015秋冬少女中长款加绒加厚连帽开衫卫衣初中高中学生外套 ","orderattrvalue":"尺寸 2XL "},{"id":"1449815622066b0869cc430454b2291e","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815622066b0869cc430454b2291e","productId":"2534e67cd3ef4437b539b10e7bbc92bb","productThumb":"/upload/image/20151128/20151128100542_966.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫 百搭打底衫毛衣女","orderattrvalue":"尺码 XL 颜色 白色 "}],"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","friendsList":[{"userId":"13","thumb":"/frontend/images/recommend-head2.jpg","userName":"admin"},{"userId":"16","thumb":"/frontend/images/recommend-head1.jpg","userName":"admin3"},{"userId":"2","thumb":"/upload/20141009141559318.jpg","userName":"wwasda2"}],"prepayId":"1449815692950499c435a83554fecb23,1449815622066b0869cc430454b2291e"}
     */

    private int code;
    private String msg;
    /**
     * total : 0.01,0.01,
     * prepayOrderList : [{"id":"1449815692950499c435a83554fecb23","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815692950499c435a83554fecb23","productId":"3365a01c228f47aaac4f86aedaa2b790","productThumb":"/upload/image/20151128/20151128112539_751.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"青少年2015秋冬少女中长款加绒加厚连帽开衫卫衣初中高中学生外套 ","orderattrvalue":"尺寸 2XL "},{"id":"1449815622066b0869cc430454b2291e","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815622066b0869cc430454b2291e","productId":"2534e67cd3ef4437b539b10e7bbc92bb","productThumb":"/upload/image/20151128/20151128100542_966.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫 百搭打底衫毛衣女","orderattrvalue":"尺码 XL 颜色 白色 "}]
     * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
     * friendsList : [{"userId":"13","thumb":"/frontend/images/recommend-head2.jpg","userName":"admin"},{"userId":"16","thumb":"/frontend/images/recommend-head1.jpg","userName":"admin3"},{"userId":"2","thumb":"/upload/20141009141559318.jpg","userName":"wwasda2"}]
     * prepayId : 1449815692950499c435a83554fecb23,1449815622066b0869cc430454b2291e
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
        private String total;
        private String userId;
        private String prepayId;
        /**
         * id : 1449815692950499c435a83554fecb23
         * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
         * orderNo : 1449815692950499c435a83554fecb23
         * productId : 3365a01c228f47aaac4f86aedaa2b790
         * productThumb : /upload/image/20151128/20151128112539_751.jpg
         * count : 1
         * price : 0.01
         * totalPrice : 0.01
         * orderName : 青少年2015秋冬少女中长款加绒加厚连帽开衫卫衣初中高中学生外套
         * orderattrvalue : 尺寸 2XL
         */

        private List<PrepayOrderListEntity> prepayOrderList;
        /**
         * userId : 13
         * thumb : /frontend/images/recommend-head2.jpg
         * userName : admin
         */

        private List<FriendsListEntity> friendsList;

        public void setTotal(String total) {
            this.total = total;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public void setPrepayOrderList(List<PrepayOrderListEntity> prepayOrderList) {
            this.prepayOrderList = prepayOrderList;
        }

        public void setFriendsList(List<FriendsListEntity> friendsList) {
            this.friendsList = friendsList;
        }

        public String getTotal() {
            return total;
        }

        public String getUserId() {
            return userId;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public List<PrepayOrderListEntity> getPrepayOrderList() {
            return prepayOrderList;
        }

        public List<FriendsListEntity> getFriendsList() {
            return friendsList;
        }

        public static class PrepayOrderListEntity {
            private String id;
            private String userId;
            private String orderNo;
            private String productId;
            private String productThumb;
            private int count;
            private double price;
            private double totalPrice;
            private String orderName;
            private String orderattrvalue;

            public void setId(String id) {
                this.id = id;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public void setProductThumb(String productThumb) {
                this.productThumb = productThumb;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public void setOrderName(String orderName) {
                this.orderName = orderName;
            }

            public void setOrderattrvalue(String orderattrvalue) {
                this.orderattrvalue = orderattrvalue;
            }

            public String getId() {
                return id;
            }

            public String getUserId() {
                return userId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public String getProductId() {
                return productId;
            }

            public String getProductThumb() {
                return productThumb;
            }

            public int getCount() {
                return count;
            }

            public double getPrice() {
                return price;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public String getOrderName() {
                return orderName;
            }

            public String getOrderattrvalue() {
                return orderattrvalue;
            }
        }

        public static class FriendsListEntity implements Serializable{
            private String userId;
            private String thumb;
            private String userName;
            private boolean isChecked;

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserId() {
                return userId;
            }

            public String getThumb() {
                return thumb;
            }

            public String getUserName() {
                return userName;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
        }
    }
}
