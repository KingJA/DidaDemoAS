package com.dida.first.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanPingouComment;
import com.dida.first.utils.FrescoManager;
import com.dida.first.utils.TimeUtils;
import com.dida.first.view.AbsListView.MyGridView;
import com.dida.first.view.AbsListView.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by KingJA on 2016-1-8.
 * 拼购评论列表
 */
public class PingouCommentAdapter extends BaseLvGvAdapter<BeanPingouComment.ResEntity.ReplyListEntity> {

    private Uri imageUri;

    public PingouCommentAdapter(List<BeanPingouComment.ResEntity.ReplyListEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=View.inflate(activity, R.layout.include_comment_pingou,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //初始化
        viewHolder.mlvpingoudetailcommentsecond.setVisibility(View.GONE);
        //填充数据
        FrescoManager.display(list.get(position).getThumb(),viewHolder.civpingoudetailcommenticon);
        viewHolder.tvpingoudetailcommentname.setText(list.get(position).getUserName());
        viewHolder.tvpingoudetailcommentfloor.setText(list.get(position).getFloorNo()+"楼");
        viewHolder.tvpingoudetailcommentdate.setText(TimeUtils.getDayTime(list.get(position).getCreateTime()));
        viewHolder.tvpingoudetailcommentcontent.setText(list.get(position).getContent());
        //评论-图片
        List<String> replyThumbList = list.get(position).getReplyThumbList();
        if (replyThumbList.size()>0){
            ImgAdapter imgAdapter = new ImgAdapter(replyThumbList, activity);
            viewHolder.mgvpingoudetailcommentimgs.setAdapter(imgAdapter);
        }
        List<BeanPingouComment.ResEntity.ReplyListEntity.SubReplysEntity> subReplys = list.get(position).getSubReplys();
        //评论-子评论
        if (subReplys.size()>0){
            viewHolder.mlvpingoudetailcommentsecond.setVisibility(View.VISIBLE);
            PingouCommentInnerAdapter pingouCommentInnerAdapter = new PingouCommentInnerAdapter(subReplys, activity);
            viewHolder.mlvpingoudetailcommentsecond.setAdapter(pingouCommentInnerAdapter);
        }


        return convertView;
    }

    private class ViewHolder {
        public final SimpleDraweeView civpingoudetailcommenticon;
        public final TextView tvpingoudetailcommentname;
        public final TextView tvpingoudetailcommentfloor;
        public final TextView tvpingoudetailcommentdate;
        public final TextView tvpingoudetailcommentcontent;
        public final MyGridView mgvpingoudetailcommentimgs;
        public final MyListView mlvpingoudetailcommentsecond;
        public final View root;

        public ViewHolder(View root) {
            civpingoudetailcommenticon = (SimpleDraweeView) root.findViewById(R.id.civ_pingou_detail_comment_icon);
            tvpingoudetailcommentname = (TextView) root.findViewById(R.id.tv_pingou_detail_comment_name);
            tvpingoudetailcommentfloor = (TextView) root.findViewById(R.id.tv_pingou_detail_comment_floor);
            tvpingoudetailcommentdate = (TextView) root.findViewById(R.id.tv_pingou_detail_comment_date);
            tvpingoudetailcommentcontent = (TextView) root.findViewById(R.id.tv_pingou_detail_comment_content);
            mgvpingoudetailcommentimgs = (MyGridView) root.findViewById(R.id.mgv_pingou_detail_comment_imgs);
            mlvpingoudetailcommentsecond = (MyListView) root.findViewById(R.id.mlv_pingou_detail_comment_second);
            this.root = root;
        }
    }
}
