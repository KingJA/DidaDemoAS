package com.dida.first.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.activity.Detail_Pingou_Store_Activity;
import com.dida.first.activity.Detail_Pingou_User_Activity;
import com.dida.first.activity.Search_Pingou_Activity;
import com.dida.first.adapter.PingouLvAdapter;
import com.dida.first.entity.BeanPingou;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;
import com.dida.first.view.TitleMoveImgView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index_Pingou_Fragment extends Base_First_Fragment implements AdapterView.OnItemClickListener{
    private boolean hasInitHead;//初始化轮播图
//    private int mInitPager = 1;//初始化页面Position
    private static final String TAG = "Index_Pingou_Fragment";
    private List<BeanPingou.ResEntity.QueryListEntity> queryList = new ArrayList<BeanPingou.ResEntity.QueryListEntity>();
    private List<BeanPingou.ResEntity.TaskAdvertiseEntity> taskAdvertise = new ArrayList<BeanPingou.ResEntity.TaskAdvertiseEntity>();
    private PullToRefreshListView plv_pingou;
    private PingouLvAdapter pingouLvAdapter;
    private RelativeLayout rl_loading;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //关闭刷新
            setRefreshComplete();
            switch (msg.what) {
                case RES_REFRESH:
                    mLoadingAndRetryManager.showContent();
                    ToastUtil.showMyToast("更新数据 page=" + mInitPager + " 数据量=" + queryList.size());
                    mInitPager = 1;
                    //避免多次加Head
                    if (!hasInitHead) {
                        addLVHead(taskAdvertise);
                        hasInitHead = true;
                    }
                    pingouLvAdapter.setData(queryList);
                    break;
                case RES_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mInitPager + " 数据量=" + queryList.size());
                    pingouLvAdapter.addData(queryList);
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
//    private NoTitleMoveImgView<BeanPingou.ResEntity.TaskAdvertiseEntity> adsLunBoTu;
    private Intent intent;
    private RelativeLayout rl_search;
//    private TitleMoveImgView adsLunBoTu1;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fr_pingou, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        plv_pingou = (PullToRefreshListView) view.findViewById(R.id.plv_pingou);
        rl_search = (RelativeLayout) view.findViewById(R.id.rl_search);

    }

    @Override
    public void initFragmentNet() {
        mLoadingAndRetryManager.showLoading();
    }

    @Override
    public void initFragmentEvent() {
        pingouLvAdapter = new PingouLvAdapter(queryList, mActivity);
        plv_pingou.setAdapter(pingouLvAdapter);
        plv_pingou.setOnRefreshListener(onRefreshListener);
        plv_pingou.setOnScrollListener(new PauseOnScrollListener(UImageLoaderUitl.getImageLoader(), true, false));
        plv_pingou.setOnItemClickListener(this);
        rl_search.setOnClickListener(this);

    }

    @Override
    public void initFragmentData() {
        refresh(1, RES_REFRESH);
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()){
            case R.id.rl_search:
                ActivityUtil.goActivity(mActivity, Search_Pingou_Activity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    /**
     * 刷新结束，隐藏刷新布局
     */
    private void setRefreshComplete() {
        if (plv_pingou.isRefreshing()) {
            plv_pingou.onRefreshComplete();
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
    private void addLVHead(List<BeanPingou.ResEntity.TaskAdvertiseEntity> adsList) {


        adsLunBoTu = new TitleMoveImgView<BeanPingou.ResEntity.TaskAdvertiseEntity>(mActivity) {
            @Override
            protected void onInitShow(TextView titleTv, List<BeanPingou.ResEntity.TaskAdvertiseEntity> list) {
                titleTv.setText(mAdsList.get(0).getAdName());
            }

            @Override
            protected void onItemClick(List<BeanPingou.ResEntity.TaskAdvertiseEntity> mAdsList, int position) {
                ToastUtil.showMyToast(mAdsList.get(position).getAdName());
            }

            @Override
            protected void onTitleShow(TextView titleTv, List<BeanPingou.ResEntity.TaskAdvertiseEntity> mAdsList, int position) {
                Log.i(TAG, "onTitleShow position: "+position );
                titleTv.setText(mAdsList.get(position).getAdName());
            }

            @Override
            protected String setImgUrl(List<BeanPingou.ResEntity.TaskAdvertiseEntity> mAdsList, int position) {
                Log.i(TAG, "setImgUrl "+ mAdsList.get(position).getAdUrl() );
                return mAdsList.get(position).getAdUrl();
            }
        };
        adsLunBoTu.show(adsList);//显示轮播图
        adsLunBoTu.setViewHeight((int) (UIUtils.getScreenWidth()/2.0f));
        adsLunBoTu.startRoll();//轮播图自动切换
        ListView refreshableView = plv_pingou.getRefreshableView();
        refreshableView.addHeaderView(adsLunBoTu, null, false);

    }
    /**
     * 刷洗·加载 网络操作
     *
     * @param page
     * @param requestCode
     */
    private void refresh(final int page, final int requestCode) {
        VolleyGsonRequest<BeanPingou> pingouRequest = new VolleyGsonRequest<BeanPingou>(UrlUtil.HOST + UrlUtil.PINGOU_LIST, BeanPingou.class, new Response.Listener<BeanPingou>() {
            @Override
            public void onResponse(BeanPingou res) {
                queryList=res.getRes().getQueryList();
                taskAdvertise=res.getRes().getTaskAdvertise();
                if (requestCode == RES_MORE && queryList.isEmpty()) {
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
                map.put("page", page + "");
                map.put("serviceType", "1");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(pingouRequest);
    }

    /**
     * 初始化刷新加载的内容
     */
    private void initIndicator() {
        ILoadingLayout downLabels = plv_pingou
                .getLoadingLayoutProxy(true, false);
        downLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        downLabels.setRefreshingLabel("正在刷新...");// 刷新时
        downLabels.setReleaseLabel("释放刷新...");// 下来达到一定距离时，显示的提示
        ILoadingLayout upLabels = plv_pingou.getLoadingLayoutProxy(false, true);
        upLabels.setPullLabel("上拉加载...");// 刚上拉时，显示的提示
        upLabels.setRefreshingLabel("正在加载...");// 加载时
        upLabels.setReleaseLabel("释放加载...");// 上来达到一定距离时，显示的提示
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //position从2开始
        int mPosition=position-2;
        BeanPingou.ResEntity.QueryListEntity queryListEntity = pingouLvAdapter.getData().get(mPosition);
        //0用户拼购  1商家拼购
        if (queryListEntity.getGroupMode()==0){
            intent = new Intent(mActivity, Detail_Pingou_User_Activity.class);

        }else{
            intent = new Intent(mActivity, Detail_Pingou_Store_Activity.class);
        }
        intent.putExtra("serviceId",queryListEntity.getServiceId()+"");
        intent.putExtra("serviceType","1");
        mActivity.startActivity(intent);

    }
    public void doThing(){
        Log.i(TAG, "doThing: ");
    }
}
