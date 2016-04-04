/**
 * 
 */
package com.dida.first.holder;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.CommentBean.ItemComment;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-8-20 上午10:30:52 
 * @use			
 *
 */
public class ItemCommentHolder extends BaseHolder<ItemComment> {
	
	private TextView tv_itemcomment_content;
	private SpannableStringBuilder content;
	private ForegroundColorSpan colorFrom;
	private ForegroundColorSpan colorTo;

	public ItemCommentHolder() {
		super();
		content = new SpannableStringBuilder();  
		colorFrom = new ForegroundColorSpan(Color.parseColor("#576B95"));    
		colorTo = new ForegroundColorSpan(Color.parseColor("#576B95"));    
	}
	@Override
	public View initView() {
		View initView=UIUtils.inflate(R.layout.item_group_detail_comment_itemcomment);
		tv_itemcomment_content = (TextView) initView.findViewById(R.id.tv_itemcomment_content);
		return initView;
	}

	@Override
	public void refreshView() {
		ItemComment itemComment = getData();
		content.clear();
		SpannableString fromString = new SpannableString(itemComment.fromName);
		SpannableString toString = new SpannableString(itemComment.toName);    
		fromString.setSpan(colorFrom, 0, itemComment.fromName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   
		toString.setSpan(colorTo, 0, itemComment.toName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   
		content.append(fromString);
		content.append("回复");
		content.append(toString);
		content.append(":");
		content.append(itemComment.itemComment);
		tv_itemcomment_content.setText(content);

	}

}
