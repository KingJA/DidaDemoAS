package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanPingouComment;
import com.dida.first.utils.FrescoManager;
import com.dida.first.utils.TimeUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by KingJA on 2016-1-8.
 * 拼购评论列表
 */
public class PingouCommentInnerAdapter extends BaseLvGvAdapter<BeanPingouComment.ResEntity.ReplyListEntity.SubReplysEntity> {


    public PingouCommentInnerAdapter(List<BeanPingouComment.ResEntity.ReplyListEntity.SubReplysEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=View.inflate(activity, R.layout.include_commenet_pingou_in,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvcommentpingouinreplyFrom.setText(list.get(position).getUserName());
        viewHolder.tvcommentpingouinreplyTo.setText(list.get(position).getReplyedUserName());
        viewHolder.tvcommentpingouincontent.setText(list.get(position).getContent());
        viewHolder.tvcommentpingouindate.setText(TimeUtils.getDayTime(list.get(position).getCreateTime()));
        FrescoManager.display(list.get(position).getThumb(),viewHolder.civcommentpingouinicon);

        return convertView;
    }


    public class ViewHolder {
        public final SimpleDraweeView civcommentpingouinicon;
        public final TextView tvcommentpingouinreplyFrom;
        public final TextView tvcommentpingouinreplyTo;
        public final TextView tvcommentpingouincontent;
        public final TextView tvcommentpingouindate;
        public final View root;

        public ViewHolder(View root) {
            civcommentpingouinicon = (SimpleDraweeView) root.findViewById(R.id.civ_comment_pingou_in_icon);
            tvcommentpingouinreplyFrom = (TextView) root.findViewById(R.id.tv_comment_pingou_in_replyFrom);
            tvcommentpingouinreplyTo = (TextView) root.findViewById(R.id.tv_comment_pingou_in_replyTo);
            tvcommentpingouincontent = (TextView) root.findViewById(R.id.tv_comment_pingou_in_content);
            tvcommentpingouindate = (TextView) root.findViewById(R.id.tv_comment_pingou_in_date);
            this.root = root;
        }
    }
}
