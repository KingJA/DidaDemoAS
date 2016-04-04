package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2015-12-1.
 */
public class BeanDetailMarket {

    /**
     * code : 1
     * msg : success
     * res : {"productType":1,"timeOrPhyProduct":{"productNo":"f89e751773af47fda7f3added531c7a4","userthumb":"/frontend/images/recommend-head3.jpg","name":"毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮","salesCount":17,"price":0.01,"shopId":"1","stock":999},"detailDesUrl":"/commodity/viewDetailDes.do?productNo=f89e751773af47fda7f3added531c7a4&type=1&app=1","purchaseAttrList":[{"attrId":"7c4ca88612a3475f85d1ef18ff75d88e","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"颜色","attrValues":[{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]},{"attrId":"863e07aa273d4ee9a6b5f86fa411f495","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"尺寸","attrValues":[{"valId":"f5f98f01818c4fc2a2caf410c291ed7c","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"S"},{"valId":"5dd31b2419f54f8fb6b83f46590730e9","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"M"},{"valId":"f4b5d6ee34234f10820f2873f6303ebc","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"L"},{"valId":"9d68d5517b664b03a672a31a844962ab","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"XL"}]}],"productCustomAttrList":[{"attributeName":"品牌","attributeValue":"迪莎衣品"},{"attributeName":"服装版型","attributeValue":"直筒"},{"attributeName":"厚薄","attributeValue":"加厚"},{"attributeName":"风格","attributeValue":"通勤"},{"attributeName":"通勤","attributeValue":"韩版"},{"attributeName":"款式","attributeValue":"套头"},{"attributeName":"组合形式","attributeValue":"单件"},{"attributeName":"衣长","attributeValue":"常规款"},{"attributeName":"袖长","attributeValue":"长袖"},{"attributeName":"领子","attributeValue":"高领"},{"attributeName":"袖型","attributeValue":"常规"},{"attributeName":"衣门襟","attributeValue":"套头"},{"attributeName":"图案","attributeValue":"纯色"},{"attributeName":"流行元素/工艺","attributeValue":"纱网"},{"attributeName":"适用年龄","attributeValue":"25-29周岁"},{"attributeName":"上市年份/季节","attributeValue":"2015年冬季"},{"attributeName":"颜色分类","attributeValue":"黑色 棕色"},{"attributeName":"尺码","attributeValue":"XL L M S"}],"productImgs":["/upload/image/20151128/mobile/20151128103132_965.jpg","/upload/image/20151128/mobile/20151128103139_50.jpg","/upload/image/20151128/mobile/20151128103148_384.jpg"],"evaluationCount":1,"feedBackVOs":[{"feedBackId":"835e199057894384ae3197a22315cfdd","orderNo":"14508377598684384ca9033e641b2952","createTime":"2015-12-23 14:04:53","des":"大小合适，无色差","imageUrlList":["/upload/image/20151223/20151223140413_209.png","/upload/image/20151223/20151223140422_320.png","/upload/image/20151223/20151223140431_920.png","/upload/image/20151223/20151223140439_936.png","/upload/image/20151223/20151223140449_259.png"],"afterComment":[{"afterId":"2551d6e82982401a9222fd34c67d0763","des":"很好","createTime":"2015-12-23 14:06:48","addDays":0,"imageUrlList":["/upload/image/20151223/20151223140616_836.png","/upload/image/20151223/20151223140623_977.png","/upload/image/20151223/20151223140631_937.png","/upload/image/20151223/20151223140638_651.png","/upload/image/20151223/20151223140645_78.png"]}],"orderAttrValues":"颜色 黑色 尺寸 XL ","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}],"shop":{"shopId":"1","name":"三国演义","thumb":"/frontend/images/recommend-head3.jpg","address":"浙江省 温州市"},"isCollection":0}
     */

    private int code;
    private String msg;
    /**
     * productType : 1
     * timeOrPhyProduct : {"productNo":"f89e751773af47fda7f3added531c7a4","userthumb":"/frontend/images/recommend-head3.jpg","name":"毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮","salesCount":17,"price":0.01,"shopId":"1","stock":999}
     * detailDesUrl : /commodity/viewDetailDes.do?productNo=f89e751773af47fda7f3added531c7a4&type=1&app=1
     * purchaseAttrList : [{"attrId":"7c4ca88612a3475f85d1ef18ff75d88e","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"颜色","attrValues":[{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]},{"attrId":"863e07aa273d4ee9a6b5f86fa411f495","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"尺寸","attrValues":[{"valId":"f5f98f01818c4fc2a2caf410c291ed7c","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"S"},{"valId":"5dd31b2419f54f8fb6b83f46590730e9","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"M"},{"valId":"f4b5d6ee34234f10820f2873f6303ebc","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"L"},{"valId":"9d68d5517b664b03a672a31a844962ab","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"XL"}]}]
     * productCustomAttrList : [{"attributeName":"品牌","attributeValue":"迪莎衣品"},{"attributeName":"服装版型","attributeValue":"直筒"},{"attributeName":"厚薄","attributeValue":"加厚"},{"attributeName":"风格","attributeValue":"通勤"},{"attributeName":"通勤","attributeValue":"韩版"},{"attributeName":"款式","attributeValue":"套头"},{"attributeName":"组合形式","attributeValue":"单件"},{"attributeName":"衣长","attributeValue":"常规款"},{"attributeName":"袖长","attributeValue":"长袖"},{"attributeName":"领子","attributeValue":"高领"},{"attributeName":"袖型","attributeValue":"常规"},{"attributeName":"衣门襟","attributeValue":"套头"},{"attributeName":"图案","attributeValue":"纯色"},{"attributeName":"流行元素/工艺","attributeValue":"纱网"},{"attributeName":"适用年龄","attributeValue":"25-29周岁"},{"attributeName":"上市年份/季节","attributeValue":"2015年冬季"},{"attributeName":"颜色分类","attributeValue":"黑色 棕色"},{"attributeName":"尺码","attributeValue":"XL L M S"}]
     * productImgs : ["/upload/image/20151128/mobile/20151128103132_965.jpg","/upload/image/20151128/mobile/20151128103139_50.jpg","/upload/image/20151128/mobile/20151128103148_384.jpg"]
     * evaluationCount : 1
     * feedBackVOs : [{"feedBackId":"835e199057894384ae3197a22315cfdd","orderNo":"14508377598684384ca9033e641b2952","createTime":"2015-12-23 14:04:53","des":"大小合适，无色差","imageUrlList":["/upload/image/20151223/20151223140413_209.png","/upload/image/20151223/20151223140422_320.png","/upload/image/20151223/20151223140431_920.png","/upload/image/20151223/20151223140439_936.png","/upload/image/20151223/20151223140449_259.png"],"afterComment":[{"afterId":"2551d6e82982401a9222fd34c67d0763","des":"很好","createTime":"2015-12-23 14:06:48","addDays":0,"imageUrlList":["/upload/image/20151223/20151223140616_836.png","/upload/image/20151223/20151223140623_977.png","/upload/image/20151223/20151223140631_937.png","/upload/image/20151223/20151223140638_651.png","/upload/image/20151223/20151223140645_78.png"]}],"orderAttrValues":"颜色 黑色 尺寸 XL ","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]
     * shop : {"shopId":"1","name":"三国演义","thumb":"/frontend/images/recommend-head3.jpg","address":"浙江省 温州市"}
     * isCollection : 0
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
        private int productType;
        /**
         * productNo : f89e751773af47fda7f3added531c7a4
         * userthumb : /frontend/images/recommend-head3.jpg
         * name : 毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮
         * salesCount : 17
         * price : 0.01
         * shopId : 1
         * stock : 999
         */

        private TimeOrPhyProductEntity timeOrPhyProduct;
        private String detailDesUrl;
        private int evaluationCount;
        /**
         * shopId : 1
         * name : 三国演义
         * thumb : /frontend/images/recommend-head3.jpg
         * address : 浙江省 温州市
         */

        private ShopEntity shop;
        private int isCollection;
        /**
         * attrId : 7c4ca88612a3475f85d1ef18ff75d88e
         * productNo : f89e751773af47fda7f3added531c7a4
         * attributeName : 颜色
         * attrValues : [{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]
         */

        private List<PurchaseAttrListEntity> purchaseAttrList;
        /**
         * attributeName : 品牌
         * attributeValue : 迪莎衣品
         */

        private List<ProductCustomAttrListEntity> productCustomAttrList;
        private List<String> productImgs;
        /**
         * feedBackId : 835e199057894384ae3197a22315cfdd
         * orderNo : 14508377598684384ca9033e641b2952
         * createTime : 2015-12-23 14:04:53
         * des : 大小合适，无色差
         * imageUrlList : ["/upload/image/20151223/20151223140413_209.png","/upload/image/20151223/20151223140422_320.png","/upload/image/20151223/20151223140431_920.png","/upload/image/20151223/20151223140439_936.png","/upload/image/20151223/20151223140449_259.png"]
         * afterComment : [{"afterId":"2551d6e82982401a9222fd34c67d0763","des":"很好","createTime":"2015-12-23 14:06:48","addDays":0,"imageUrlList":["/upload/image/20151223/20151223140616_836.png","/upload/image/20151223/20151223140623_977.png","/upload/image/20151223/20151223140631_937.png","/upload/image/20151223/20151223140638_651.png","/upload/image/20151223/20151223140645_78.png"]}]
         * orderAttrValues : 颜色 黑色 尺寸 XL
         * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
         * userName : wujie0209
         * thumb : /frontend/images/recommend-head4.jpg
         */

        private List<FeedBackVOsEntity> feedBackVOs;
        private List<String> imageJson;
        public void setImageJson(List<String> imageJson) {
            this.imageJson = imageJson;
        }
        public List<String> getImageJson() {
            return imageJson;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public void setTimeOrPhyProduct(TimeOrPhyProductEntity timeOrPhyProduct) {
            this.timeOrPhyProduct = timeOrPhyProduct;
        }

        public void setDetailDesUrl(String detailDesUrl) {
            this.detailDesUrl = detailDesUrl;
        }

        public void setEvaluationCount(int evaluationCount) {
            this.evaluationCount = evaluationCount;
        }

        public void setShop(ShopEntity shop) {
            this.shop = shop;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public void setPurchaseAttrList(List<PurchaseAttrListEntity> purchaseAttrList) {
            this.purchaseAttrList = purchaseAttrList;
        }

        public void setProductCustomAttrList(List<ProductCustomAttrListEntity> productCustomAttrList) {
            this.productCustomAttrList = productCustomAttrList;
        }

        public void setProductImgs(List<String> productImgs) {
            this.productImgs = productImgs;
        }

        public void setFeedBackVOs(List<FeedBackVOsEntity> feedBackVOs) {
            this.feedBackVOs = feedBackVOs;
        }

        public int getProductType() {
            return productType;
        }

        public TimeOrPhyProductEntity getTimeOrPhyProduct() {
            return timeOrPhyProduct;
        }

        public String getDetailDesUrl() {
            return detailDesUrl;
        }

        public int getEvaluationCount() {
            return evaluationCount;
        }

        public ShopEntity getShop() {
            return shop;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public List<PurchaseAttrListEntity> getPurchaseAttrList() {
            return purchaseAttrList;
        }

        public List<ProductCustomAttrListEntity> getProductCustomAttrList() {
            return productCustomAttrList;
        }

        public List<String> getProductImgs() {
            return productImgs;
        }

        public List<FeedBackVOsEntity> getFeedBackVOs() {
            return feedBackVOs;
        }

        public static class TimeOrPhyProductEntity {
            private String mobileThumb;
            private String shareLink;
            private String mobileDes;
            private String productNo;
            private String userthumb;
            private String name;
            private int salesCount;
            private double price;
            private String shopId;
            private int stock;

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public void setUserthumb(String userthumb) {
                this.userthumb = userthumb;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSalesCount(int salesCount) {
                this.salesCount = salesCount;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getProductNo() {
                return productNo;
            }

            public String getUserthumb() {
                return userthumb;
            }

            public String getName() {
                return name;
            }

            public int getSalesCount() {
                return salesCount;
            }

            public double getPrice() {
                return price;
            }

            public String getShopId() {
                return shopId;
            }

            public int getStock() {
                return stock;
            }

            public String getMobileThumb() {
                return mobileThumb;
            }

            public void setMobileThumb(String mobileThumb) {
                this.mobileThumb = mobileThumb;
            }

            public String getShareLink() {
                return shareLink;
            }

            public void setShareLink(String shareLink) {
                this.shareLink = shareLink;
            }

            public String getMobileDes() {
                return mobileDes;
            }

            public void setMobileDes(String mobileDes) {
                this.mobileDes = mobileDes;
            }
        }

        public static class ShopEntity {
            private String shopId;
            private String name;
            private String thumb;
            private String address;

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getShopId() {
                return shopId;
            }

            public String getName() {
                return name;
            }

            public String getThumb() {
                return thumb;
            }

            public String getAddress() {
                return address;
            }
        }

        public static class PurchaseAttrListEntity {
            private String attrId;
            private String productNo;
            private String attributeName;
            /**
             * valId : d5ff0117d6e0404eb5686ee38ba20b70
             * attrId : 7c4ca88612a3475f85d1ef18ff75d88e
             * attrValue : 黑色
             */

            private List<AttrValuesEntity> attrValues;

            public void setAttrId(String attrId) {
                this.attrId = attrId;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public void setAttrValues(List<AttrValuesEntity> attrValues) {
                this.attrValues = attrValues;
            }

            public String getAttrId() {
                return attrId;
            }

            public String getProductNo() {
                return productNo;
            }

            public String getAttributeName() {
                return attributeName;
            }

            public List<AttrValuesEntity> getAttrValues() {
                return attrValues;
            }

            public static class AttrValuesEntity {
                private String attrValue;
                private boolean isCheck;


                public String getAttrValue() {
                    return attrValue;
                }

                public void setAttrValue(String attrValue) {
                    this.attrValue = attrValue;
                }

                public boolean isCheck() {
                    return isCheck;
                }

                public void setCheck(boolean check) {
                    isCheck = check;
                }
            }
        }

        public static class ProductCustomAttrListEntity {
            private String attributeName;
            private String attributeValue;

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public void setAttributeValue(String attributeValue) {
                this.attributeValue = attributeValue;
            }

            public String getAttributeName() {
                return attributeName;
            }

            public String getAttributeValue() {
                return attributeValue;
            }
        }

        public static class FeedBackVOsEntity {
            private String feedBackId;
            private String orderNo;
            private String createTime;
            private String des;
            private String orderAttrValues;
            private String userId;
            private String userName;
            private String thumb;
            private List<String> imageUrlList;
            /**
             * afterId : 2551d6e82982401a9222fd34c67d0763
             * des : 很好
             * createTime : 2015-12-23 14:06:48
             * addDays : 0
             * imageUrlList : ["/upload/image/20151223/20151223140616_836.png","/upload/image/20151223/20151223140623_977.png","/upload/image/20151223/20151223140631_937.png","/upload/image/20151223/20151223140638_651.png","/upload/image/20151223/20151223140645_78.png"]
             */

            private List<AfterCommentEntity> afterComment;

            public void setFeedBackId(String feedBackId) {
                this.feedBackId = feedBackId;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public void setOrderAttrValues(String orderAttrValues) {
                this.orderAttrValues = orderAttrValues;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setImageUrlList(List<String> imageUrlList) {
                this.imageUrlList = imageUrlList;
            }

            public void setAfterComment(List<AfterCommentEntity> afterComment) {
                this.afterComment = afterComment;
            }

            public String getFeedBackId() {
                return feedBackId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getDes() {
                return des;
            }

            public String getOrderAttrValues() {
                return orderAttrValues;
            }

            public String getUserId() {
                return userId;
            }

            public String getUserName() {
                return userName;
            }

            public String getThumb() {
                return thumb;
            }

            public List<String> getImageUrlList() {
                return imageUrlList;
            }

            public List<AfterCommentEntity> getAfterComment() {
                return afterComment;
            }

            public static class AfterCommentEntity {
                private String afterId;
                private String des;
                private String createTime;
                private int addDays;
                private List<String> imageUrlList;

                public void setAfterId(String afterId) {
                    this.afterId = afterId;
                }

                public void setDes(String des) {
                    this.des = des;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public void setAddDays(int addDays) {
                    this.addDays = addDays;
                }

                public void setImageUrlList(List<String> imageUrlList) {
                    this.imageUrlList = imageUrlList;
                }

                public String getAfterId() {
                    return afterId;
                }

                public String getDes() {
                    return des;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public int getAddDays() {
                    return addDays;
                }

                public List<String> getImageUrlList() {
                    return imageUrlList;
                }
            }
        }
    }
}
