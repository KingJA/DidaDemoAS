package com.dida.first.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.adapter.FavPingouAdapter;
import com.dida.first.entity.BeanFavPingou;
import com.dida.first.favpingou.presenter.FavPingouPresenterImpl;
import com.dida.first.favpingou.view.FavPingouView;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KingJA on 2015-12-16.
 */
public class FavPingou_Fragment extends Base_Second_Fragment implements FavPingouView, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private static final String TAG = "FavPingou_Fragment";
    private List<BeanFavPingou.ResEntity.GroupCollectionEntity> mFavPingouList = new ArrayList<>();
    private ListView lv_single;
    private FavPingouPresenterImpl favPingouPresenter;
    private FavPingouAdapter favPingouAdapter;
    private SwipeRefreshLayout swipe_single;
    private int mType;
    private int mPage = 1;


    public static FavPingou_Fragment getInstance(int type) {
        FavPingou_Fragment myPingou__fragment = new FavPingou_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        myPingou__fragment.setArguments(bundle);
        return myPingou__fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("TYPE", 1);
        Log.i(TAG, "mType: " + mType);
    }

    @Override
    public View setFragmentView() {
        view = View.inflate(mActivity, R.layout.single_lv, null);
        return view;
    }

    @Override
    public void initFragmentView() {
        lv_single = (ListView) view.findViewById(R.id.lv_single);
        swipe_single = (SwipeRefreshLayout) view.findViewById(R.id.swipe_single);
        favPingouPresenter = new FavPingouPresenterImpl(this);
        favPingouAdapter = new FavPingouAdapter(mFavPingouList, mActivity);
    }

    @Override
    public void initFragmentNet() {


    }

    @Override
    public void initFragmentEvent() {
        swipe_single.setOnRefreshListener(this);
        swipe_single.setColorSchemeColors(0xFFFF5A5F);
        lv_single.setAdapter(favPingouAdapter);
        lv_single.setOnItemClickListener(this);
        lv_single.setOnScrollListener(onScrollListener);
    }

    @Override
    public void initFragmentData() {
        favPingouPresenter.loadNet("fb9a38d82cd3405a9b60ec54cdb5ecdf", 1, 1, mType);
    }

    @Override
    public void onChildClick(View v) {

    }

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (lv_single.getLastVisiblePosition() == (lv_single.getCount() - 1)) {
                        Log.e("log", "滑到底部");
                        if (mHasMore) {
                            favPingouPresenter.loadMore("fb9a38d82cd3405a9b60ec54cdb5ecdf", ++mPage, 1, mType);
                        }else{
                            ToastUtil.showMyToast("到底啦，亲！");
                        }
                    }
                    if (lv_single.getFirstVisiblePosition() == 0) {
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
    public void showProgress() {
        swipe_single.setProgressViewOffset(false, 0, UIUtils.dip2px(24));
        swipe_single.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_single.setRefreshing(false);
    }

    @Override
    public void setData(BeanFavPingou data) {
        mFavPingouList = data.getRes().getGroupCollection();
        Log.i(TAG, "setData: " + data.getRes().getGroupCollection().size());
        favPingouAdapter.setData(mFavPingouList);
    }

    @Override
    public void addData(BeanFavPingou data) {
        mFavPingouList = data.getRes().getGroupCollection();
        if (mFavPingouList.size() > 0) {
            favPingouAdapter.addData(mFavPingouList);
        } else {
            mHasMore = false;
//            ToastUtil.showMyToast("到底啦，亲！");
        }

    }

    @Override
    public void showError() {
        ToastUtil.showMyToast("服务器君躲猫猫了！");
    }

    @Override
    public void onRefresh() {
        mPage=1;
        mHasMore=true;
        favPingouPresenter.loadNet("fb9a38d82cd3405a9b60ec54cdb5ecdf", 1, 1, mType);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BeanFavPingou.ResEntity.GroupCollectionEntity itemAtPosition = (BeanFavPingou.ResEntity.GroupCollectionEntity) parent.getItemAtPosition(position);
        if (itemAtPosition.getGroupStatus() == 4) {
            //TODO
            ToastUtil.showMyToast("TODO 进入详情");
        }
    }
}
