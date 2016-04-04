package com.dida.first.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.adapter.PingouCommentAdapter;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanPingouComment;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by KingJA on 2016-1-8.
 */
public class CommentPinggouActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "CommentPinggouActivity";
    private List<BeanPingouComment.ResEntity.ReplyListEntity> replyList = new ArrayList<BeanPingouComment.ResEntity.ReplyListEntity>();
    private ListView lv_single;
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
                    ToastUtil.showMyToast("更新数据 page=" + mPage + " 数据量=" + replyList.size());
                    pingouCommentAdapter.setData(replyList);
                    break;
                case RES_MORE:
                    ToastUtil.showMyToast("加载更多数据 page=" + mPage + " 数据量=" + replyList.size());
                    pingouCommentAdapter.addData(replyList);
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
    private PingouCommentAdapter pingouCommentAdapter;
    private SwipeRefreshLayout swipe_single;


    @Override
    public View setView() {
        view = View.inflate(this, R.layout.single_lv, null);
        return view;
    }

    @Override
    public void initView() {
        lv_single = (ListView) view.findViewById(R.id.lv_single);
        swipe_single = (SwipeRefreshLayout) view.findViewById(R.id.swipe_single);
        pingouCommentAdapter = new PingouCommentAdapter(replyList, CommentPinggouActivity.this);
    }

    @Override
    public void initDoNet() {

    }

    private void onRefresh(final int page, final int requestCode) {
        Log.i(TAG, "onRefresh: "+mPage);
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_PINGOU_MORE_COMMENT))
                .addParams("serviceId", "2028")
                .addParams("page", page+"")
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanPingouComment>(BeanPingouComment.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        mHandler.sendEmptyMessage(RES_ERROR);
                    }

                    @Override
                    public void onResponse(BeanPingouComment response) {
                        replyList = response.getRes().getReplyList();
                        Log.i(TAG, "JsonResponse: " + response.getMsg());

                        if (requestCode == RES_MORE && replyList.isEmpty()) {
                            ToastUtil.showMyToast("没有更多商品");
                            mHasMore = false;
                            mHandler.sendEmptyMessage(RES_NOMORE);
                        }
                        mHandler.sendEmptyMessage(requestCode);
                    }
                });
    }

    @Override
    public void initEvent() {
        lv_single.setAdapter(pingouCommentAdapter);
        swipe_single.setOnRefreshListener(this);
        swipe_single.setColorSchemeColors(0xFFFF5A5F);
        lv_single.setOnScrollListener(onScrollListener);
    }

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                // 当不滚动时
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (lv_single.getLastVisiblePosition() == (lv_single.getCount() - 1)) {
                        Log.e("log", "滑到底部");
                        if (mHasMore) {
                            onRefresh(++mPage,RES_MORE);
                        } else {
                            mHandler.sendEmptyMessage(RES_NOMORE);
                        }

                    }
                    if(lv_single.getFirstVisiblePosition() == 0){
                        Log.e("log", "滑到顶部");

                    }
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    @Override
    public void initData() {
        setBackTitle("全部评论");
        onRefresh(1, RES_REFRESH);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setBackClick() {
        finish();
    }


    @Override
    public void onRefresh() {
        onRefresh(1, RES_REFRESH);
    }
}
