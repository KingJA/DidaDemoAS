/**
 * 
 */
package com.dida.first.entity;

import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-8-19 下午1:08:37 
 * @use			
 *
 */
public class CommentBean {
	public String ionUrl;
	public String rootName;
	public String rootComment;
	public String rootDate;
	public List<ItemComment> itemComments;
	public CommentBean(String rootName,String rootComment,String date) {
		
		this.rootName=rootName;
		this.rootComment=rootComment;
		this.rootDate=date;
		
	}
	
	public class ItemComment{
		public ItemComment(String fromName,String toName,String itemComment,String itemDate) {
			this.fromName=fromName;
			this.toName=toName;
			this.itemComment=itemComment;
			this.itemDate=itemDate;
		}
		public String fromName;
		public String toName;
		public String itemComment;
		public String itemDate;
		
	}

}
