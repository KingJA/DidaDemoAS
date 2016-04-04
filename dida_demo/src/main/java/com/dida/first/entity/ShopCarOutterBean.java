/**
 * 
 */
package com.dida.first.entity;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-2 上午10:16:47
 * @use
 * 
 */
public class ShopCarOutterBean {
	public String shopName;
	public List<ShopCarInnerBean> innerBeanList;

	public ShopCarOutterBean(String shopName) {
		this.shopName = shopName;
	}

	public class ShopCarInnerBean {
		public String title;
		public String param;
		public String price;
		public String oldPrice;
		public Integer count;

		public ShopCarInnerBean(String title, String param, String price,
				String oldPrice, Integer count) {
			this.title=title;
			this.param=param;
			this.price=price;
			this.oldPrice=oldPrice;
			this.count=count;
		}
	}

}
