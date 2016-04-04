package com.dida.first.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.adapter.MarketLvAdapter;
import com.dida.first.entity.MarketBean;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;
import com.dida.first.view.TitleMoveImgView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market_Real_Fragment extends Base_First_Fragment {

    private MarketLvAdapter marketLvAdapter;
    private RelativeLayout rl_loading;
    private boolean hasInitHead;//初始化轮播图
    private PullToRefreshListView plv;
    private List<MarketBean.ResEntity.ProductAdsEntity> productAds = new ArrayList<MarketBean.ResEntity.ProductAdsEntity>();
    private List<MarketBean.ResEntity.ProductsEntity> products = new ArrayList<MarketBean.ResEntity.ProductsEntity>();
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //关闭刷新
            setRefreshComplete();
            switch (msg.what) {
                case RES_REFRESH:
                    mLoadingAndRetryManager.showContent();
                    mInitPager = 1;
                    ToastUtil.showMyToast("更新数据 page=" + mInitPager + " 数据量=" + products.size());
                    //隐藏加载进度条
                    //避免多次加Head
                    if (!hasInitHead) {
                        addLVHead(productAds);
                        hasInitHead = true;
                    }
                    marketLvAdapter.setData(products);
                    break;
                case RES_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mInitPager + " 数据量=" + products.size());
                    marketLvAdapter.addData(products);
                    break;
                case RES_ERROR:
                    mLoadingAndRetryManager.showRetry();
                    ToastUtil.showMyToast("服务器被都敏俊拐走了！");
                    break;
                case RES_NOMORE:
                    ToastUtil.showMyToast("更多商品敬请期待！");
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    public View setFragmentView() {
        view = View.inflate(getActivity(), R.layout.fr_market_real, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        plv = (PullToRefreshListView) view.findViewById(R.id.plv_market_real);
    }

    @Override
    public void initFragmentNet() {
        mLoadingAndRetryManager.showLoading();
    }

    @Override
    public void initFragmentEvent() {
        marketLvAdapter = new MarketLvAdapter(products, getActivity());
        plv.setAdapter(marketLvAdapter);
        plv.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void initFragmentData() {
        refresh(1, RES_REFRESH);
    }

    /**
     * 刷新结束，隐藏刷新布局
     */
    private void setRefreshComplete() {
        if (plv.isRefreshing()) {
            plv.onRefreshComplete();
        }
    }

    /**
     * 刷新加载监听器
     */
    private PullToRefreshBase.OnRefreshListener2 onRefreshListener = new PullToRefreshBase.OnRefreshListener2<ListView>() {

        @Override
        public void onPullDownToRefresh(
                PullToRefreshBase<ListView> refreshView) {
            refresh(1, RES_REFRESH);

        }

        @Override
        public void onPullUpToRefresh(
                PullToRefreshBase<ListView> refreshView) {
            if (mHasMore) {
                refresh(++mInitPager, RES_MORE);
            } else {
                mHandler.sendEmptyMessage(RES_NOMORE);
            }
        }
    };

    /**
     * ListView加入轮播图Head
     *
     * @param adsList
     */
    private void addLVHead(List<MarketBean.ResEntity.ProductAdsEntity> adsList) {
        adsLunBoTu = new TitleMoveImgView<MarketBean.ResEntity.ProductAdsEntity>(mActivity) {
            @Override
            protected void onInitShow(TextView titleTv, List<MarketBean.ResEntity.ProductAdsEntity> list) {
                titleTv.setText(mAdsList.get(0).getAdName());
            }

            @Override
            protected void onItemClick(List<MarketBean.ResEntity.ProductAdsEntity> mAdsList, int position) {

            }

            @Override
            protected void onTitleShow(TextView titleTv, List<MarketBean.ResEntity.ProductAdsEntity> mAdsList, int position) {
                Log.i(TAG, "onTitleShow position: "+position );
                titleTv.setText(mAdsList.get(position).getAdName());
            }

            @Override
            protected String setImgUrl(List<MarketBean.ResEntity.ProductAdsEntity> mAdsList, int position) {
                Log.i(TAG, "setImgUrl "+ mAdsList.get(position).getAdUrl() );
                return mAdsList.get(position).getAdUrl();
            }
        };
        adsLunBoTu.show(adsList);//显示轮播图
        adsLunBoTu.setViewHeight((int) (UIUtils.getScreenWidth()/2.0f));
        adsLunBoTu.startRoll();//轮播图自动切换
        ListView refreshableView = plv.getRefreshableView();
        refreshableView.addHeaderView(adsLunBoTu, null, false);
    }


    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
        }

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    /**
     * 刷洗·加载 网络操作
     *
     * @param page
     * @param requestCode
     */
    private void refresh(final int page, final int requestCode) {
        VolleyGsonRequest<MarketBean> pingouRequest = new VolleyGsonRequest<MarketBean>(UrlUtil.HOST + UrlUtil.MARKET_LIST, MarketBean.class, new Response.Listener<MarketBean>() {
            @Override
            public void onResponse(MarketBean bean) {
                productAds = bean.getRes().getProductAds();
                products = bean.getRes().getProducts();
                if (requestCode == RES_MORE && products.isEmpty()) {
                    ToastUtil.showMyToast("没有更多商品");
                    mHasMore = false;
                    mHandler.sendEmptyMessage(RES_NOMORE);
                }
                mHandler.sendEmptyMessage(requestCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERROR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "1");
                map.put("page", page + "");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(pingouRequest);
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        setMoveImg(hidden);
//    }
//
//    private void setMoveImg(boolean hidden) {
//        if (adsLunBoTu!=null){
//            if (hidden){
//                adsLunBoTu.stopRoll();
//            }else{
//                adsLunBoTu.startRoll();
//            }
//        }
//    }
}
