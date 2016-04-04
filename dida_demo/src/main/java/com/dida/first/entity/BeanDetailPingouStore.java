package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2015-12-28.
 */
public class BeanDetailPingouStore {
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


        private ComGroupDetailEntity comGroupDetail;
        private String sellerThumb;
        private List<String> imageJson;
        private List<String> productImgs;
        private List<CustomAttrsEntity> customAttrs;
        private List<PurchaseAttrsEntity> purchaseAttrs;
        public void setImageJson(List<String> imageJson) {
            this.imageJson = imageJson;
        }
        public List<String> getImageJson() {
            return imageJson;
        }

        public void setComGroupDetail(ComGroupDetailEntity comGroupDetail) {
            this.comGroupDetail = comGroupDetail;
        }

        public void setSellerThumb(String sellerThumb) {
            this.sellerThumb = sellerThumb;
        }

        public void setProductImgs(List<String> productImgs) {
            this.productImgs = productImgs;
        }

        public void setCustomAttrs(List<CustomAttrsEntity> customAttrs) {
            this.customAttrs = customAttrs;
        }

        public void setPurchaseAttrs(List<PurchaseAttrsEntity> purchaseAttrs) {
            this.purchaseAttrs = purchaseAttrs;
        }

        public ComGroupDetailEntity getComGroupDetail() {
            return comGroupDetail;
        }

        public String getSellerThumb() {
            return sellerThumb;
        }

        public List<String> getProductImgs() {
            return productImgs;
        }

        public List<CustomAttrsEntity> getCustomAttrs() {
            return customAttrs;
        }

        public List<PurchaseAttrsEntity> getPurchaseAttrs() {
            return purchaseAttrs;
        }

        public static class ComGroupDetailEntity {
            private String mobileThumb;
            private String mobileDes;
            private String shareLink;
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
            private List<ParticipatesEntity> participates;
            private List<ReplysEntity> replys;

            public String getShareLink() {
                return shareLink;
            }

            public void setShareLink(String shareLink) {
                this.shareLink = shareLink;
            }

            public String getMobileThumb() {
                return mobileThumb;
            }

            public void setMobileThumb(String mobileThumb) {
                this.mobileThumb = mobileThumb;
            }

            public String getMobileDes() {
                return mobileDes;
            }

            public void setMobileDes(String mobileDes) {
                this.mobileDes = mobileDes;
            }

            public static class ParticipatesEntity implements Serializable {
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

            public static class ReplysEntity {
                private String userId;
                private int floorNo;
                private int replyId;
                private int hasNode;
                private String content;
                private String createTime;
                private String userName;
                private String thumb;
                private List<String> replyThumbList;
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

        public static class CustomAttrsEntity {
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

        public static class PurchaseAttrsEntity {
            private String attrId;
            private String productNo;
            private String attributeName;
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
    }
}
