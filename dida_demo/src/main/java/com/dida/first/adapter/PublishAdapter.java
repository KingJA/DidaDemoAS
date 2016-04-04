package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.PrepayBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.UImageLoaderUitl;

import java.util.List;

public class PublishAdapter extends BaseAdapter {

    private List<PrepayBean.ResEntity.PrepayOrderListEntity> list;
    private Activity activity;
    private OnCheckListener onCheckListener;
    public PublishAdapter(List<PrepayBean.ResEntity.PrepayOrderListEntity> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }
    public void setOnShowCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
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


    public List<PrepayBean.ResEntity.PrepayOrderListEntity> getList(){
        return list;
    }

    public List<PrepayBean.ResEntity.PrepayOrderListEntity> getData() {
        return list;
    }

    public void setData(List<PrepayBean.ResEntity.PrepayOrderListEntity> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
    public void addData(List<PrepayBean.ResEntity.PrepayOrderListEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = View
                    .inflate(activity, R.layout.item_publish_head, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvitemshowedittitle.setText(list.get(position).getOrderName());
        viewHolder.tvitemshoweditparam.setText(list.get(position).getOrderattrvalue());
        viewHolder.tvitemshoweditprice.setText(list.get(position).getPrice()+"");
        viewHolder.tvitemshoweditcount.setText(list.get(position).getCount()+"");
        UImageLoaderUitl.displayGvMidImage(list.get(position).getProductThumb(),viewHolder.ivitemshowediticon);
        return convertView;
    }


    public class ViewHolder {
        public final ImageView ivitemshowediticon;
        public final TextView tvitemshowedittitle;
        public final TextView tvitemshoweditparam;
        public final TextView tvitemshoweditprice;
        public final TextView tvitemshoweditcount;
        public final View root;

        public ViewHolder(View root) {
            ivitemshowediticon = (ImageView) root.findViewById(R.id.iv_item_show_edit_icon);
            tvitemshowedittitle = (TextView) root.findViewById(R.id.tv_item_show_edit_title);
            tvitemshoweditparam = (TextView) root.findViewById(R.id.tv_item_show_edit_param);
            tvitemshoweditprice = (TextView) root.findViewById(R.id.tv_item_show_edit_price);
            tvitemshoweditcount = (TextView) root.findViewById(R.id.tv_item_show_edit_count);
            this.root = root;
        }
    }
}
