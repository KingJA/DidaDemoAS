package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2015-12-28.
 */
public class BeanDetailPingouUser {


    /**
     * code : 1
     * msg : 详情加载成功
     * res : {"prepayorders":[{"pId":"1449730580534d0adee0edadc4beb896","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449730580534d0adee0edadc4beb896","productId":"f89e751773af47fda7f3added531c7a4","productType":1,"productThumb":"/upload/image/20151128/20151128102908_576.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮","orderattrvalue":"颜色:黑色 尺寸:XL "},{"pId":"1449815622066b0869cc430454b2291e","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815622066b0869cc430454b2291e","productId":"2534e67cd3ef4437b539b10e7bbc92bb","productType":1,"productThumb":"/upload/image/20151128/20151128100542_966.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫 百搭打底衫毛衣女","orderattrvalue":"尺码:XL 颜色:白色 "}],"userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","pureDes":"市场上的保暖内衣大部分是絮片型，即两层针织布中间填充化纤原料制成絮片，表层轧出花纹。质量不好的洗涤后易变形，中间絮片易滑动。最好选择里外一体的产品，这种保暖","imageJson":["http://121.40.28.206/upload/image/20151225/1451023809275066934.png","http://121.40.28.206/upload/image/20151225/1451023809275036766.jpg","http://121.40.28.206/upload/image/20151225/1451023809281063709.png","http://121.40.28.206/upload/image/20151225/1451023809352069807.png","http://121.40.28.206/upload/image/20151225/1451023809761057499.png"],"sex":1,"userCredits":4,"comGroupDetail":{"serviceId":2028,"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","isCollection":0,"groupMode":0,"groupName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","price":0.02,"oldPrice":0.02,"count":10,"buyCount":4,"taskCount":5,"customDueDate":"2016-08-27 14:10:00","participates":[{"userId":"13","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg","sex":1},{"userId":"58","userName":"hhh","thumb":"/upload/1.jpg","sex":1},{"userId":"b99e6df0f6b1406fad0467b79258e742","userName":"love8510","thumb":"/frontend/images/recommend-head3.jpg","sex":1}],"replys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":5,"replyId":410,"hasNode":1,"content":"我是第五楼。","createTime":"2015-12-29 16:43:39","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumb":"/upload/image/20151229/20151229165401_242.png,/upload/image/20151229/20151229165412_124.png,/upload/image/20151229/20151229165423_206.png,/upload/image/20151229/20151229165435_907.png,/upload/image/20151229/20151229165449_380.png,","replyThumbList":["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"],"subReplys":[{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]}]}}
     */

    private int code;
    private String msg;
    /**
     * prepayorders : [{"pId":"1449730580534d0adee0edadc4beb896","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449730580534d0adee0edadc4beb896","productId":"f89e751773af47fda7f3added531c7a4","productType":1,"productThumb":"/upload/image/20151128/20151128102908_576.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮","orderattrvalue":"颜色:黑色 尺寸:XL "},{"pId":"1449815622066b0869cc430454b2291e","userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","orderNo":"1449815622066b0869cc430454b2291e","productId":"2534e67cd3ef4437b539b10e7bbc92bb","productType":1,"productThumb":"/upload/image/20151128/20151128100542_966.jpg","count":1,"price":0.01,"totalPrice":0.01,"orderName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫 百搭打底衫毛衣女","orderattrvalue":"尺码:XL 颜色:白色 "}]
     * userName : wujie0209
     * thumb : /frontend/images/recommend-head4.jpg
     * pureDes : 市场上的保暖内衣大部分是絮片型，即两层针织布中间填充化纤原料制成絮片，表层轧出花纹。质量不好的洗涤后易变形，中间絮片易滑动。最好选择里外一体的产品，这种保暖
     * imageJson : ["http://121.40.28.206/upload/image/20151225/1451023809275066934.png","http://121.40.28.206/upload/image/20151225/1451023809275036766.jpg","http://121.40.28.206/upload/image/20151225/1451023809281063709.png","http://121.40.28.206/upload/image/20151225/1451023809352069807.png","http://121.40.28.206/upload/image/20151225/1451023809761057499.png"]
     * sex : 1
     * userCredits : 4.0
     * comGroupDetail : {"serviceId":2028,"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","isCollection":0,"groupMode":0,"groupName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","price":0.02,"oldPrice":0.02,"count":10,"buyCount":4,"taskCount":5,"customDueDate":"2016-08-27 14:10:00","participates":[{"userId":"13","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg","sex":1},{"userId":"58","userName":"hhh","thumb":"/upload/1.jpg","sex":1},{"userId":"b99e6df0f6b1406fad0467b79258e742","userName":"love8510","thumb":"/frontend/images/recommend-head3.jpg","sex":1}],"replys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":5,"replyId":410,"hasNode":1,"content":"我是第五楼。","createTime":"2015-12-29 16:43:39","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumb":"/upload/image/20151229/20151229165401_242.png,/upload/image/20151229/20151229165412_124.png,/upload/image/20151229/20151229165423_206.png,/upload/image/20151229/20151229165435_907.png,/upload/image/20151229/20151229165449_380.png,","replyThumbList":["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"],"subReplys":[{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]}]}
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
        private String userName;
        private String thumb;
        private String pureDes;
        private int sex;
        private double userCredits;
        /**
         * serviceId : 2028
         * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
         * isCollection : 0
         * groupMode : 0
         * groupName : 梵希蔓2015秋冬新款 淑女气质高领长袖针织衫
         * price : 0.02
         * oldPrice : 0.02
         * count : 10
         * buyCount : 4
         * taskCount : 5
         * customDueDate : 2016-08-27 14:10:00
         * participates : [{"userId":"13","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg","sex":1},{"userId":"58","userName":"hhh","thumb":"/upload/1.jpg","sex":1},{"userId":"b99e6df0f6b1406fad0467b79258e742","userName":"love8510","thumb":"/frontend/images/recommend-head3.jpg","sex":1}]
         * replys : [{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":5,"replyId":410,"hasNode":1,"content":"我是第五楼。","createTime":"2015-12-29 16:43:39","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumb":"/upload/image/20151229/20151229165401_242.png,/upload/image/20151229/20151229165412_124.png,/upload/image/20151229/20151229165423_206.png,/upload/image/20151229/20151229165435_907.png,/upload/image/20151229/20151229165449_380.png,","replyThumbList":["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"],"subReplys":[{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]}]
         */

        private ComGroupDetailEntity comGroupDetail;
        /**
         * pId : 1449730580534d0adee0edadc4beb896
         * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
         * orderNo : 1449730580534d0adee0edadc4beb896
         * productId : f89e751773af47fda7f3added531c7a4
         * productType : 1
         * productThumb : /upload/image/20151128/20151128102908_576.jpg
         * count : 1
         * price : 0.01
         * totalPrice : 0.01
         * orderName : 毛衣女2015冬季新款长袖网纱拼接打底衫上衣韩版修身套头针织衫潮
         * orderattrvalue : 颜色:黑色 尺寸:XL
         */

        private List<PrepayordersEntity> prepayorders;
        private List<String> imageJson;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setPureDes(String pureDes) {
            this.pureDes = pureDes;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public void setUserCredits(double userCredits) {
            this.userCredits = userCredits;
        }

        public void setComGroupDetail(ComGroupDetailEntity comGroupDetail) {
            this.comGroupDetail = comGroupDetail;
        }

        public void setPrepayorders(List<PrepayordersEntity> prepayorders) {
            this.prepayorders = prepayorders;
        }

        public void setImageJson(List<String> imageJson) {
            this.imageJson = imageJson;
        }

        public String getUserName() {
            return userName;
        }

        public String getThumb() {
            return thumb;
        }

        public String getPureDes() {
            return pureDes;
        }

        public int getSex() {
            return sex;
        }

        public double getUserCredits() {
            return userCredits;
        }

        public ComGroupDetailEntity getComGroupDetail() {
            return comGroupDetail;
        }

        public List<PrepayordersEntity> getPrepayorders() {
            return prepayorders;
        }

        public List<String> getImageJson() {
            return imageJson;
        }

        public static class ComGroupDetailEntity {
            private int serviceId;
            private String userId;
            private int isCollection;
            private int groupMode;
            private String groupName;
            private double price;
            private double oldPrice;
            private int count;
            private int buyCount;
            private int taskCount;
            private String customDueDate;
            /**
             * userId : 13
             * userName : admin
             * thumb : /upload/145145917901652e9b4bf7b83.jpg
             * sex : 1
             */

            private List<ParticipatesEntity> participates;
            /**
             * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
             * floorNo : 5
             * replyId : 410
             * hasNode : 1
             * content : 我是第五楼。
             * createTime : 2015-12-29 16:43:39
             * userName : wujie0209
             * thumb : /frontend/images/recommend-head4.jpg
             * replyThumb : /upload/image/20151229/20151229165401_242.png,/upload/image/20151229/20151229165412_124.png,/upload/image/20151229/20151229165423_206.png,/upload/image/20151229/20151229165435_907.png,/upload/image/20151229/20151229165449_380.png,
             * replyThumbList : ["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"]
             * subReplys : [{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]
             */

            private List<ReplysEntity> replys;

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setOldPrice(double oldPrice) {
                this.oldPrice = oldPrice;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

            public void setTaskCount(int taskCount) {
                this.taskCount = taskCount;
            }

            public void setCustomDueDate(String customDueDate) {
                this.customDueDate = customDueDate;
            }

            public void setParticipates(List<ParticipatesEntity> participates) {
                this.participates = participates;
            }

            public void setReplys(List<ReplysEntity> replys) {
                this.replys = replys;
            }

            public int getServiceId() {
                return serviceId;
            }

            public String getUserId() {
                return userId;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public String getGroupName() {
                return groupName;
            }

            public double getPrice() {
                return price;
            }

            public double getOldPrice() {
                return oldPrice;
            }

            public int getCount() {
                return count;
            }

            public int getBuyCount() {
                return buyCount;
            }

            public int getTaskCount() {
                return taskCount;
            }

            public String getCustomDueDate() {
                return customDueDate;
            }

            public List<ParticipatesEntity> getParticipates() {
                return participates;
            }

            public List<ReplysEntity> getReplys() {
                return replys;
            }

            public static class ParticipatesEntity implements Serializable{
                private String userId;
                private String userName;
                private String thumb;
                private int sex;

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setSex(int sex) {
                    this.sex = sex;
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

                public int getSex() {
                    return sex;
                }
            }

            public static class ReplysEntity {
                private String userId;
                private int floorNo;
                private int replyId;
                private int hasNode;
                private String content;
                private String createTime;
                private String userName;
                private String thumb;
                private String replyThumb;
                private List<String> replyThumbList;
                /**
                 * userId : 13
                 * replyedUserName : wujie0209
                 * parentId : 410
                 * replyId : 411
                 * hasNode : 1
                 * content : 地方郭德纲的非官方
                 * createTime : 2015-12-29 16:47:46
                 * userName : admin
                 * thumb : /upload/145145917901652e9b4bf7b83.jpg
                 */

                private List<SubReplysEntity> subReplys;

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setFloorNo(int floorNo) {
                    this.floorNo = floorNo;
                }

                public void setReplyId(int replyId) {
                    this.replyId = replyId;
                }

                public void setHasNode(int hasNode) {
                    this.hasNode = hasNode;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setReplyThumb(String replyThumb) {
                    this.replyThumb = replyThumb;
                }

                public void setReplyThumbList(List<String> replyThumbList) {
                    this.replyThumbList = replyThumbList;
                }

                public void setSubReplys(List<SubReplysEntity> subReplys) {
                    this.subReplys = subReplys;
                }

                public String getUserId() {
                    return userId;
                }

                public int getFloorNo() {
                    return floorNo;
                }

                public int getReplyId() {
                    return replyId;
                }

                public int getHasNode() {
                    return hasNode;
                }

                public String getContent() {
                    return content;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public String getUserName() {
                    return userName;
                }

                public String getThumb() {
                    return thumb;
                }

                public String getReplyThumb() {
                    return replyThumb;
                }

                public List<String> getReplyThumbList() {
                    return replyThumbList;
                }

                public List<SubReplysEntity> getSubReplys() {
                    return subReplys;
                }

                public static class SubReplysEntity {
                    private String userId;
                    private String replyedUserName;
                    private int parentId;
                    private int replyId;
                    private int hasNode;
                    private String content;
                    private String createTime;
                    private String userName;
                    private String thumb;

                    public void setUserId(String userId) {
                        this.userId = userId;
                    }

                    public void setReplyedUserName(String replyedUserName) {
                        this.replyedUserName = replyedUserName;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public void setReplyId(int replyId) {
                        this.replyId = replyId;
                    }

                    public void setHasNode(int hasNode) {
                        this.hasNode = hasNode;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public void setUserName(String userName) {
                        this.userName = userName;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getUserId() {
                        return userId;
                    }

                    public String getReplyedUserName() {
                        return replyedUserName;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public int getReplyId() {
                        return replyId;
                    }

                    public int getHasNode() {
                        return hasNode;
                    }

                    public String getContent() {
                        return content;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public String getUserName() {
                        return userName;
                    }

                    public String getThumb() {
                        return thumb;
                    }
                }
            }
        }

        public static class PrepayordersEntity {
            private String pId;
            private String userId;
            private String orderNo;
            private String productId;
            private int productType;
            private String productThumb;
            private int count;
            private double price;
            private double totalPrice;
            private String orderName;
            private String orderattrvalue;

            public void setPId(String pId) {
                this.pId = pId;
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

            public void setProductType(int productType) {
                this.productType = productType;
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

            public String getPId() {
                return pId;
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

            public int getProductType() {
                return productType;
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
    }
}
