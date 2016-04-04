package com.dida.first.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanFavPingou;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.UrlUtil;

import java.util.List;

public class FavPingouAdapter extends BaseLvGvAdapter<BeanFavPingou.ResEntity.GroupCollectionEntity> {


    public FavPingouAdapter(List<BeanFavPingou.ResEntity.GroupCollectionEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_pingou2, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        showDeadTime(position, viewHolder);
        switch (list.get(position).getGroupMode()){
            //用户拼购
            case 0:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_user,list.get(position).getName());
                viewHolder.ivitempingouqiang.setBackgroundColor(Color.TRANSPARENT);
                break;
            //商家拼购
            case 1:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_store,list.get(position).getName());
                viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.pin);
                break;
            //商家抢购
            case 2:
                StringUtil.setRichText(viewHolder.tvitempingoutitle,R.drawable.bg_store,list.get(position).getName());
                viewHolder.ivitempingouqiang.setBackgroundResource(R.drawable.qiang);
                break;
        }

        viewHolder.ivitempingouicon.setImageURI(Uri.parse(UrlUtil.getImgUrl(list.get(position).getProductThumb())));
        viewHolder.tvitempingousubject.setText(list.get(position).getCount() + "");
        viewHolder.tvitempingouleft.setText(list.get(position).getCount() - list.get(position).getBuyCount()+ "");
        viewHolder.tvitempingouprice.setText("¥ "+list.get(position).getPrice());
        viewHolder.tvitempingouoldprice.setText("¥ "+list.get(position).getOldPrice());
        if (list.get(position).getGroupStatus()==2){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            viewHolder.ivitempingouicon.setColorFilter(filter);
            viewHolder.ivoverude.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivitempingouicon.setColorFilter(null);
            viewHolder.ivoverude.setVisibility(View.GONE);
        }
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
        public final ImageView ivoverude;
        public final ImageView ivitempingouqiang;
        public final TextView tvitempingoutitle;
        public final TextView tvitempingousubject;
        public final TextView tvitempingouleft;
        public final TextView tvitempingouday;
        public final TextView tvitempingouhour;
        public final TextView tvitempingoumin;
        public final TextView tvitempingouprice;
        public final TextView tvitempingouoldprice;
        public final View root;

        public ViewHolder(View root) {
            llitempingoudeadline = (LinearLayout) root.findViewById(R.id.ll_item_pingou_deadline);
            ivitempingouicon = (ImageView) root.findViewById(R.id.iv_item_pingou_icon);
            ivoverude = (ImageView) root.findViewById(R.id.iv_overude);
            ivitempingouqiang = (ImageView) root.findViewById(R.id.iv_item_pingou_qiang);
            tvitempingoutitle = (TextView) root.findViewById(R.id.tv_item_pingou_title);
            tvitempingousubject = (TextView) root.findViewById(R.id.tv_item_pingou_subject);
            tvitempingouleft = (TextView) root.findViewById(R.id.tv_item_pingou_left);
            tvitempingouday = (TextView) root.findViewById(R.id.tv_item_pingou_day);
            tvitempingouhour = (TextView) root.findViewById(R.id.tv_item_pingou_hour);
            tvitempingoumin = (TextView) root.findViewById(R.id.tv_item_pingou_min);
            tvitempingouprice = (TextView) root.findViewById(R.id.tv_item_pingou_price);
            tvitempingouoldprice = (TextView) root.findViewById(R.id.tv_item_pingou_oldprice);
            this.root = root;
        }
    }

}
