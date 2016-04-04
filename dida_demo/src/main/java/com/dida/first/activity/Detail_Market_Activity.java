/**
 *
 */
package com.dida.first.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.dialog.DialogProgress;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.entity.BeanRes;
import com.dida.first.holder.MDetail_Comment_Holder;
import com.dida.first.holder.MDetail_Des_Holder;
import com.dida.first.holder.MDetail_Head_Holder;
import com.dida.first.holder.MDetail_Store_Holder;
import com.dida.first.popupwindow.PopupWindowParamMarket;
import com.dida.first.popupwindow.PopupWindowSelectMarket;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingJA
 * @data 2015-9-11 上午9:47:00
 * @use
 */
public class Detail_Market_Activity extends BaseNomalActivity {

    private static final String TAG = "Detail_Market_Activity";

    private LinearLayout ll_parent_market_detail;
    private ImageView iv_market_detail_back;
    private ImageView iv_market_detail_chat;
    private MDetail_Head_Holder headHolder;
    private MDetail_Des_Holder desHolder;
    private MDetail_Store_Holder storeHolder;
    private MDetail_Comment_Holder commentHolder;
    private BeanDetailMarket mDetailMarket = new BeanDetailMarket();
    private PopupWindowSelectMarket selectPopupWindow;
    private PopupWindowParamMarket paramPopupWindow;
    private String mProductNo;
    private String mType;
    private TextView tv_market_join;
    private TextView tv_market_addcar;
    private ImageView iv_market_addcar;
    private LinearLayout ll_market_addshow;
    private LinearLayout ll_market_store;
    private LinearLayout ll_market_addcar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RES_OK:
                    mLoadingAndRetryManager.showContent();
                    setData(mDetailMarket);
                    //初始化参数列表
                    initPopupWindow();
                    break;
                case RES_ERR:
                    mLoadingAndRetryManager.showRetry();
                    ToastUtil.showMyToast("服务器君被绑架啦！");
                    break;
                case RES_COLLECT_OK:
                    mDialogProgress.dismiss();
                    showFav(mIsCollection);
                    break;

            }

        }
    };



    private void initPopupWindow() {
        selectPopupWindow = new PopupWindowSelectMarket(
                ll_parent_market_detail, Detail_Market_Activity.this, mDetailMarket.getRes().getPurchaseAttrList());
        paramPopupWindow = new PopupWindowParamMarket(
                ll_parent_market_detail, Detail_Market_Activity.this, mDetailMarket.getRes().getProductCustomAttrList());
    }



    @Override
    protected void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_market_detail_param:
                ToastUtil.showMyToast("选择规格");
                selectPopupWindow.showPopupWindow();
                break;
            case R.id.rl_market_detail_info:
                ToastUtil.showMyToast("商品参数");

                paramPopupWindow.showPopupWindow();
                break;
            case R.id.iv_market_detail_back:
                finish();
                break;
            case R.id.iv_market_detail_chat:
                //TODO
                ToastUtil.showMyToast("聊天界面");
                break;
            case R.id.ll_market_addcar:
                //TODO
                ToastUtil.showMyToast("加入待购");
                doNetCollect(mProductNo, mType, "fb9a38d82cd3405a9b60ec54cdb5ecdf", mIsCollection);
                Log.i(TAG, " mProductNo: "+mProductNo+" mType: "+mType+" mIsCollection: "+mIsCollection);
                break;
            case R.id.ll_market_addshow:
                //TODO
                ToastUtil.showMyToast("加入晒单");
                break;
            case R.id.ll_market_store:
                //TODO
                ToastUtil.showMyToast("店铺");
                break;
            case R.id.tv_market_join:
                //TODO
                ToastUtil.showMyToast("立即购买");
                break;

            default:
                break;
        }
    }

    @Override
    protected View setView() {
        View view = UIUtils.inflate(R.layout.activity_market_detail);

        /**
         * head信息
         */
        FrameLayout fl_market_detail_head = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_head);
        headHolder = new MDetail_Head_Holder(ll_parent_market_detail, this);
        fl_market_detail_head.addView(headHolder.getRootView());
        /**
         * 规格选择
         */
        RelativeLayout rl_market_detail_param = (RelativeLayout) view
                .findViewById(R.id.rl_market_detail_param);
        rl_market_detail_param.setOnClickListener(this);
        /**
         * 评价
         */
        FrameLayout fl_market_detail_comment = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_comment);
        commentHolder = new MDetail_Comment_Holder(this);
        fl_market_detail_comment.addView(commentHolder.getRootView());
        /**
         * 店铺信息
         */
        FrameLayout fl_market_detail_store = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_store);
        storeHolder = new MDetail_Store_Holder();
        fl_market_detail_store.addView(storeHolder.getRootView());

        /**
         * 商品参数
         */
        RelativeLayout rl_market_detail_info = (RelativeLayout) view
                .findViewById(R.id.rl_market_detail_info);
        rl_market_detail_info.setOnClickListener(this);
        /**
         * 图文详情
         */
        FrameLayout fl_market_detail_image = (FrameLayout) view
                .findViewById(R.id.fl_market_detail_image);
        desHolder = new MDetail_Des_Holder(this);
        fl_market_detail_image.addView(desHolder.getRootView());
        return view;
    }

    @Override
    protected void initView() {
        ll_parent_market_detail = (LinearLayout) view
                .findViewById(R.id.ll_parent_market_detail);
        ll_market_addcar = (LinearLayout) view.findViewById(R.id.ll_market_addcar);
        ll_market_store = (LinearLayout) view.findViewById(R.id.ll_market_store);
        ll_market_addshow = (LinearLayout) view.findViewById(R.id.ll_market_addshow);

        iv_market_addcar = (ImageView) view.findViewById(R.id.iv_market_addcar);
        tv_market_addcar = (TextView) view.findViewById(R.id.tv_market_addcar);

        tv_market_join = (TextView) view.findViewById(R.id.tv_market_join);

        iv_market_detail_back = (ImageView) view.findViewById(R.id.iv_market_detail_back);
        iv_market_detail_chat = (ImageView) view.findViewById(R.id.iv_market_detail_chat);
    }

    @Override
    protected void initNet() {
        Bundle bundle = getIntent().getExtras();
        mProductNo = bundle.getString("productNo");
        mType = bundle.getString("type");
        mLoadingAndRetryManager.showLoading();
        mDialogProgress = new DialogProgress(this);

    }

    /**
     * 访问网络-初始化页面
     *
     * @param productNo
     * @param type
     */
    private void doNetInit(final String productNo, final String type) {
        VolleyGsonRequest<BeanDetailMarket> initRequest = new VolleyGsonRequest<BeanDetailMarket>(UrlUtil.HOST + UrlUtil.MARKET_DETAIL, BeanDetailMarket.class, new Response.Listener<BeanDetailMarket>() {
            @Override
            public void onResponse(BeanDetailMarket beanDetailMarket) {
                Log.i(TAG, "beanDetailMarket: " + beanDetailMarket.getRes().getTimeOrPhyProduct().getName());
                mDetailMarket = beanDetailMarket;
                mIsCollection = beanDetailMarket.getRes().getIsCollection();
                mHandler.sendEmptyMessage(RES_OK);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("productNo", productNo);
                map.put("type", type);
                map.put("userId", "fb9a38d82cd3405a9b60ec54cdb5ecdf");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(initRequest);
    }

    /**
     * 加入/取消    待购单
     * @param productId
     * @param productType
     * @param userId
     * @param isCollection
     */
    private void doNetCollect(final String productId, final String productType, final String userId, final int isCollection) {
        VolleyGsonRequest<BeanRes> collectRequest = new VolleyGsonRequest<BeanRes>(UrlUtil.HOST + UrlUtil.MARKET_ADD_CANCLE_COLLECT, BeanRes.class, new Response.Listener<BeanRes>() {
            @Override
            public void onResponse(BeanRes res) {
                mIsCollection = res.getRes();
                Log.i(TAG, "onResponse: "+mIsCollection);
                mHandler.sendEmptyMessage(RES_COLLECT_OK);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                mHandler.sendEmptyMessage(RES_ERR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("productId", productId);
                map.put("productType", productType);
                map.put("userId", "fb9a38d82cd3405a9b60ec54cdb5ecdf");
                map.put("isCollection", isCollection + "");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(collectRequest);
    }


    @Override
    protected void initEvent() {
        ll_market_addcar.setOnClickListener(this);
        ll_market_store.setOnClickListener(this);
        ll_market_addshow.setOnClickListener(this);
        tv_market_join.setOnClickListener(this);
        iv_market_detail_back.setOnClickListener(this);
        iv_market_detail_chat.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        doNetInit(mProductNo, mType);
    }

    private void setData(BeanDetailMarket bean) {
        headHolder.setData(bean);
        storeHolder.setData(bean);
        commentHolder.setData(bean);
        commentHolder.setData(bean);
        desHolder.setData(bean);
        showFav(mIsCollection);
    }

    private void showFav(int isCollection) {

        iv_market_addcar.setBackgroundResource(isCollection == 0 ? R.drawable.btn_addcar_sel : R.drawable.btn_canclecar_nor);
        tv_market_addcar.setText(isCollection == 0 ? "加入待购" : "取消待购");
    }


}
