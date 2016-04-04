package com.dida.first.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.utils.FrescoManager;
import com.dida.first.utils.UIUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-1-11.
 */
public abstract class TitleMoveImgView<T> extends RelativeLayout {
    protected Context context;
    protected ViewPager vp_auto;
    protected LinearLayout ll_auto_dot;
    protected TextView tv_auto_title;
    protected List<T> mAdsList = new ArrayList<T>();
    protected List<View> dotList;
    protected RunnableTask mAutoRunnable;
    protected int mShowTime = 3000;
    private RelativeLayout rl_base_mylbu;
    private ViewGroup.LayoutParams layoutParams;
    private boolean isRunning;
    private List<SimpleDraweeView> mSdvList = new ArrayList<SimpleDraweeView>();

    public TitleMoveImgView(Context context) {
        this(context, null);
    }

    public TitleMoveImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleMoveImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    protected abstract void onTitleShow(TextView titleTv, List<T> mAdsList, int position);

    protected abstract String setImgUrl(List<T> mAdsList, int position);

    protected abstract void onInitShow(TextView titleTv, List<T> list);

    protected abstract void onItemClick(List<T> mAdsList, int position);

    public void setViewHeight(int px) {
        layoutParams.height = px;
        rl_base_mylbu.setLayoutParams(layoutParams);
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.view_vp, this, true);
        rl_base_mylbu = (RelativeLayout) findViewById(R.id.rl_base_mylbu);
        vp_auto = (ViewPager) findViewById(R.id.vp_notitle_moveimgview);
        ll_auto_dot = (LinearLayout) findViewById(R.id.ll_notitle_moveimgview);
        tv_auto_title = (TextView) findViewById(R.id.tv_auto_title);
        layoutParams = vp_auto.getLayoutParams();
    }

    public void show(List<T> list) {
        this.mAdsList = list;
        initData();
        initDot(list);
        initViewPager(list);
        onInitShow(tv_auto_title, list);
    }

    private void initData() {

        for (int i = 0; i < mAdsList.size(); i++) {
            SimpleDraweeView sdv = (SimpleDraweeView) UIUtils.inflate(R.layout.single_siv_lunbotu);
            FrescoManager.display(setImgUrl(mAdsList, i % mAdsList.size()), sdv);
            final int finalI = i;
            sdv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(mAdsList, finalI);
                }
            });
            mSdvList.add(sdv);
        }
    }


    public void setShowTime(int millis) {
        this.mShowTime = millis;
    }

    public void startRoll() {
        mAutoRunnable = new RunnableTask();
        mAutoRunnable.start();
        isRunning = true;
    }

    public void stopRoll() {
        if (isRunning) {
            mAutoRunnable.stop();
            isRunning = false;
        }

    }
    public boolean isRunning(){
        return isRunning;
    }

    /**
     * 初始化ViewPager
     *
     * @param list
     */
    private void initViewPager(List<T> list) {
        vp_auto.setAdapter(new MyPagerAdapter());
        vp_auto.addOnPageChangeListener(onPageChangeListener);
    }

    /**
     * 初始化点
     *
     * @param list
     */
    public void initDot(List<T> list) {
        dotList = new ArrayList<View>();
        LinearLayout llLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(8), UIUtils.dip2px(8));
        layoutParams.setMargins(0, 0, UIUtils.dip2px(8), 0);
        for (int i = 0; i < list.size(); i++) {
            View dotView = new View(context);
            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.icon_sel_1);

            } else {
                dotView.setBackgroundResource(R.drawable.icon_nor_1);
            }
            dotList.add(dotView);
            ll_auto_dot.addView(dotView, layoutParams);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            int index = position % dotList.size();
            for (int i = 0; i < dotList.size(); i++) {
                if (i == index) {
                    dotList.get(index).setBackgroundResource(R.drawable.icon_sel_1);
                } else {
                    dotList.get(i).setBackgroundResource(R.drawable.icon_nor_1);
                }
            }
            onTitleShow(tv_auto_title, mAdsList, position % mAdsList.size());
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mSdvList.get(position % mSdvList.size()));
            return mSdvList.get(position % mSdvList.size());
        }
    }

    class RunnableTask implements Runnable {
        public void start() {
            UIUtils.removeCallback(mAutoRunnable);
            UIUtils.postDelayed(mAutoRunnable, mShowTime);
        }

        @Override
        public void run() {
            UIUtils.removeCallback(mAutoRunnable);
            int currentItem = vp_auto.getCurrentItem();
            vp_auto.setCurrentItem(currentItem + 1);
            UIUtils.postDelayed(mAutoRunnable, mShowTime);
        }

        public void stop() {
            UIUtils.removeCallback(mAutoRunnable);
        }
    }


}
