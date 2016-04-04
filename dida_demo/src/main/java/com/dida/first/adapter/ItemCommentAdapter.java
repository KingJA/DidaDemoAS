package com.dida.first.adapter;

import java.util.List;

import com.dida.first.entity.CommentBean.ItemComment;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.ItemCommentHolder;

/**
 * @author		KingJA 
 * @data		2015-8-20 上午10:19:12 
 * @use			
 *
 */
public class ItemCommentAdapter extends MyBaseAdapter<ItemComment> {

	/**
	 * @param list
	 */
	public ItemCommentAdapter(List<ItemComment> list) {
		super(list); 
	}

	@Override
	public BaseHolder<ItemComment> getHolder() {
		return new ItemCommentHolder();
	}}
