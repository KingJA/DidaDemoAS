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

import com.dida.first.R;
import com.dida.first.interfaces.OnShowItemListener;
import com.dida.first.utils.FrescoManager;
import com.dida.first.utils.UIUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-26.
 */
public abstract class NoTitleMoveImgView<T> extends RelativeLayout {
    private boolean isRunning;
    private Context context;
    private ViewPager vp_notitle_moveimgview;
    private LinearLayout ll_notitle_moveimgview;
    private List<T> mAdsList = new ArrayList<T>();
    private List<View> dotList;
    private RunnableTask mAutoRunnable;
    private OnShowItemListener<T> onShowItemListener;
    private RelativeLayout rl_notitle_moveimgview;
    private ViewGroup.LayoutParams layoutParams;
    protected int mShowTime = 3000;
    private List<SimpleDraweeView> mSdvList = new ArrayList<SimpleDraweeView>();

    public NoTitleMoveImgView(Context context) {
        this(context, null);
    }

    public NoTitleMoveImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoTitleMoveImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }
    protected abstract void onItemClick(List<T> mAdsList, int position);
    private void init() {
        LayoutInflater.from(context).inflate(R.layout.view_vp_notitle, this, true);
        vp_notitle_moveimgview = (ViewPager) findViewById(R.id.vp_notitle_moveimgview);
        ll_notitle_moveimgview = (LinearLayout) findViewById(R.id.ll_notitle_moveimgview);
        rl_notitle_moveimgview = (RelativeLayout) findViewById(R.id.rl_notitle_moveimgview);
        layoutParams = rl_notitle_moveimgview.getLayoutParams();
    }

    public void setViewHeight(int px) {
        layoutParams.height = px;
        rl_notitle_moveimgview.setLayoutParams(layoutParams);
    }

    public void setShowTime(int millis) {
        this.mShowTime = millis;
    }
    public void show(List<T> list) {
        this.mAdsList = list;
        initData();
        initDot(list);
        initViewPager(list);
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
    public void startRoll() {
        mAutoRunnable = new RunnableTask();
        mAutoRunnable.start();
        isRunning=true;
    }
    public void stopRoll() {
        if (isRunning){
            mAutoRunnable.stop();
            isRunning=false;
        }

    }


    /**
     * 初始化ViewPager
     *
     * @param list
     */
    private void initViewPager(List<T> list) {
        if (list.size()>0){
            vp_notitle_moveimgview.setAdapter(new MyPagerAdapter());
            vp_notitle_moveimgview.addOnPageChangeListener(onPageChangeListener);
        }

    }

    /**
     * 初始化点
     *
     * @param list
     */
    public void initDot(List<T> list) {
        dotList = new ArrayList<View>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(6), UIUtils.dip2px(6));
        layoutParams.setMargins(0, 0, UIUtils.dip2px(4), 0);
        for (int i = 0; i < list.size(); i++) {
            View dotView = new View(context);
            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.icon_sel_1);

            } else {
                dotView.setBackgroundResource(R.drawable.icon_nor_1);
            }
            dotList.add(dotView);
            ll_notitle_moveimgview.addView(dotView, layoutParams);
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

    protected abstract String setImgUrl(List<T> mAdsList, int position);

    class RunnableTask implements Runnable {
        public void start() {
            UIUtils.removeCallback(mAutoRunnable);
            UIUtils.postDelayed(mAutoRunnable, mShowTime);
        }
        public void stop() {
            UIUtils.removeCallback(mAutoRunnable);
        }

        @Override
        public void run() {
            UIUtils.removeCallback(mAutoRunnable);
            int currentItem = vp_notitle_moveimgview.getCurrentItem();
            vp_notitle_moveimgview.setCurrentItem(currentItem + 1);
            UIUtils.postDelayed(mAutoRunnable, mShowTime);
        }

    }

    public void setOnShowItemListener(OnShowItemListener<T> onShowItemListener) {
        this.onShowItemListener = onShowItemListener;
    }
}
