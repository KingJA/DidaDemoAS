/**
 *
 */
package com.dida.first.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.Store_Activity;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.dida.first.utils.UrlUtil;
import com.meg7.widget.CircleImageView;


/**
 * @author KingJA
 * @data 2015-9-15 上午11:25:46
 * @use
 */
public class MDetail_Store_Holder extends BaseHolder<BeanDetailMarket> implements OnClickListener {

    private View view;
    private TextView tv_market_detail_store_wuliu_state;
    private TextView tv_market_detail_store_wuliu;
    private TextView tv_market_detail_store_service_state;
    private TextView tv_market_detail_store_service;
    private TextView tv_market_detail_store_description_state;
    private TextView tv_market_detail_store_description;
    private TextView tv_market_detail_store_name;
    private CircleImageView civ_market_detail_store_name;

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.market_detail_store);
        init();
        return view;
    }


    @Override
    public void refreshView() {
        BeanDetailMarket data = getData();
        tv_market_detail_store_name.setText(data.getRes().getShop().getName());
        UImageLoaderUitl.displaySmallImage(UrlUtil.HOST+data.getRes().getShop().getThumb(),civ_market_detail_store_name);
    }

    private void init() {
        LinearLayout ll_store_des = (LinearLayout) view.findViewById(R.id.ll_store_des);
        LinearLayout ll_market_detail_store_enter = (LinearLayout) view.findViewById(R.id.ll_market_detail_store_enter);
        LinearLayout ll_market_detail_store_list = (LinearLayout) view.findViewById(R.id.ll_market_detail_store_list);
        ll_store_des.setOnClickListener(this);
        ll_market_detail_store_enter.setOnClickListener(this);
        ll_market_detail_store_list.setOnClickListener(this);
        civ_market_detail_store_name = (CircleImageView) view.findViewById(R.id.civ_market_detail_store_name);
        tv_market_detail_store_name = (TextView) view.findViewById(R.id.tv_market_detail_store_name);
        tv_market_detail_store_description = (TextView) view.findViewById(R.id.tv_market_detail_store_description);
        tv_market_detail_store_description_state = (TextView) view.findViewById(R.id.tv_market_detail_store_description_state);
        tv_market_detail_store_service = (TextView) view.findViewById(R.id.tv_market_detail_store_service);
        tv_market_detail_store_service_state = (TextView) view.findViewById(R.id.tv_market_detail_store_service_state);
        tv_market_detail_store_wuliu = (TextView) view.findViewById(R.id.tv_market_detail_store_wuliu);
        tv_market_detail_store_wuliu_state = (TextView) view.findViewById(R.id.tv_market_detail_store_wuliu_state);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_market_detail_store_enter:
                //TODO
                ToastUtil.showMyToast("进入店铺");
                ActivityUtil.goActivity(ActivityFactory.mainActivity, Store_Activity.class);
                break;
            case R.id.ll_market_detail_store_list:
                //TODO
                ToastUtil.showMyToast("商品分类");
                break;
            case R.id.ll_store_des:
                //TODO
                ToastUtil.showMyToast("店铺简介");
                break;

            default:
                break;
        }

    }
}
