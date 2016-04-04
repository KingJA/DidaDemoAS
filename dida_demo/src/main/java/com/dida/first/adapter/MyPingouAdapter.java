package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanMinePingou;
import com.dida.first.utils.UrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyPingouAdapter extends BaseLvGvAdapter<BeanMinePingou.ResEntity.GroupListEntity> {


    public MyPingouAdapter(List<BeanMinePingou.ResEntity.GroupListEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(activity, R.layout.item_mypingou_mine, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvmypingouminedate.setText(list.get(position).getCreateTime());
        viewHolder.tvmypingouminetitle.setText(list.get(position).getServiceName());
        viewHolder.tvmypingouminecontent.setText(list.get(position).getPureDes());
        viewHolder.tvmypingouminestate.setText(list.get(position).getStatusText());
        viewHolder.tvmypingouminesubject.setText(list.get(position).getCount()+"");
        viewHolder.tvmypingouminecount.setText(list.get(position).getJoinCount()+"");
        viewHolder.tvfavpingoufrom.setText(list.get(position).getGroupMode()==0?"用户":"商家");
        viewHolder.ivmypingoumineicon.setImageURI(UrlUtil.getUri(list.get(position).getThumb()));
        viewHolder.llmypingouminebtn.setVisibility(list.get(position).getStatus()==2?View.VISIBLE:View.GONE);
        return convertView;
    }


    public class ViewHolder {
        public final TextView tvmypingouminedate;
        public final TextView tvfavpingoufrom;
        public final TextView tvmypingouminestate;
        public final SimpleDraweeView ivmypingoumineicon;
        public final TextView tvmypingouminetitle;
        public final TextView tvmypingouminecontent;
        public final TextView tvmypingouminesubject;
        public final TextView tvmypingouminecount;
        public final TextView tvmypingouminetotelPrice;
        public final Button btnmypingoumineedit;
        public final LinearLayout llmypingouminebtn;
        public final View root;

        public ViewHolder(View root) {
            tvmypingouminedate = (TextView) root.findViewById(R.id.tv_mypingou_mine_date);
            tvfavpingoufrom = (TextView) root.findViewById(R.id.tv_fav_pingou_from);
            tvmypingouminestate = (TextView) root.findViewById(R.id.tv_mypingou_mine_state);
            ivmypingoumineicon = (SimpleDraweeView) root.findViewById(R.id.iv_mypingou_mine_icon);
            tvmypingouminetitle = (TextView) root.findViewById(R.id.tv_mypingou_mine_title);
            tvmypingouminecontent = (TextView) root.findViewById(R.id.tv_mypingou_mine_content);
            tvmypingouminesubject = (TextView) root.findViewById(R.id.tv_mypingou_mine_subject);
            tvmypingouminecount = (TextView) root.findViewById(R.id.tv_mypingou_mine_count);
            tvmypingouminetotelPrice = (TextView) root.findViewById(R.id.tv_mypingou_mine_totelPrice);
            btnmypingoumineedit = (Button) root.findViewById(R.id.btn_mypingou_mine_edit);
            llmypingouminebtn = (LinearLayout) root.findViewById(R.id.ll_mypingou_mine_btn);
            this.root = root;
        }
    }
}
