package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.ShowBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.UImageLoaderUitl;

import java.util.List;

public class ShowLvAdapter extends BaseAdapter {

    private List<ShowBean.ResEntity.PrepayOrderListEntity> list;
    private Activity activity;
    private OnCheckListener onCheckListener;
    public ShowLvAdapter(List<ShowBean.ResEntity.PrepayOrderListEntity> list, Activity activity) {
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


    public List<ShowBean.ResEntity.PrepayOrderListEntity> getList(){
        return list;
    }
    /**
     * 全选/全不选
     *
     * @param checked
     */
    public void checkAll(boolean checked) {
        for (ShowBean.ResEntity.PrepayOrderListEntity shaiDanItemBean : list) {
            shaiDanItemBean.setChecked(checked);
        }
        this.notifyDataSetChanged();
    }
    public List<ShowBean.ResEntity.PrepayOrderListEntity> getData() {
        return list;
    }

    public void setData(List list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
    public void addData(List<ShowBean.ResEntity.PrepayOrderListEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = View
                    .inflate(activity, R.layout.item_shaidan, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvitemshowtitle.setText(list.get(position).getOrderName());
        viewHolder.tvitemshowparam.setText(list.get(position).getOrderattrvalue());
        viewHolder.tvitemshowprice.setText(list.get(position).getPrice()+"");
        viewHolder.tvitemshowcount.setText(list.get(position).getCount()+"");
        UImageLoaderUitl.displayGvMidImage(list.get(position).getProductThumb(), viewHolder.ivitemshowimage);
        viewHolder.cbitemshow.setChecked(list.get(position).isChecked());
        viewHolder.cbitemshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //屏蔽非点击设置触发，如sheChecked(boolean);
                if (!buttonView.isPressed())
                    return;
                onCheckListener.onCheck(list.get(position), isChecked);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        public final CheckBox cbitemshow;
        public final RelativeLayout rlshowselect;
        public final ImageView ivitemshowimage;
        public final TextView tvitemshowtitle;
        public final TextView tvitemshowparam;
        public final TextView saidanrmb;
        public final TextView tvitemshowprice;
        public final TextView tvitemshowcount;
        public final View root;

        public ViewHolder(View root) {
            cbitemshow = (CheckBox) root.findViewById(R.id.cb_item_show);
            rlshowselect = (RelativeLayout) root.findViewById(R.id.rl_show_select);
            ivitemshowimage = (ImageView) root.findViewById(R.id.iv_item_show_image);
            tvitemshowtitle = (TextView) root.findViewById(R.id.tv_item_show_title);
            tvitemshowparam = (TextView) root.findViewById(R.id.tv_item_show_param);
            saidanrmb = (TextView) root.findViewById(R.id.saidan_rmb);
            tvitemshowprice = (TextView) root.findViewById(R.id.tv_item_show_price);
            tvitemshowcount = (TextView) root.findViewById(R.id.tv_item_show_count);
            this.root = root;
        }
    }
}
