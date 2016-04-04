package com.dida.first.entity;

import java.util.List;
/**
 * 
 * @author		KingJA 
 * @data		2015-8-11 下午4:18:09 
 * @use			
 *
 */

public class YaoYueBean{
	public Integer code;
	public String msg;
	public List<Res> res;

	public class Res {

		public int serviceId;// 任务分享ID
		public String name;   
		public String pwd;             //AA密码  （AA专用）
		public String des;             //描述
		public double price;           //价格
			//JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
		public String createTime;
			//JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
		public String customDueDate;	    //截止时间
		public Integer isDeliveryFree; //是否包邮 1是 0否
		public Integer groupType;      //团购类型   商家发布  还是用户发布
		public String userName;        //用户名称
		public String thumb;           //用户头像
		public String productThumb;    //产品图片
		public String shopId;          //商店Id
		public String userId;//用户Id
		public Integer taskCount;   //评论数
		public Integer newCount;        //购买的数量
		public Integer state;
		public String purchaseId;//购买ID
		public Integer GroupStatus;
		public String GroupStatusText;    //字典11
		public Integer status;//状态
		public String statusText;

	}

}
