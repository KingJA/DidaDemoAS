package com.dida.first.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;
import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;

/**
 * @author KingJA
 * @data 2016-1-13 下午4:43:41
 * @use 搜索页面
 */
public abstract class BaseSearchActivity extends FragmentActivity implements
        OnClickListener {
    protected static final int RES_REFRESH = 0;//刷新
    protected static final int RES_MORE = 1;//加载更多
    protected static final int RES_ERROR = -1;//错误
    protected static final int RES_NOMORE = -3;//没有更多
    protected boolean mHasMore = true;//有无更多
    private static final String TAG = "BaseSearchActivity";
    protected View view;
    private final String[]mMainSort={"综合排序","价格从低到高","价格从高到低","信用排序"};
    protected LoadingAndRetryManager mLoadingAndRetryManager;
    private EditText et_content;
    private TextView tv_sort_main;
    private TextView tv_sort_sales;
    private TextView tv_sort_select;
    private ImageView iv_sort_main;
    private ImageView iv_sort_select;
    private boolean isSelectOpen;
    private boolean isMainOpen;
    private String searchContent;
    private LinearLayout ll_price_sort;
    private EditText et_price_from;
    private EditText et_price_to;
    private ViewGroup.LayoutParams priceLayoutParams;
    private int expandPriceHeight;
    private ValueAnimator valueAnimator;
    private Button btn_price_sort;
    private String priceFrom;
    private String priceTo;
    private LinearLayout ll_main_sort;
    private int expendMainHeight;
    private ViewGroup.LayoutParams mainLayoutParams;
    private TextView tv_item_main;
    private TextView tv_item_asc;
    private TextView tv_item_desc;
    private TextView tv_item_credit;
    private ImageView iv_item_main;
    private ImageView iv_item_asc;
    private ImageView iv_item_desc;
    private ImageView iv_item_credit;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_top_search);
        view = setView();
        initLoadPager();
        initTopView();
        initView();
        initNet();
        initEvent();
        initData();


    }

    private void initLoadPager() {
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
//                setMyRetryEvent(retryView);
            }
        });
        mLoadingAndRetryManager.showContent();
    }


    @Override
    public void onClick(View v) {
        onChildClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_item_main:
                selectMainSort(0);
                break;
            case R.id.rl_item_asc:
                selectMainSort(1);
                break;
            case R.id.rl_item_desc:
                selectMainSort(2);
                break;
            case R.id.rl_item_credit:
                selectMainSort(3);
                break;
            case R.id.iv_search:
                imm.hideSoftInputFromWindow(et_content.getWindowToken(), 0);
                clearFocus();

                searchContent = et_content.getText().toString().trim();
                onSearch(searchContent);
                break;
            case R.id.tv_sort_main:
                if (isSelectOpen){
                    expandPrice();
                }
                select(0);
                break;
            case R.id.tv_sort_sales:
                if (isSelectOpen){
                    expandPrice();
                }
                if (isMainOpen){
                    expandMain();
                }
                select(1);
                break;
            case R.id.tv_sort_select:
                if (isMainOpen){
                    expandMain();
                }
                select(2);
                break;
            case R.id.btn_price_sort:
                priceFrom = et_price_from.getText().toString().trim();
                priceTo = et_price_to.getText().toString().trim();
                onPriceFromToSort(searchContent, priceFrom, priceTo);
                expandPrice();
                isSelectOpen=false;
                break;
            default:
                break;
        }
    }

    private void clearFocus() {
        tv_sort_main.setFocusable(true);
        tv_sort_main.setFocusableInTouchMode(true);
        tv_sort_main.requestFocus();
        tv_sort_main.requestFocusFromTouch();
    }

    private void selectMainSort(int mainSortType) {
        resetMainSort(false);
        switch (mainSortType) {
            case 0:
                tv_sort_main.setText(mMainSort[0]);
                tv_item_main.setTextColor(UIUtils.getTextColor(R.color.red));
                iv_item_main.setVisibility(View.VISIBLE);

                break;
            case 1:
                tv_sort_main.setText(mMainSort[1]);
                tv_item_asc.setTextColor(UIUtils.getTextColor(R.color.red));
                iv_item_asc.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_sort_main.setText(mMainSort[2]);
                tv_item_desc.setTextColor(UIUtils.getTextColor(R.color.red));
                iv_item_desc.setVisibility(View.VISIBLE);
                break;
            case 3:
                tv_sort_main.setText(mMainSort[3]);
                tv_item_credit.setTextColor(UIUtils.getTextColor(R.color.red));
                iv_item_credit.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        expandMain();
        onMainSort(searchContent,mainSortType);

    }

    protected abstract void onMainSort(String name, int mainSortType);

    private void resetMainSort(boolean isFirst) {
        tv_item_main.setTextColor(UIUtils.getTextColor(R.color.black));
        tv_item_asc.setTextColor(UIUtils.getTextColor(R.color.black));
        tv_item_desc.setTextColor(UIUtils.getTextColor(R.color.black));
        tv_item_credit.setTextColor(UIUtils.getTextColor(R.color.black));
        iv_item_main.setVisibility(View.GONE);
        iv_item_asc.setVisibility(View.GONE);
        iv_item_desc.setVisibility(View.GONE);
        iv_item_credit.setVisibility(View.GONE);
        if (isFirst){
            tv_item_main.setTextColor(UIUtils.getTextColor(R.color.red));
            iv_item_main.setVisibility(View.VISIBLE);
        }

    }



    private void select(int position) {
        reset();
        switch (position) {
            case 0:
                isSelectOpen = false;
                tv_sort_main.setTextColor(UIUtils.getTextColor(R.color.red));
                expandMain();
                break;
            case 1:
                isMainOpen = false;
                isSelectOpen = false;
                tv_sort_sales.setTextColor(UIUtils.getTextColor(R.color.red));
                onSalesSort(searchContent);
                break;
            case 2:
                isMainOpen = false;
                tv_sort_select.setTextColor(UIUtils.getTextColor(R.color.red));
                expandPrice();
                break;
            default:
                break;
        }
    }

    private void initTopView() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        ImageView iv_search = (ImageView) findViewById(R.id.iv_search);
        et_content = (EditText) findViewById(R.id.et_content);
        FrameLayout fl_content = (FrameLayout) findViewById(R.id.fl_content);

        tv_sort_main = (TextView) findViewById(R.id.tv_sort_main);
        tv_sort_sales = (TextView) findViewById(R.id.tv_sort_sales);
        tv_sort_select = (TextView) findViewById(R.id.tv_sort_select);

        iv_sort_main = (ImageView) findViewById(R.id.iv_sort_main);
        iv_sort_select = (ImageView) findViewById(R.id.iv_sort_select);

        ll_price_sort = (LinearLayout) findViewById(R.id.ll_price_sort);
        et_price_from = (EditText) findViewById(R.id.et_price_from);
        et_price_to = (EditText) findViewById(R.id.et_price_to);
        btn_price_sort = (Button) findViewById(R.id.btn_price_sort);

        ll_main_sort = (LinearLayout) findViewById(R.id.ll_main_sort);

        RelativeLayout rl_item_main = (RelativeLayout) findViewById(R.id.rl_item_main);
        RelativeLayout rl_item_asc = (RelativeLayout) findViewById(R.id.rl_item_asc);
        RelativeLayout rl_item_desc = (RelativeLayout) findViewById(R.id.rl_item_desc);
        RelativeLayout rl_item_credit = (RelativeLayout) findViewById(R.id.rl_item_credit);

        tv_item_main = (TextView) findViewById(R.id.tv_item_main);
        tv_item_asc = (TextView) findViewById(R.id.tv_item_asc);
        tv_item_desc = (TextView) findViewById(R.id.tv_item_desc);
        tv_item_credit = (TextView) findViewById(R.id.tv_item_credit);

        iv_item_main = (ImageView) findViewById(R.id.iv_item_main);
        iv_item_asc = (ImageView) findViewById(R.id.iv_item_asc);
        iv_item_desc = (ImageView) findViewById(R.id.iv_item_desc);
        iv_item_credit = (ImageView) findViewById(R.id.iv_item_credit);

        resetMainSort(true);
        ViewTreeObserver vto = ll_price_sort.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_price_sort.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                expandPriceHeight = ll_price_sort.getHeight();
                expendMainHeight = ll_main_sort.getHeight();
                Log.i(TAG, "expandPriceHeight: " + expandPriceHeight);
                Log.i(TAG, "expendMainHeight: " + expendMainHeight);
                initPriceSortHeight();
                initMainSortHeight();
            }
        });

        tv_sort_main.setOnClickListener(this);
        tv_sort_sales.setOnClickListener(this);
        tv_sort_select.setOnClickListener(this);
        btn_price_sort.setOnClickListener(this);

        rl_item_main.setOnClickListener(this);
        rl_item_asc.setOnClickListener(this);
        rl_item_desc.setOnClickListener(this);
        rl_item_credit.setOnClickListener(this);

        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);

        View child = setView();
        if (child != null) {
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            fl_content.addView(child, params);
        }
    }

    private void initPriceSortHeight() {
        priceLayoutParams = ll_price_sort.getLayoutParams();
        priceLayoutParams.height = 0;
        ll_price_sort.setLayoutParams(priceLayoutParams);
    }

    private void initMainSortHeight() {
        mainLayoutParams = ll_main_sort.getLayoutParams();
        mainLayoutParams.height = 0;
        ll_main_sort.setLayoutParams(this.mainLayoutParams);
    }

    /**
     * 打开关闭价格区间
     */
    private void expandPrice() {
        if (isSelectOpen) {
            priceLayoutParams.height = 0;
            ll_price_sort.setLayoutParams(priceLayoutParams);
        } else {
            priceLayoutParams.height = 0;
            valueAnimator = ValueAnimator.ofInt(0, expandPriceHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator arg0) {
                    priceLayoutParams.height = (Integer) arg0.getAnimatedValue();
                    ll_price_sort.setLayoutParams(priceLayoutParams);
                }
            });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
        iv_sort_select.setBackgroundResource(isSelectOpen ? R.drawable.open_down_sel : R.drawable.open_up_sel);
        isSelectOpen = !isSelectOpen;

    }

    /**
     * 打开关闭综合排序
     */
    private void expandMain() {
        if (isMainOpen) {
            mainLayoutParams.height = 0;
            ll_main_sort.setLayoutParams(mainLayoutParams);
        } else {
            mainLayoutParams.height = 0;
            valueAnimator = ValueAnimator.ofInt(0, expendMainHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator arg0) {
                    mainLayoutParams.height = (Integer) arg0.getAnimatedValue();
                    ll_main_sort.setLayoutParams(mainLayoutParams);
                }
            });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
        iv_sort_main.setBackgroundResource(isMainOpen ? R.drawable.open_down_sel : R.drawable.open_up_sel);
        isMainOpen = !isMainOpen;
    }

    public void reset() {
        //重置字体颜色和箭头
        tv_sort_main.setTextColor(UIUtils.getTextColor(R.color.black));
        tv_sort_sales.setTextColor(UIUtils.getTextColor(R.color.black));
        tv_sort_select.setTextColor(UIUtils.getTextColor(R.color.black));
        iv_sort_main.setBackgroundResource(R.drawable.open_down_nor);
        iv_sort_select.setBackgroundResource(R.drawable.open_down_nor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openKeyboard();

    }

    private void openKeyboard() {
        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();
        imm = (InputMethodManager) et_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_content, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected abstract View setView();

    protected abstract void initView();

    protected abstract void initNet();

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void onChildClick(View v);

    protected abstract void onSearch(String name);

    protected abstract void onSalesSort(String name);
    protected abstract void onPriceFromToSort(String name, String lowPrice, String maxPrice);

//    protected abstract void setMyRetryEvent(View retryView);
}
