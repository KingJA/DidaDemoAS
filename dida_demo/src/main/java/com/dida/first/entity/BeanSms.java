/**
 * 
 */
package com.dida.first.entity;

/**
 * @author		KingJA 
 * @data		2015-10-21 上午9:30:58 
 * @use			
 *
 */
public class BeanSms {
	private int code;
	private String msg;
	private String res;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the res
	 */
	public String getRes() {
		return res;
	}
	/**
	 * @param res the res to set
	 */
	public void setRes(String res) {
		this.res = res;
	}
	@Override
	public String toString() {
		return "BeanSms [code=" + code + ", msg=" + msg + ", res=" + res + "]";
	}

	
}
