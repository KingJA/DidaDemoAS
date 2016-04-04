package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2016-1-27.
 */
public class BeanMinePingou {

    /**
     * code : 1
     * msg : 加载拼购成功
     * res : {"groupList":[{"serviceId":2028,"groupMode":0,"createTime":"2015-12-25 14:10","status":1,"thumb":"/upload/image/20151128/20151128112539_751.jpg","serviceName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","pureDes":"市场上的保暖内衣大部分是絮片型，即两层针织布中间填充化纤原料制成絮片，表层轧出花纹。质量不好的洗涤后易变形，中间絮片易滑动。最好选择里外一体的产品，这种保暖","count":10,"price":0.02,"statusText":"审核通过"},{"serviceId":2019,"groupMode":0,"createTime":"2015-12-11 10:28","status":1,"thumb":"/upload/image/20151128/20151128104724_140.jpg","serviceName":"粉红大布娃娃","pureDes":"","count":10,"price":0.02,"statusText":"审核通过"},{"serviceId":2017,"groupMode":0,"createTime":"2015-12-08 16:49","status":1,"thumb":"/upload/image/20151128/20151128102908_576.jpg","serviceName":"2015冬季新款长袖网纱拼接打底衫上衣韩版","pureDes":"","count":10,"price":0.02,"statusText":"审核通过"}],"pages":1,"rowCount":3}
     */

    private int code;
    private String msg;
    /**
     * groupList : [{"serviceId":2028,"groupMode":0,"createTime":"2015-12-25 14:10","status":1,"thumb":"/upload/image/20151128/20151128112539_751.jpg","serviceName":"梵希蔓2015秋冬新款 淑女气质高领长袖针织衫","pureDes":"市场上的保暖内衣大部分是絮片型，即两层针织布中间填充化纤原料制成絮片，表层轧出花纹。质量不好的洗涤后易变形，中间絮片易滑动。最好选择里外一体的产品，这种保暖","count":10,"price":0.02,"statusText":"审核通过"},{"serviceId":2019,"groupMode":0,"createTime":"2015-12-11 10:28","status":1,"thumb":"/upload/image/20151128/20151128104724_140.jpg","serviceName":"粉红大布娃娃","pureDes":"","count":10,"price":0.02,"statusText":"审核通过"},{"serviceId":2017,"groupMode":0,"createTime":"2015-12-08 16:49","status":1,"thumb":"/upload/image/20151128/20151128102908_576.jpg","serviceName":"2015冬季新款长袖网纱拼接打底衫上衣韩版","pureDes":"","count":10,"price":0.02,"statusText":"审核通过"}]
     * pages : 1
     * rowCount : 3
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
         * serviceId : 2028
         * groupMode : 0
         * createTime : 2015-12-25 14:10
         * status : 1
         * thumb : /upload/image/20151128/20151128112539_751.jpg
         * serviceName : 梵希蔓2015秋冬新款 淑女气质高领长袖针织衫
         * pureDes : 市场上的保暖内衣大部分是絮片型，即两层针织布中间填充化纤原料制成絮片，表层轧出花纹。质量不好的洗涤后易变形，中间絮片易滑动。最好选择里外一体的产品，这种保暖
         * count : 10
         * price : 0.02
         * statusText : 审核通过
         */

        private List<GroupListEntity> groupList;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setGroupList(List<GroupListEntity> groupList) {
            this.groupList = groupList;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<GroupListEntity> getGroupList() {
            return groupList;
        }

        public static class GroupListEntity {
            private int serviceId;
            private int groupMode;
            private String createTime;
            private int status;
            private String thumb;
            private String serviceName;
            private String pureDes;
            private int count;
            private int joinCount;
            private int type;
            private double price;
            private String statusText;

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public void setPureDes(String pureDes) {
                this.pureDes = pureDes;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setStatusText(String statusText) {
                this.statusText = statusText;
            }

            public int getServiceId() {
                return serviceId;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public String getCreateTime() {
                return createTime;
            }

            public int getStatus() {
                return status;
            }

            public String getThumb() {
                return thumb;
            }

            public String getServiceName() {
                return serviceName;
            }

            public String getPureDes() {
                return pureDes;
            }

            public int getCount() {
                return count;
            }

            public double getPrice() {
                return price;
            }

            public String getStatusText() {
                return statusText;
            }

            public int getJoinCount() {
                return joinCount;
            }

            public void setJoinCount(int joinCount) {
                this.joinCount = joinCount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
