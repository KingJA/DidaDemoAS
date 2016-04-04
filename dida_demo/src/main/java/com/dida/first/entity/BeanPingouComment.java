package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2016-1-8.
 */
public class BeanPingouComment {

    /**
     * code : 1
     * msg : 加载第1页评论成功
     * res : {"replyList":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":5,"replyId":410,"hasNode":1,"content":"我是第五楼。","createTime":"2015-12-29 16:43:39","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"],"subReplys":[{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":4,"replyId":406,"hasNode":1,"content":"测试评论","createTime":"2015-12-28 13:49:05","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":[],"subReplys":[]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":3,"replyId":405,"hasNode":1,"content":"dsfsdfsdfds","createTime":"2015-12-26 17:12:41","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":[],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":405,"replyId":407,"hasNode":1,"content":"测试评论","createTime":"2015-12-28 13:50:45","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":407,"replyId":409,"hasNode":0,"content":"","createTime":"2015-12-28 13:53:29","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":2,"replyId":402,"hasNode":1,"content":"地板","createTime":"2015-12-25 14:22:16","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151225/20151225142214_150.png"],"subReplys":[]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":1,"replyId":397,"hasNode":1,"content":"沙发","createTime":"2015-12-25 14:21:20","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151225/20151225142113_588.jpg"],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":397,"replyId":398,"hasNode":1,"content":"呵呵","createTime":"2015-12-25 14:21:30","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":398,"replyId":399,"hasNode":0,"content":"哈哈","createTime":"2015-12-25 14:21:36","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":398,"replyId":401,"hasNode":0,"content":"。。。","createTime":"2015-12-25 14:21:53","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":397,"replyId":400,"hasNode":1,"content":"嘿嘿","createTime":"2015-12-25 14:21:45","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]}],"pages":1,"rowCount":5}
     */

    private int code;
    private String msg;
    /**
     * replyList : [{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":5,"replyId":410,"hasNode":1,"content":"我是第五楼。","createTime":"2015-12-29 16:43:39","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"],"subReplys":[{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":4,"replyId":406,"hasNode":1,"content":"测试评论","createTime":"2015-12-28 13:49:05","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":[],"subReplys":[]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":3,"replyId":405,"hasNode":1,"content":"dsfsdfsdfds","createTime":"2015-12-26 17:12:41","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":[],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":405,"replyId":407,"hasNode":1,"content":"测试评论","createTime":"2015-12-28 13:50:45","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":407,"replyId":409,"hasNode":0,"content":"","createTime":"2015-12-28 13:53:29","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":2,"replyId":402,"hasNode":1,"content":"地板","createTime":"2015-12-25 14:22:16","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151225/20151225142214_150.png"],"subReplys":[]},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","floorNo":1,"replyId":397,"hasNode":1,"content":"沙发","createTime":"2015-12-25 14:21:20","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg","replyThumbList":["/upload/image/20151225/20151225142113_588.jpg"],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":397,"replyId":398,"hasNode":1,"content":"呵呵","createTime":"2015-12-25 14:21:30","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":398,"replyId":399,"hasNode":0,"content":"哈哈","createTime":"2015-12-25 14:21:36","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":398,"replyId":401,"hasNode":0,"content":"。。。","createTime":"2015-12-25 14:21:53","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"wujie0209","parentId":397,"replyId":400,"hasNode":1,"content":"嘿嘿","createTime":"2015-12-25 14:21:45","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]}]
     * pages : 1
     * rowCount : 5
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
         * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
         * floorNo : 5
         * replyId : 410
         * hasNode : 1
         * content : 我是第五楼。
         * createTime : 2015-12-29 16:43:39
         * userName : wujie0209
         * thumb : /frontend/images/recommend-head4.jpg
         * replyThumbList : ["/upload/image/20151229/20151229165401_242.png","/upload/image/20151229/20151229165412_124.png","/upload/image/20151229/20151229165423_206.png","/upload/image/20151229/20151229165435_907.png","/upload/image/20151229/20151229165449_380.png"]
         * subReplys : [{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":411,"hasNode":1,"content":"地方郭德纲的非官方","createTime":"2015-12-29 16:47:46","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":411,"replyId":413,"hasNode":0,"content":"啥地方撒打算地方撒","createTime":"2015-12-29 16:48:50","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":411,"replyId":415,"hasNode":0,"content":"啥地方公司大概啥地方","createTime":"2015-12-29 16:50:16","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":410,"replyId":412,"hasNode":1,"content":"放大和广泛地和天高地厚","createTime":"2015-12-29 16:48:05","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"},{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"admin","parentId":412,"replyId":414,"hasNode":0,"content":"啥地方撒打算放","createTime":"2015-12-29 16:48:59","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"},{"userId":"13","replyedUserName":"wujie0209","parentId":412,"replyId":416,"hasNode":0,"content":"旧共和国交换机","createTime":"2015-12-29 16:50:27","userName":"admin","thumb":"/upload/145145917901652e9b4bf7b83.jpg"}]
         */

        private List<ReplyListEntity> replyList;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setReplyList(List<ReplyListEntity> replyList) {
            this.replyList = replyList;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<ReplyListEntity> getReplyList() {
            return replyList;
        }

        public static class ReplyListEntity {
            private String userId;
            private int floorNo;
            private int replyId;
            private int hasNode;
            private String content;
            private String createTime;
            private String userName;
            private String thumb;
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
}
