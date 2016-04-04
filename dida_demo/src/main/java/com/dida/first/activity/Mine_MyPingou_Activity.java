/**
 *
 */
package com.dida.first.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.fragment.MyPingou_Fragment;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-10-14 下午1:22:21
 * @use
 */
public class Mine_MyPingou_Activity extends BackTitleActivity {
    private static final String TAG = "Mine_MyPingou_Activity";
    private ViewPager vp_mine_mypingou;
    private FrameLayout fl_mypingou_tab;

    private TextView tv__mypingou_mime;
    private TextView tv__mypingou_join;
    private TextView tv__mypingou_invate;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private static final int TAB_COUNT = 3;
    private LinearLayout.LayoutParams layoutParams;
    private int screenWidth;
    private int tabWidth;
    private MyAdapter myAdapter;

    @Override
    public View setView() {
        view = View.inflate(this,R.layout.activity_mine_pingou, null);
        return view;
    }

    @Override
    public void initView() {

        vp_mine_mypingou = (ViewPager) view.findViewById(R.id.vp_mine_mypingou);
        fl_mypingou_tab = (FrameLayout) view.findViewById(R.id.fl_mypingou_tab);
        tv__mypingou_mime = (TextView) view.findViewById(R.id.tv__mypingou_mime);
        tv__mypingou_join = (TextView) view.findViewById(R.id.tv__mypingou_join);
        tv__mypingou_invate = (TextView) view.findViewById(R.id.tv__mypingou_invate);

        fragmentList.add( MyPingou_Fragment.getInstance(1));
        fragmentList.add( MyPingou_Fragment.getInstance(2));
        fragmentList.add( MyPingou_Fragment.getInstance(3));

    }

    @Override
    public void initDoNet() {
        initTab();


    }

    private void initTab() {
        screenWidth = UIUtils.getScreenWidth();
        tabWidth = screenWidth / TAB_COUNT;
        LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_mypingou_tab.getLayoutParams();
        layoutParam.width = tabWidth;
        fl_mypingou_tab.setLayoutParams(layoutParam);
    }

    @Override
    public void initEvent() {
        myAdapter = new MyAdapter(getSupportFragmentManager());
        vp_mine_mypingou.setAdapter(myAdapter);
        vp_mine_mypingou.addOnPageChangeListener(onPageChangeListener);
        vp_mine_mypingou.setOffscreenPageLimit(2);
        tv__mypingou_mime.setOnClickListener(this);
        tv__mypingou_join.setOnClickListener(this);
        tv__mypingou_invate.setOnClickListener(this);

    }

    @Override
    public void initData() {
        setBackTitle("我的拼购");
    }


    @Override
    public void onChildClick(View v) {

        switch (v.getId()) {
            case R.id.tv__mypingou_mime:
                select(0);
                break;
            case R.id.tv__mypingou_join:
                select(1);
                break;
            case R.id.tv__mypingou_invate:
                select(2);
                break;
            default:
                break;
        }

    }

    @Override
    public void setBackClick() {
        finish();

    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    private int currentPosition;
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            reSet();
            currentPosition = position;
            switch (position) {
                case 0:
                    tv__mypingou_mime.setTextColor(getResources().getColor(R.color.red));
                    break;
                case 1:
                    tv__mypingou_join.setTextColor(getResources().getColor(R.color.red));
                    break;
                case 2:
                    tv__mypingou_invate.setTextColor(getResources().getColor(R.color.red));
                    break;
                default:
                    break;
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
//            Log.i("onPageScrolled", "currentPosition=" + currentPosition + "  position=" + position + "  偏移百分比=" + positionOffset + "  偏移像素=" + positionOffsetPixels);
            // 向右滑动
            if (currentPosition == position) {
                LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_mypingou_tab.getLayoutParams();
                layoutParam.leftMargin = (int) (currentPosition * tabWidth + tabWidth
                        * positionOffset);
                fl_mypingou_tab.setLayoutParams(layoutParam);
            }
            // 向左滑动
            else if (currentPosition > position) {
                LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_mypingou_tab.getLayoutParams();
                layoutParam.leftMargin = (int) (currentPosition * tabWidth - tabWidth
                        * (1 - positionOffset));
                fl_mypingou_tab.setLayoutParams(layoutParam);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void reSet() {
        tv__mypingou_mime.setTextColor(getResources().getColor(R.color.black));
        tv__mypingou_join.setTextColor(getResources().getColor(R.color.black));
        tv__mypingou_invate.setTextColor(getResources().getColor(R.color.black));
    }

    private void select(int position) {
        Log.i(TAG, "select: "+position);
        reSet();
        switch (position) {
            case 0:
                tv__mypingou_mime.setTextColor(getResources().getColor(R.color.red));
                vp_mine_mypingou.setCurrentItem(0);
                break;
            case 1:
                tv__mypingou_join.setTextColor(getResources().getColor(R.color.red));
                vp_mine_mypingou.setCurrentItem(1);
                break;
            case 2:
                tv__mypingou_invate.setTextColor(getResources().getColor(R.color.red));
                vp_mine_mypingou.setCurrentItem(2);
                break;
            default:
                break;
        }
    }


}
