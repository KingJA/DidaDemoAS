package com.dida.first.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.activity.Publish_Activity;
import com.dida.first.adapter.ShowLvAdapter;
import com.dida.first.entity.ShowBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index_Show_Fragment extends Base_First_Fragment implements
        OnCheckedChangeListener, OnCheckListener<ShowBean.ResEntity.PrepayOrderListEntity>, AdapterView.OnItemClickListener {
    private static final String TAG = "Index_Show_Fragment";
    private PullToRefreshListView plv_show;
    private List<ShowBean.ResEntity.PrepayOrderListEntity> prepayOrderList = new ArrayList<ShowBean.ResEntity.PrepayOrderListEntity>();
    private CheckBox cb_show_checkall;
    private ShowLvAdapter mShowAdapter;
    private RelativeLayout rl_show_publish;
    private LinearLayout ll_show_publish;
    private StringBuilder sb;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //关闭刷新
            setRefreshComplete();
            switch (msg.what) {
                case RES_REFRESH:
                    mLoadingAndRetryManager.showContent();
                    ll_show_publish.setVisibility(View.VISIBLE);
                    ToastUtil.showMyToast("更新数据 page=" + mInitPager + " 数据量=" + prepayOrderList.size());
                    //隐藏加载进度条
                    mInitPager = 1;
                    //避免多次加Head
                    mShowAdapter.setData(prepayOrderList);
                    break;
                case RES_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mInitPager + " 数据量=" + prepayOrderList.size());
                    mShowAdapter.addData(prepayOrderList);
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


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fragment_show, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        rl_show_publish = (RelativeLayout) view.findViewById(R.id.rl_show_publish);
        ll_show_publish = (LinearLayout) view.findViewById(R.id.ll_show_publish);
        plv_show = (PullToRefreshListView) view.findViewById(R.id.plv_show);
        cb_show_checkall = (CheckBox) view
                .findViewById(R.id.cb_show_checkall);

    }

    @Override
    public void initFragmentNet() {
        mLoadingAndRetryManager.showLoading();
    }


    @Override
    public void initFragmentEvent() {
        mShowAdapter = new ShowLvAdapter(prepayOrderList,mActivity);
        cb_show_checkall.setOnCheckedChangeListener(this);
        rl_show_publish.setOnClickListener(this);
        mShowAdapter.setOnShowCheckListener(this);
        plv_show.setOnItemClickListener(this);
        plv_show.setOnRefreshListener(onRefreshListener);
        plv_show.setAdapter(mShowAdapter);

    }

    @Override
    public void initFragmentData() {
        refresh(1, RES_REFRESH);
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
                Log.i(TAG, "mInitPager: "+mInitPager);
            } else {
                mHandler.sendEmptyMessage(RES_NOMORE);
            }
        }
    };

    @Override
    public void onChildClick(View v) {
        if (v.getId() == R.id.rl_show_publish) {
            String checkedIds = getCheckedIds(mShowAdapter.getList());
            if (TextUtils.isEmpty(checkedIds)) {
                ToastUtil.showMyToast("亲，请选择要发起的商品");
            } else {
                ToastUtil.showMyToast(checkedIds);
                /**
                 * 跳转到发布Activity
                 */
                Bundle bundle = new Bundle();
                bundle.putString("prepayId",checkedIds);
                ActivityUtil.goActivityWithBundle(getActivity(), Publish_Activity.class,bundle);
            }
        }
    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    /**
     * 刷新结束，隐藏刷新布局
     */
    private void setRefreshComplete() {
        if (plv_show.isRefreshing()) {
            plv_show.onRefreshComplete();
        }
    }

    /**
     * 刷洗·加载 网络操作
     *
     * @param page
     * @param requestCode
     */
    private void refresh(final int page, final int requestCode) {
        VolleyGsonRequest<ShowBean> pingouRequest = new VolleyGsonRequest<ShowBean>(UrlUtil.HOST+UrlUtil.SHOW_LIST, ShowBean.class, new Response.Listener<ShowBean>() {
            @Override
            public void onResponse(ShowBean res) {
                prepayOrderList = res.getRes().getPrepayOrderList();
//                Log.i(TAG, "onResponse: "+prepayOrderList.size());
                if (requestCode == RES_MORE && prepayOrderList.isEmpty()) {
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
                map.put("userId", "fb9a38d82cd3405a9b60ec54cdb5ecdf");
                map.put("type", "1");
                map.put("page", page + "");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(pingouRequest);
    }

    private String getCheckedIds(List<ShowBean.ResEntity.PrepayOrderListEntity> list) {
        sb = new StringBuilder();
        for (ShowBean.ResEntity.PrepayOrderListEntity bean : list) {

            if (bean.isChecked()) {
                sb.append(bean.getId() + ",");
            }
        }
        return sb.toString();
    }

    /**
     * 单个选择/取消
     *
     * @param shaiDanItemBean
     * @param isChecked
     */
    @Override
    public void onCheck(ShowBean.ResEntity.PrepayOrderListEntity shaiDanItemBean, boolean isChecked) {
        shaiDanItemBean.setChecked(isChecked);
    }

    /**
     * 全选/全不选
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //屏蔽非点击设置触发，如sheChecked(boolean);
        if (!buttonView.isPressed())
            return;
        switch (buttonView.getId()) {
            case R.id.cb_show_checkall:
                mShowAdapter.checkAll(isChecked);
                break;
            default:
                break;
        }
    }

    /**
     * 点击跳转到集市详情页
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 跳转到发布Activity
         */
    }

}
