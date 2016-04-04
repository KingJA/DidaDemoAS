package com.dida.first.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.dida.first.R;
import com.dida.first.activity.Detail_Market_Activity;
import com.dida.first.entity.MarketBean;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.view.AbsListView.MyGridView;

import java.util.List;

public class MarketLvAdapter extends BaseAdapter {
    private List<MarketBean.ResEntity.ProductsEntity> list;
    private Activity activity;
    private MyGridView gv_item_market;

    public MarketLvAdapter(List<MarketBean.ResEntity.ProductsEntity> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addData(List<MarketBean.ResEntity.ProductsEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setData(List list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = View.inflate(activity,
                R.layout.item_single_gridview, null);
        gv_item_market = (MyGridView) itemView
                .findViewById(R.id.gv_item_market);
        MarketGvAdapter mMarketGvAdapter = new MarketGvAdapter(list, activity);
        gv_item_market.setAdapter(mMarketGvAdapter);
        gv_item_market.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showMyToast("position =" + position);
                Bundle bundle = new Bundle();
                bundle.putString("productNo", list.get(position).getProductNo());
                bundle.putString("type", "1");
                ActivityUtil.goActivityWithBundle(activity, Detail_Market_Activity.class, bundle);
            }
        });
        return itemView;
    }

}
