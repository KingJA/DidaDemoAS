package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UImageLoaderUitl;
import com.meg7.widget.CircleImageView;

import java.util.List;

/**
 * Created by KingJA on 2015-12-29.
 * 拼购已经参与的团员列表
 */
public class PingouTeamUserAdapter extends BaseLvGvAdapter<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> {
    public PingouTeamUserAdapter(List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> list, Activity activity) {
        super(list, activity);
    }



    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_attention, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivpingougroupname.setText(list.get(position).getUserName());
        viewHolder.ivpingougroupgender.setBackgroundResource(list.get(position).getSex()==1?R.drawable.gender_man:R.drawable.gender_woman);
        UImageLoaderUitl.displayGvMidImage(list.get(position).getThumb(), viewHolder.ivpingougroupicon);
        viewHolder.llpingougroupadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMyToast("添加好友");
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public final CircleImageView ivpingougroupicon;
        public final TextView ivpingougroupname;
        public final ImageView ivpingougroupgender;
        public final LinearLayout llpingougroupadd;
        public final View root;

        public ViewHolder(View root) {
            ivpingougroupicon = (CircleImageView) root.findViewById(R.id.iv_pingou_group_icon);
            ivpingougroupname = (TextView) root.findViewById(R.id.iv_pingou_group_name);
            ivpingougroupgender = (ImageView) root.findViewById(R.id.iv_pingou_group_gender);
            llpingougroupadd = (LinearLayout) root.findViewById(R.id.ll_pingou_group_add);
            this.root = root;
        }
    }
}
