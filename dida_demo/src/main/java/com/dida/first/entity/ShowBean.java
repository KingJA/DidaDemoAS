package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2015-12-11.
 */
public class ShowBean {

    /**
     * code : 1
     * msg : 获取预付单成功
     * res : {"prepayOrderList":[{"id":"1449815692950499c435a83554fecb23","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815692950499c435a83554fecb23","productId":"3365a01c228f47aaac4f86aedaa2b790","productThumb":"/upload/image/20151128/20151128112539_751.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"青少年2015秋冬少女中长款加绒加厚连帽开衫卫衣初中高中学生外套 ","orderattrvalue":"尺寸 2XL "},{"id":"1449815622066b0869cc430454b2291e","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815622066b0869cc430454b2291e","productId":"2534e67cd3ef4437b539b10e7bbc92bb","productThumb":"/upload/image/20151128/20151128100542_966.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫 百搭打底衫毛衣女","orderattrvalue":"尺码 XL 颜色 白色 "},{"id":"14498153539379436bcb77d0b4a9fa15","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"14498153539379436bcb77d0b4a9fa15","productId":"d8139debd5d343d2ae1101e8a6af7cd8","productThumb":"/upload/image/20151128/20151128104724_140.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"粉红大布娃娃 2015秋冬新款气质修身荷叶边系蝴蝶结长袖女衬衫","orderattrvalue":"尺寸 XXL "},{"id":"1449730580534d0adee0edadc4beb896","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449730580534d0adee0edadc4beb896","productId":"f89e751773af47fda7f3added531c7a4","productThumb":"/upload/image/20151128/20151128102908_576.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮","orderattrvalue":"颜色 黑色 尺寸 XL "}]}
     */

    private int code;
    private String msg;
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

        public void setPrepayOrderList(List<PrepayOrderListEntity> prepayOrderList) {
            this.prepayOrderList = prepayOrderList;
        }

        public List<PrepayOrderListEntity> getPrepayOrderList() {
            return prepayOrderList;
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
            private boolean isChecked;

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

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
        }
    }
}
