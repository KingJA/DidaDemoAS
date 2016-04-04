package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2016-1-28.
 */
public class BeanFavPingou  {


   /**
     * code : 1
     * msg : 加载拼购收藏成功
     * res : {"pages":2,"rowCount":18,"groupCollection":[{"serviceId":2024,"groupMode":0,"name":"双商品发布团购","price":0.02,"oldPrice":0.02,"customDueDate":"2015-12-31 13:59:00","productThumb":"https://gd1.alicdn.com/bao/uploaded/i1/TB1br9WKFXXXXXdXVXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg_.webp","taskCount":0,"count":6,"buyCount":1},{"serviceId":2025,"groupMode":0,"name":"测试不同商家一起发布拼购","price":0.02,"oldPrice":0.02,"customDueDate":"2015-12-31 14:14:00","productThumb":"https://gd1.alicdn.com/bao/uploaded/i1/TB1br9WKFXXXXXdXVXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg_.webp","taskCount":4,"count":8,"buyCount":6},{"serviceId":2026,"groupMode":1,"name":"商家发布的拼购  多商品","price":10,"oldPrice":0.01,"customDueDate":"2015-12-31 13:52:00","productThumb":"//gd1.alicdn.com/bao/uploaded/i1/TB1C2OcJXXXXXXtXFXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg","taskCount":6,"count":4,"buyCount":0},{"serviceId":2028,"groupMode":0,"name":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","price":0.02,"oldPrice":0.02,"customDueDate":"2016-08-27 14:10:00","productThumb":"/upload/image/20151128/20151128112539_751.jpg","taskCount":20,"count":10,"buyCount":4},{"serviceId":2029,"groupMode":1,"name":"商家拼购测试","price":15,"oldPrice":20,"customDueDate":"2015-12-29 14:10:00","productThumb":"/upload/image/20151207/20151207093931_815.jpeg","taskCount":0,"count":12,"buyCount":0},{"serviceId":2030,"groupMode":1,"name":"5t","price":4,"oldPrice":0.01,"customDueDate":"2016-01-07 17:25:00","productThumb":"/upload/image/20151128/20151128102908_576.jpg","taskCount":1,"count":2,"buyCount":0},{"serviceId":2031,"groupMode":1,"name":"商家拼购测试","price":0.01,"oldPrice":0.01,"customDueDate":"2016-04-14 15:49:00","productThumb":"/upload/image/20160109/20160109155813_761.jpg","taskCount":0,"count":3,"buyCount":1},{"serviceId":2032,"groupMode":2,"name":"华为mete8首次抢购活动","price":0.01,"oldPrice":0.01,"customDueDate":"2016-04-07 17:02:00","productThumb":"/upload/image/20160109/20160109161239_202.jpg","taskCount":0,"count":5,"buyCount":0}]}
     */

    private int code;
    private String msg;
    /**
     * pages : 2
     * rowCount : 18
     * groupCollection : [{"serviceId":2024,"groupMode":0,"name":"双商品发布团购","price":0.02,"oldPrice":0.02,"customDueDate":"2015-12-31 13:59:00","productThumb":"https://gd1.alicdn.com/bao/uploaded/i1/TB1br9WKFXXXXXdXVXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg_.webp","taskCount":0,"count":6,"buyCount":1},{"serviceId":2025,"groupMode":0,"name":"测试不同商家一起发布拼购","price":0.02,"oldPrice":0.02,"customDueDate":"2015-12-31 14:14:00","productThumb":"https://gd1.alicdn.com/bao/uploaded/i1/TB1br9WKFXXXXXdXVXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg_.webp","taskCount":4,"count":8,"buyCount":6},{"serviceId":2026,"groupMode":1,"name":"商家发布的拼购  多商品","price":10,"oldPrice":0.01,"customDueDate":"2015-12-31 13:52:00","productThumb":"//gd1.alicdn.com/bao/uploaded/i1/TB1C2OcJXXXXXXtXFXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg","taskCount":6,"count":4,"buyCount":0},{"serviceId":2028,"groupMode":0,"name":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","price":0.02,"oldPrice":0.02,"customDueDate":"2016-08-27 14:10:00","productThumb":"/upload/image/20151128/20151128112539_751.jpg","taskCount":20,"count":10,"buyCount":4},{"serviceId":2029,"groupMode":1,"name":"商家拼购测试","price":15,"oldPrice":20,"customDueDate":"2015-12-29 14:10:00","productThumb":"/upload/image/20151207/20151207093931_815.jpeg","taskCount":0,"count":12,"buyCount":0},{"serviceId":2030,"groupMode":1,"name":"5t","price":4,"oldPrice":0.01,"customDueDate":"2016-01-07 17:25:00","productThumb":"/upload/image/20151128/20151128102908_576.jpg","taskCount":1,"count":2,"buyCount":0},{"serviceId":2031,"groupMode":1,"name":"商家拼购测试","price":0.01,"oldPrice":0.01,"customDueDate":"2016-04-14 15:49:00","productThumb":"/upload/image/20160109/20160109155813_761.jpg","taskCount":0,"count":3,"buyCount":1},{"serviceId":2032,"groupMode":2,"name":"华为mete8首次抢购活动","price":0.01,"oldPrice":0.01,"customDueDate":"2016-04-07 17:02:00","productThumb":"/upload/image/20160109/20160109161239_202.jpg","taskCount":0,"count":5,"buyCount":0}]
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
        private int pages;
        private int rowCount;
        /**
         * serviceId : 2024
         * groupMode : 0
         * name : 双商品发布团购
         * price : 0.02
         * oldPrice : 0.02
         * customDueDate : 2015-12-31 13:59:00
         * productThumb : https://gd1.alicdn.com/bao/uploaded/i1/TB1br9WKFXXXXXdXVXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg_.webp
         * taskCount : 0
         * count : 6
         * buyCount : 1
         */

        private List<GroupCollectionEntity> groupCollection;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setGroupCollection(List<GroupCollectionEntity> groupCollection) {
            this.groupCollection = groupCollection;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<GroupCollectionEntity> getGroupCollection() {
            return groupCollection;
        }

        public static class GroupCollectionEntity {
            private int serviceId;
            private int groupMode;
            private String name;
            private double price;
            private double oldPrice;
            private String customDueDate;
            private String productThumb;
            private int taskCount;
            private int count;
            private int buyCount;
            private int groupStatus;

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setOldPrice(double oldPrice) {
                this.oldPrice = oldPrice;
            }

            public void setCustomDueDate(String customDueDate) {
                this.customDueDate = customDueDate;
            }

            public void setProductThumb(String productThumb) {
                this.productThumb = productThumb;
            }

            public void setTaskCount(int taskCount) {
                this.taskCount = taskCount;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

            public int getServiceId() {
                return serviceId;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public String getName() {
                return name;
            }

            public double getPrice() {
                return price;
            }

            public double getOldPrice() {
                return oldPrice;
            }

            public String getCustomDueDate() {
                return customDueDate;
            }

            public String getProductThumb() {
                return productThumb;
            }

            public int getTaskCount() {
                return taskCount;
            }

            public int getCount() {
                return count;
            }

            public int getBuyCount() {
                return buyCount;
            }

            public int getGroupStatus() {
                return groupStatus;
            }

            public void setGroupStatus(int groupStatus) {
                this.groupStatus = groupStatus;
            }
        }
    }
}
