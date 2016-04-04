package com.dida.first.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dida.first.R;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-3.
 * 引导页面
 */
public class Guide_Activity extends Activity {

    private final int[] idsArr = {R.drawable.guide_0, R.drawable.guide_1, R.drawable.guide_2};
    private static final int SIZE_DOT = 8;
    private List<ImageView> imageList = new ArrayList<ImageView>();
    private List<ImageView> dotList = new ArrayList<ImageView>();
    private ViewPager vp_guide;
    private LinearLayout ll_sort_dots;
    private Button btn_gudie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initNet();
        initListener();
        initData();
    }

    private void initView() {
        btn_gudie = (Button) findViewById(R.id.btn_gudie);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        ll_sort_dots = (LinearLayout) findViewById(R.id.ll_sort_dots);

    }

    private void initNet() {
        initImgs();
        initDots();

    }

    /**
     * 初始化点
     */
    private void initDots() {
        int size = UIUtils.dip2px(SIZE_DOT);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.setMargins(0, 0, size, 0);
        for (int i = 0; i < idsArr.length; i++) {
            ImageView dot = new ImageView(this);
            if (i == 0) {
                dot.setBackgroundResource(R.drawable.icon_sel_1);
            } else {
                dot.setBackgroundResource(R.drawable.icon_nor_1);
            }
            dotList.add(dot);
            ll_sort_dots.addView(dot, layoutParams);

        }

    }

    /**
     * 初始化图片
     */
    private void initImgs() {
        for (int i = 0; i < idsArr.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(idsArr[i]);
            imageList.add(iv);
        }
    }

    private void initListener() {
        vp_guide.setAdapter(new MyPagerAdapter());
        vp_guide.setOnPageChangeListener(onPageChangeListener);
    }

    private void initData() {

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 让点的状态随着图片改变
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < idsArr.length; i++) {
                if (i == position) {
                    dotList.get(i).setBackgroundResource(R.drawable.icon_sel_1);
                } else {
                    dotList.get(i).setBackgroundResource(R.drawable.icon_nor_1);
                }
            }
            /**
             * 最后个页面显示进入按钮
             */
            if (position==idsArr.length-1){
                btn_gudie.setVisibility(View.VISIBLE);
                btn_gudie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("进入","进入");
                    }
                });
            }else{
                btn_gudie.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return idsArr.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
