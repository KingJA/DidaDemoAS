package com.dida.first.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.dida.first.R;
import com.dida.first.adapter.SearchMarketAdapter;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanSearchMarket;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-15.
 */
public class Search_Market_Activity extends BaseSearchActivity {


    private static final String TAG = "Search_Market_Activity";
    private String mType;
    private SwipeRefreshLayout swipe_single;
    private GridView gv_single;
    private List<BeanSearchMarket.ResEntity.ProductsEntity> products=new ArrayList<>();
    private int mPage = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            swipe_single.setRefreshing(false);
            switch (msg.what) {
                case RES_REFRESH:
                    mLoadingAndRetryManager.showContent();
                    mPage = 1;
                    mHasMore=true;
                    ToastUtil.showMyToast("更新数据 page=" + mPage + " 数据量=" + products.size());
                    searchMarketAdapter.setData(products);
                    break;
                case RES_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mPage + " 数据量=" + products.size());
                    searchMarketAdapter.addData(products);
                    break;
                case RES_ERROR:
                    ToastUtil.showMyToast("服务器被都敏俊拐走了！");
                    mLoadingAndRetryManager.showRetry();
                    break;
                case RES_NOMORE:
                    ToastUtil.showMyToast("更多商品敬请期待！");
                    break;
            }
        }
    };
    private SearchMarketAdapter searchMarketAdapter;

    @Override
    protected View setView() {
        view= View.inflate(this, R.layout.single_gv,null);
        return view;
    }

    @Override
    protected void initView() {
        swipe_single = (SwipeRefreshLayout) view.findViewById(R.id.swipe_single);
        gv_single = (GridView) view.findViewById(R.id.gv_single);
        searchMarketAdapter = new SearchMarketAdapter(products, this);

    }

    @Override
    protected void initNet() {
        mType = getIntent().getStringExtra("TYPE");
        ToastUtil.showMyToast(mType);

    }

    @Override
    protected void initEvent() {
        gv_single.setAdapter(searchMarketAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onChildClick(View v) {

    }

    protected void onSearch(String name) {
        ToastUtil.showMyToast(name);
        doSearch(RES_REFRESH, name,1+"",mType,"","","");
    }

    private void doSearch(final int requestCode, String name, String page, String type, String sort, String lowPrice, String maxPrice) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_MARKET_LIST))
                .addParams("name", name)
                .addParams("page", page)
                .addParams("type", type)
                .addParams("sort", sort)
                .addParams("lowPrice", lowPrice)
                .addParams("maxPrice", maxPrice)
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanSearchMarket>(BeanSearchMarket.class) {
                    @Override
                    public void onError(Request request, Exception e) {
//                        mHandler.sendEmptyMessage(RES_ERROR);
                    }

                    @Override
                    public void onResponse(BeanSearchMarket bean) {
                        products = bean.getRes().getProducts();
                        Log.i(TAG, "JsonResponse: " + bean.getRes().getRowCount());

                        if (requestCode == RES_MORE && products.isEmpty()) {
                            ToastUtil.showMyToast("没有更多商品");
                            mHasMore = false;
                            mHandler.sendEmptyMessage(RES_NOMORE);
                        }
                        mHandler.sendEmptyMessage(requestCode);
                    }
                });

    }

    @Override
    protected void onSalesSort(String name) {
        ToastUtil.showMyToast("name = "+ name +" 销量领先 ");
        doSearch(RES_REFRESH, name, "1", mType, "4", "", "");
    }

    @Override
    protected void onMainSort(String name, int mainSortType) {
        ToastUtil.showMyToast("name = "+name+" mainSortType = "+mainSortType);
        doSearch(RES_REFRESH, name, "1", mType, mainSortType+"", "", "");
    }

    @Override
    protected void onPriceFromToSort(String name, String lowPrice, String maxPrice) {
        ToastUtil.showMyToast("name = "+ name +" priceFrom = "+ lowPrice +" priceTo = "+ maxPrice);
        doSearch(RES_REFRESH, name, "1", mType, "1", lowPrice, maxPrice);
    }
}
