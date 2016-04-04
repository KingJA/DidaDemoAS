package com.dida.first.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.MarketBean;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MarketGvAdapter extends BaseAdapter {
    private static final String TAG = "MarketGvAdapter";
    private List<MarketBean.ResEntity.ProductsEntity> list;
    private Context context;
    private LinearLayout.LayoutParams param;

    public MarketGvAdapter(List<MarketBean.ResEntity.ProductsEntity> list, Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int itemWidth = (screenWidth - 3 * UIUtils.dip2px(4)) / 2;
        int itemHeight= (int) (itemWidth*1.2f);
        param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, itemHeight);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_marketlist, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
//        viewHolder.ivmarketitemimg.setLayoutParams(param);
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
