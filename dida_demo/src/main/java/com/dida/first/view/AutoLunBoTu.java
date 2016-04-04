package com.dida.first.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.MarketBean;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-26.
 */
public class AutoLunBoTu extends RelativeLayout{
    private Context context;
    private ViewPager vp_auto;
    private LinearLayout ll_auto_dot;
    private TextView tv_auto_title;
    private List<MarketBean.ResEntity.ProductAdsEntity> mAdsList=new ArrayList<MarketBean.ResEntity.ProductAdsEntity>();
    private List<View> dotList;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private RunnableTask mAutoRunnable;

    public AutoLunBoTu(Context context) {
        this(context, null);
    }

    public AutoLunBoTu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLunBoTu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.view_vp, this, true);
        vp_auto = (ViewPager) findViewById(R.id.vp_notitle_moveimgview);
        ll_auto_dot = (LinearLayout) findViewById(R.id.ll_notitle_moveimgview);
        tv_auto_title = (TextView) findViewById(R.id.tv_auto_title);
        initImageLoader();
    }

    public void show(List<MarketBean.ResEntity.ProductAdsEntity> list){
        this.mAdsList=list;
        initDot(list);
        initViewPager(list);
        tv_auto_title.setText(mAdsList.get(0).getAdName());

    }
    public void startRoll() {
        mAutoRunnable = new RunnableTask();
        mAutoRunnable.start();
    }
    private void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_img)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_img)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
    }

    /**
     * 初始化ViewPager
     * @param list
     */
    private void initViewPager(List<MarketBean.ResEntity.ProductAdsEntity> list) {
        vp_auto.setAdapter(new MyPagerAdapter());
        vp_auto.setOffscreenPageLimit(2);
        vp_auto.setOnPageChangeListener(onPageChangeListener);
    }

    /**
     * 初始化点
     * @param list
     */
    public void initDot(List<MarketBean.ResEntity.ProductAdsEntity> list) {
        dotList = new ArrayList<View>();
        LinearLayout llLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(8),UIUtils.dip2px(8));
        layoutParams.setMargins(0, 0, UIUtils.dip2px(8),0);
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
    private ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {

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
            tv_auto_title.setText(mAdsList.get(index).getAdName());
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
            ImageView imageView = (ImageView) UIUtils.inflate(R.layout.viewpager_item);
//            ImageView iv_viewpager_item = (ImageView) imageView.findViewById(R.id.iv_viewpager_item);
//            bitmapUtils.display(iv_viewpager_item, UrlUtil.HOST+mAdsList.get(position % mAdsList.size()).getAdUrl());
            imageLoader.displayImage(UrlUtil.HOST+mAdsList.get(position % mAdsList.size()).getAdUrl(), imageView,options);
            container.addView(imageView);
            return imageView;
        }
    }
    class RunnableTask implements Runnable {
        public void start() {
            UIUtils.removeCallback(mAutoRunnable);
            UIUtils.postDelayed(mAutoRunnable, 3000);


        }

        @Override
        public void run() {
            UIUtils.removeCallback(mAutoRunnable);
            int currentItem = vp_auto.getCurrentItem();
            vp_auto.setCurrentItem(currentItem + 1);
            UIUtils.postDelayed(mAutoRunnable, 3000);
        }

    }
}
