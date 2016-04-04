package com.dida.first.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanPinGouGroup;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class Store_All_Fr extends Base_First_Fragment {
    private RecyclerView rv_store_all;
    private LinearLayout ll_sort_all;
    private LinearLayout ll_sort_count;
    private LinearLayout ll_sort_new;
    private LinearLayout ll_sort_price;
    private TextView tv_sort_all;
    private TextView tv_sort_count;
    private TextView tv_sort_new;
    private TextView tv_sort_price;
    private View view_sort_all;
    private View view_sort_count;
    private View view_sort_new;
    private View view_sort_price;
    private ImageView iv_store_sort;
    private boolean isUp=true;
    private List<BeanPinGouGroup> pinGouGroups = new ArrayList<BeanPinGouGroup>();
    private LinearLayout.LayoutParams param;
    private int itemWidth;
    private AllAdapter allAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;
    @Override
    public View setFragmentView() {
        view=View.inflate(getActivity(), R.layout.fragment_store_all,null);

        return view;
    }

    @Override
    public void initFragmentView() {
        rv_store_all =(RecyclerView) view.findViewById(R.id.rv_store_all);

        ll_sort_all =(LinearLayout) view.findViewById(R.id.ll_sort_all);
        ll_sort_count =(LinearLayout) view.findViewById(R.id.ll_sort_count);
        ll_sort_new =(LinearLayout) view.findViewById(R.id.ll_sort_new);
        ll_sort_price =(LinearLayout) view.findViewById(R.id.ll_sort_price);

        tv_sort_all =(TextView) view.findViewById(R.id.tv_sort_main);
        tv_sort_count =(TextView) view.findViewById(R.id.tv_sort_count);
        tv_sort_new =(TextView) view.findViewById(R.id.tv_sort_new);
        tv_sort_price =(TextView) view.findViewById(R.id.tv_sort_price);

        view_sort_all =(View) view.findViewById(R.id.view_sort_all);
        view_sort_count =(View) view.findViewById(R.id.view_sort_count);
        view_sort_new =(View) view.findViewById(R.id.view_sort_new);
        view_sort_price =(View) view.findViewById(R.id.view_sort_price);

        iv_store_sort =(ImageView) view.findViewById(R.id.iv_store_sort);
    }

    @Override
    public void initFragmentNet() {
        int screenWidth = UIUtils.getScreenWidth();
        itemWidth = (screenWidth - 3 * UIUtils.dip2px(4)) / 2;
        param = new LinearLayout.LayoutParams(itemWidth, itemWidth);
        for (int i = 0; i < 20; i++) {
            pinGouGroups.add(new BeanPinGouGroup());
        }
    }

    @Override
    public void initFragmentEvent() {
        ll_sort_all.setOnClickListener(this);
        ll_sort_count.setOnClickListener(this);
        ll_sort_new.setOnClickListener(this);
        ll_sort_price.setOnClickListener(this);
        linearLayoutManager = new GridLayoutManager(context, 2);
        rv_store_all.setLayoutManager(linearLayoutManager);
        allAdapter = new AllAdapter();
        rv_store_all.setAdapter(allAdapter);
        rv_store_all.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("onScrollStateChanged", "onScrollStateChanged");
                Log.i("lastVisibleItem", lastVisibleItem + "");
                Log.i("getItemCount", allAdapter.getItemCount() + "");
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == allAdapter.getItemCount()) {
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
        reSet();
        switch (v.getId()){
            case R.id.ll_sort_all:
                isUp=true;
                tv_sort_all.setTextColor(getResources().getColor(R.color.red));
                view_sort_all.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_sort_count:
                isUp=true;
                tv_sort_count.setTextColor(getResources().getColor(R.color.red));
                view_sort_count.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_sort_new:
                isUp=true;
                tv_sort_new.setTextColor(getResources().getColor(R.color.red));
                view_sort_new.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_sort_price:
                tv_sort_price.setTextColor(getResources().getColor(R.color.red));
                view_sort_price.setVisibility(View.VISIBLE);
                if (isUp){
                    iv_store_sort.setBackgroundResource(R.drawable.sort_up);
                }else{
                    iv_store_sort.setBackgroundResource(R.drawable.sort_down);
                }
                isUp=!isUp;
                break;
            default:
                break;
        }

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    public void reSet(){
        tv_sort_all.setTextColor(getResources().getColor(R.color.black));
        tv_sort_count.setTextColor(getResources().getColor(R.color.black));
        tv_sort_new.setTextColor(getResources().getColor(R.color.black));
        tv_sort_price.setTextColor(getResources().getColor(R.color.black));

        view_sort_all.setVisibility(View.INVISIBLE);
        view_sort_count.setVisibility(View.INVISIBLE);
        view_sort_new.setVisibility(View.INVISIBLE);
        view_sort_price.setVisibility(View.INVISIBLE);
        iv_store_sort.setBackgroundResource(R.drawable.sort_nor);
    }
    class AllAdapter extends RecyclerView.Adapter<AllAdapter.MyViewHolder> {

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
