package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2016-1-18.
 */
public class BeanAddressList {

    /**
     * code : 1
     * res : {"pages":1,"deliveryAdressList":[{"deliveryAddressId":"018084b8b1eb4af9b3da1841378fa12b","receiverName":"悟空","mobileNo":"18888888888","detailAddress":"中南海1号","isDefault":1,"provinceId":"330000","cityId":"330300","strictId":"330381","provinceName":"浙江省","cityName":"温州市","strictName":"瑞安市"},{"deliveryAddressId":"f13c03110024493da73425786ac0498d","receiverName":"吴杰","mobileNo":"15858713170","detailAddress":"文昌路红连文创园811室","isDefault":1,"provinceId":"330000","cityId":"330300","strictId":"330303","provinceName":"浙江省","cityName":"温州市","strictName":"龙湾区"}],"rowCount":2}
     */

    private int code;
    /**
     * pages : 1
     * deliveryAdressList : [{"deliveryAddressId":"018084b8b1eb4af9b3da1841378fa12b","receiverName":"悟空","mobileNo":"18888888888","detailAddress":"中南海1号","isDefault":1,"provinceId":"330000","cityId":"330300","strictId":"330381","provinceName":"浙江省","cityName":"温州市","strictName":"瑞安市"},{"deliveryAddressId":"f13c03110024493da73425786ac0498d","receiverName":"吴杰","mobileNo":"15858713170","detailAddress":"文昌路红连文创园811室","isDefault":1,"provinceId":"330000","cityId":"330300","strictId":"330303","provinceName":"浙江省","cityName":"温州市","strictName":"龙湾区"}]
     * rowCount : 2
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
         * deliveryAddressId : 018084b8b1eb4af9b3da1841378fa12b
         * receiverName : 悟空
         * mobileNo : 18888888888
         * detailAddress : 中南海1号
         * isDefault : 1
         * provinceId : 330000
         * cityId : 330300
         * strictId : 330381
         * provinceName : 浙江省
         * cityName : 温州市
         * strictName : 瑞安市
         */

        private List<DeliveryAdressListEntity> deliveryAdressList;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setDeliveryAdressList(List<DeliveryAdressListEntity> deliveryAdressList) {
            this.deliveryAdressList = deliveryAdressList;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<DeliveryAdressListEntity> getDeliveryAdressList() {
            return deliveryAdressList;
        }

        public static class DeliveryAdressListEntity implements Serializable{
            private String deliveryAddressId;
            private String receiverName;
            private String mobileNo;
            private String detailAddress;
            private int isDefault;
            private String provinceId;
            private String cityId;
            private String strictId;
            private String provinceName;
            private String cityName;
            private String strictName;

            public void setDeliveryAddressId(String deliveryAddressId) {
                this.deliveryAddressId = deliveryAddressId;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public void setStrictId(String strictId) {
                this.strictId = strictId;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public void setStrictName(String strictName) {
                this.strictName = strictName;
            }

            public String getDeliveryAddressId() {
                return deliveryAddressId;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public String getCityId() {
                return cityId;
            }

            public String getStrictId() {
                return strictId;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public String getCityName() {
                return cityName;
            }

            public String getStrictName() {
                return strictName;
            }
        }
    }
}
