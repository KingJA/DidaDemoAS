package com.dida.first.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanSearchMarket;
import com.dida.first.utils.UrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SearchMarketAdapter extends BaseLvGvAdapter<BeanSearchMarket.ResEntity.ProductsEntity> {

    public SearchMarketAdapter(List<BeanSearchMarket.ResEntity.ProductsEntity> list, Activity activity) {
        super(list, activity);
    }



    @Override
    public View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_marketlist, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvmarketitemtitle.setText(list.get(position).getName());
        viewHolder.tvmarketitemprice.setText("¥ "+list.get(position).getPrice());
        viewHolder.tvmarketitemcount.setText(list.get(position).getSalesCount()+"");
        Uri imageUri = Uri.parse(UrlUtil.getImgUrl(list.get(position).getThumb()));
        viewHolder.ivmarketitemimg.setImageURI(imageUri);
        return convertView;
    }


    public class ViewHolder {
        public SimpleDraweeView ivmarketitemimg;
        public TextView tvmarketitemtitle;
        public TextView tvmarketitemprice;
        public TextView tvmarketitemcount;
        public View root;

        public ViewHolder(View root) {
            ivmarketitemimg = (SimpleDraweeView) root.findViewById(R.id.iv_market_item_img);
            tvmarketitemtitle = (TextView) root.findViewById(R.id.tv_market_item_title);
            tvmarketitemprice = (TextView) root.findViewById(R.id.tv_market_item_price);
            tvmarketitemcount = (TextView) root.findViewById(R.id.tv_market_item_count);
            this.root = root;
        }
    }
}
