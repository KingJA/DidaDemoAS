package com.dida.first.entity;

import java.util.List;

/**
 * Created by KingJA on 2016-1-15.
 */
public class BeanSearchMarket  {


    private int code;

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

        private List<ProductAdsEntity> productAds;

        private List<ProductsEntity> products;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setProductAds(List<ProductAdsEntity> productAds) {
            this.productAds = productAds;
        }

        public void setProducts(List<ProductsEntity> products) {
            this.products = products;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public List<ProductAdsEntity> getProductAds() {
            return productAds;
        }

        public List<ProductsEntity> getProducts() {
            return products;
        }

        public static class ProductAdsEntity {
            private String adName;
            private String adUrl;
            private String adLink;

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public void setAdUrl(String adUrl) {
                this.adUrl = adUrl;
            }

            public void setAdLink(String adLink) {
                this.adLink = adLink;
            }

            public String getAdName() {
                return adName;
            }

            public String getAdUrl() {
                return adUrl;
            }

            public String getAdLink() {
                return adLink;
            }
        }

        public static class ProductsEntity {
            private String productNo;
            private String thumb;
            private String name;
            private double price;
            private int salesCount;
            private int productType;

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setSalesCount(int salesCount) {
                this.salesCount = salesCount;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public String getProductNo() {
                return productNo;
            }

            public String getThumb() {
                return thumb;
            }

            public String getName() {
                return name;
            }

            public double getPrice() {
                return price;
            }

            public int getSalesCount() {
                return salesCount;
            }

            public int getProductType() {
                return productType;
            }
        }
    }
}
