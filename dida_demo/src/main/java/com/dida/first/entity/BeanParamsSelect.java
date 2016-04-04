/**
 * 
 */
package com.dida.first.entity;

import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-9-28 上午9:27:33 
 * @use			
 *
 */
public class BeanParamsSelect {
	public String selectString;
	public List<ParamItem> paramList;
	/**
	 * @param paramName
	 * @param paramList
	 */
	public BeanParamsSelect(String paramName, List<ParamItem> paramList) {
		this.selectString = paramName;
		this.paramList = paramList;
	}
	public BeanParamsSelect() {
	}
	public class ParamItem{
		public String paramString;
		public boolean isCheck;
		public ParamItem(String paramString,boolean isCheck) {
			this.paramString=paramString;
			this.isCheck=isCheck;
		}
		
		
	}
	
}
