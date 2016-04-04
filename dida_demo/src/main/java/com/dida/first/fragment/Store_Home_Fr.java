package com.dida.first.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dida.first.R;
import com.dida.first.entity.BeanPinGouGroup;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class Store_Home_Fr extends Base_First_Fragment {

    private RecyclerView mRecyclerView;
    private int lastVisibleItem;
    private HomeAdapter homeAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<BeanPinGouGroup> pinGouGroups = new ArrayList<BeanPinGouGroup>();
    private LinearLayout.LayoutParams param;
    private int itemWidth;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fragment_store_home, null);
        return view;
    }


    @Override
    public void initFragmentView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_store_home);
    }

    @Override
    public void initFragmentNet() {
        initData();

    }


    @Override
    public void initFragmentEvent() {
        linearLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(homeAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("onScrollStateChanged", "onScrollStateChanged");
                Log.i("lastVisibleItem", lastVisibleItem + "");
                Log.i("getItemCount", homeAdapter.getItemCount() + "");
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == homeAdapter.getItemCount()) {
                    Log.i("底部", "底部");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("onScrolled", lastVisibleItem + "");
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });
    }

    @Override
    public void initFragmentData() {

    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initData() {
        int screenWidth = UIUtils.getScreenWidth();
        itemWidth = (screenWidth - 3 * UIUtils.dip2px(4)) / 2;
        param = new LinearLayout.LayoutParams(itemWidth, itemWidth);
        for (int i = 0; i < 20; i++) {
            pinGouGroups.add(new BeanPinGouGroup());
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.item_marketlist,
                    null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.iv_market_item.setLayoutParams(param);
        }

        @Override
        public int getItemCount() {
            return pinGouGroups.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_market_item;

            public MyViewHolder(View view) {
                super(view);
                iv_market_item = (ImageView) view.findViewById(R.id.iv_market_item_img);
            }
        }
    }

}
