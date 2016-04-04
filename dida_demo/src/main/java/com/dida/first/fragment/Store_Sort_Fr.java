package com.dida.first.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.interfaces.OnClickSortListener;
import com.dida.first.R;
import com.dida.first.entity.BeanPinGouGroup;
import com.dida.first.entity.BeanSortButton;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.FlowLayout;
import com.dida.first.view.MyGridViewTextView;

import java.util.ArrayList;
import java.util.List;


public class Store_Sort_Fr extends Base_First_Fragment implements OnClickSortListener {

    private OnClickSortListener onChickSortListener;
    private ImageView iv_sort_open;
    private FlowLayout fl_store_sort;
    private RelativeLayout rl_sort;
    private TextView tv_sort;
    private RecyclerView rv_store_sort;
    private int lastVisibleItem;
    private SortAdapter sortAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<BeanPinGouGroup> pinGouGroups = new ArrayList<BeanPinGouGroup>();
    private List<BeanSortButton> SortButtonList = new ArrayList<BeanSortButton>();
    private LinearLayout.LayoutParams param;
    private int itemWidth;
    private int expandHeight;
    private static String[] sortsArr = {"上衣", "韩版", "紧身", "欧版", "明星同款", "天蓝色",
            "长裤", "连体衣", "冬季恋歌", "爆款", "春装", "秋装", "时尚新款"};
    private boolean isOpen;
    private ViewGroup.LayoutParams layoutParams;
    private ValueAnimator valueAnimator;


    @Override
    public View setFragmentView() {
        view = View.inflate(context, R.layout.fragment_store_sort, null);

        return view;
    }


    @Override
    public void initFragmentView() {
        iv_sort_open = (ImageView) view.findViewById(R.id.iv_sort_open);
        rl_sort = (RelativeLayout) view.findViewById(R.id.rl_sort);
        rv_store_sort = (RecyclerView) view.findViewById(R.id.rv_store_sort);
        fl_store_sort = (FlowLayout) view.findViewById(R.id.fl_store_sort);
        tv_sort = (TextView) view.findViewById(R.id.tv_sort);
        initTreeObserver();

    }

    private void initHeight() {
        layoutParams = fl_store_sort.getLayoutParams();
        layoutParams.height = 0;
        fl_store_sort.setLayoutParams(layoutParams);
    }

    private void initTreeObserver() {

        ViewTreeObserver vto = fl_store_sort.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fl_store_sort.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                expandHeight = fl_store_sort.getHeight();
                initHeight();
            }
        });
    }

    @Override
    public void initFragmentNet() {
        initData();

    }


    @Override
    public void initFragmentEvent() {
        rl_sort.setOnClickListener(this);
        setOnClickSortListener(this);
        linearLayoutManager = new GridLayoutManager(context, 2);
        rv_store_sort.setLayoutManager(linearLayoutManager);
        sortAdapter = new SortAdapter();
        rv_store_sort.setAdapter(sortAdapter);
        rv_store_sort.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("onScrollStateChanged", "onScrollStateChanged");
                Log.i("lastVisibleItem", lastVisibleItem + "");
                Log.i("getItemCount", sortAdapter.getItemCount() + "");
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == sortAdapter.getItemCount()) {
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
        switch (v.getId()) {
            case R.id.rl_sort:
                expand();
                break;
            default:
                break;
        }

    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    private void expand() {
        if (isOpen) {
            //            展开=>关闭
            Log.i("关闭","关闭");
            layoutParams.height=0;
            valueAnimator = ValueAnimator.ofInt(expandHeight, 0);
        } else {
            Log.i("展开",expandHeight+"");
            //            关闭=>展开
            layoutParams.height=expandHeight;
            valueAnimator = ValueAnimator.ofInt(0, expandHeight);
        }
        iv_sort_open.setBackgroundResource(isOpen?R.drawable.open_down_nor :R.drawable.open_up_nor);
        isOpen=!isOpen;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                //设置给控件的长度值
                layoutParams.height = (Integer) arg0.getAnimatedValue();
                //作用在子控件(ll_footer)上
                fl_store_sort.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
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


        for (int i = 0; i < sortsArr.length; i++) {
            final BeanSortButton sortButton = new BeanSortButton();
            MyGridViewTextView tv = (MyGridViewTextView) UIUtils
                    .inflate(R.layout.textview_flowlayout);
            tv.setText(sortsArr[i]);
            sortButton.setTextView(tv);
            sortButton.setIndex(i);
            fl_store_sort.addView(tv);
            SortButtonList.add(sortButton);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChickSortListener.onChickSort(sortButton);
                }
            });
        }
    }

    @Override
    public void onChickSort(BeanSortButton sortButton) {
        for (int i = 0; i < SortButtonList.size(); i++) {
            MyGridViewTextView textView = SortButtonList.get(i).getTextView();
            textView.setBackgroundResource(R.drawable.shape_l1red_bwhite_r12);
            textView.setTextColor(getResources().getColor(R.color.black));
        }
        sortButton.getTextView().setBackgroundResource(R.drawable.shape_bg12red);
        sortButton.getTextView().setTextColor(getResources().getColor(R.color.white));
        tv_sort.setText(sortsArr[sortButton.getIndex()]);
        expand();
    }


    class SortAdapter extends RecyclerView.Adapter<SortAdapter.MyViewHolder> {

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

    public void setOnClickSortListener(OnClickSortListener onChickSortListener) {
        this.onChickSortListener = onChickSortListener;
    }

}