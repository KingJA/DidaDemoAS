package com.dida.first.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanPingou;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;

import java.util.List;

public class PingouLvAdapter extends BaseAdapter {

    private List<BeanPingou.ResEntity.QueryListEntity> list;
    private Activity activity;
    private RelativeLayout.LayoutParams layoutParams;

    public PingouLvAdapter(List<BeanPingou.ResEntity.QueryListEntity> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        int imgWidth = UIUtils.getScreenWidth();
        int imgHeight = imgWidth / 2;
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imgHeight);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addData(List<BeanPingou.ResEntity.QueryListEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    public List<BeanPingou.ResEntity.QueryListEntity> getData(){
        return list;
    }

    public void setData(List list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_pingou2, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.ivitempingouicon.setLayoutParams(layoutParams);



        showDeadTime(position, viewHolder);
        switch (list.get(position).getGroupMode()){
            //用户拼购
            case 0:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_user,list.get(position).getName());
//                viewHolder.ivitempingouisuser.setBackgroundResource(R.drawable.bg_user);
                viewHolder.ivitempingouqiang.setBackgroundColor(Color.TRANSPARENT);
                break;
            //商家拼购
            case 1:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_store,list.get(position).getName());
//                viewHolder.ivitempingouisuser.setBackgroundResource(R.drawable.bg_store);
                viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.pin);
                break;
            //商家抢购
            case 2:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_store,list.get(position).getName());
//                viewHolder.ivitempingouisuser.setBackgroundResource(R.drawable.bg_store);
                viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.qiang);
                break;
        }
        viewHolder.ivitempingouicon.setImageURI(Uri.parse(UrlUtil.getImgUrl(list.get(position).getProductThumb())));
        viewHolder.tvitempingousubject.setText(list.get(position).getCount() + "");
        viewHolder.tvitempingouleft.setText(list.get(position).getCount() - list.get(position).getBuyCount()+ "");
//        viewHolder.tvitempingoutitle.setText(list.get(position).getName());
        viewHolder.tvitempingouprice.setText("¥ "+list.get(position).getPrice());
        viewHolder.tvitempingouoldprice.setText("¥ "+list.get(position).getOldPrice());
//        viewHolder.tvitempingoucomment.setText(list.get(position).getTaskCount() + "");
//        StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_user,list.get(position).getName());
        return convertView;
    }

    private void showDeadTime(int position, ViewHolder viewHolder) {
        viewHolder.tvitempingouday.setText(StringUtil.getDoubleNum(TimeUtils.getDeadTime(list.get(position).getCustomDueDate())[0]));
        viewHolder.tvitempingouhour.setText(StringUtil.getDoubleNum(TimeUtils.getDeadTime(list.get(position).getCustomDueDate())[1]));
        viewHolder.tvitempingoumin.setText(StringUtil.getDoubleNum(TimeUtils.getDeadTime(list.get(position).getCustomDueDate())[2]));
    }

    private class ViewHolder {
        public final LinearLayout llitempingoudeadline;
        public final ImageView ivitempingouicon;
//        public final ImageView ivitempingouisuser;
        public final ImageView ivitempingouqiang;
        public final TextView tvitempingoutitle;
        public final TextView tvitempingousubject;
        public final TextView tvitempingouleft;
        public final TextView tvitempingouday;
        public final TextView tvitempingouhour;
        public final TextView tvitempingoumin;
        public final TextView tvitempingouprice;
        public final TextView tvitempingouoldprice;
//        public final TextView tvitempingoucomment;
        public final View root;

        public ViewHolder(View root) {
            llitempingoudeadline = (LinearLayout) root.findViewById(R.id.ll_item_pingou_deadline);
            ivitempingouicon = (ImageView) root.findViewById(R.id.iv_item_pingou_icon);
//            ivitempingouisuser = (ImageView) root.findViewById(R.id.iv_item_pingou_isuser);
            ivitempingouqiang = (ImageView) root.findViewById(R.id.iv_item_pingou_qiang);
            tvitempingoutitle = (TextView) root.findViewById(R.id.tv_item_pingou_title);
            tvitempingousubject = (TextView) root.findViewById(R.id.tv_item_pingou_subject);
            tvitempingouleft = (TextView) root.findViewById(R.id.tv_item_pingou_left);
            tvitempingouday = (TextView) root.findViewById(R.id.tv_item_pingou_day);
            tvitempingouhour = (TextView) root.findViewById(R.id.tv_item_pingou_hour);
            tvitempingoumin = (TextView) root.findViewById(R.id.tv_item_pingou_min);
            tvitempingouprice = (TextView) root.findViewById(R.id.tv_item_pingou_price);
            tvitempingouoldprice = (TextView) root.findViewById(R.id.tv_item_pingou_oldprice);
//            tvitempingoucomment = (TextView) root.findViewById(R.id.tv_item_pingou_comment);
            this.root = root;
        }
    }

}
