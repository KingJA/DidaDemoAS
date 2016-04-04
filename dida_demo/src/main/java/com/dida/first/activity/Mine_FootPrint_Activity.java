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
 * @data 2015-9-24 下午1:35:14
 * @use  收藏的足迹
 * 
 */
public class Mine_FootPrint_Activity extends BackTitleActivity {

	private static final String TAG = "Mine_MyPingou_Activity";
	private ViewPager vp_mine_myfootprint;
	private FrameLayout fl_myfootprint_tab;

	private TextView tv_myfootprint_real;
	private TextView tv_myfootprint_service;
	private TextView tv_myfootprint_pingou;

	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private static final int TAB_COUNT = 3;
	private LinearLayout.LayoutParams layoutParams;
	private int screenWidth;
	private int tabWidth;
	private MyAdapter myAdapter;

	@Override
	public View setView() {
		view = View.inflate(this,R.layout.activity_mine_footprint, null);
		return view;
	}

	@Override
	public void initView() {

		vp_mine_myfootprint = (ViewPager) view.findViewById(R.id.vp_mine_myfootprint);
		fl_myfootprint_tab = (FrameLayout) view.findViewById(R.id.fl_myfootprint_tab);
		tv_myfootprint_real = (TextView) view.findViewById(R.id.tv_myfootprint_real);
		tv_myfootprint_service = (TextView) view.findViewById(R.id.tv_myfootprint_service);
		tv_myfootprint_pingou = (TextView) view.findViewById(R.id.tv_myfootprint_pingou);

		fragmentList.add(new MyPingou_Fragment());
//		fragmentList.add(new MyPingou_Join_Fragment());
//		fragmentList.add(new MyPingou_Invate_Fragment());

	}

	@Override
	public void initDoNet() {
		initTab();


	}

	private void initTab() {
		screenWidth = UIUtils.getScreenWidth();
		tabWidth = screenWidth / TAB_COUNT;
		LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_myfootprint_tab.getLayoutParams();
		layoutParam.width = tabWidth;
		fl_myfootprint_tab.setLayoutParams(layoutParam);
	}

	@Override
	public void initEvent() {
		myAdapter = new MyAdapter(getSupportFragmentManager());
		vp_mine_myfootprint.setAdapter(myAdapter);
		vp_mine_myfootprint.addOnPageChangeListener(onPageChangeListener);
		vp_mine_myfootprint.setOffscreenPageLimit(2);
		tv_myfootprint_real.setOnClickListener(this);
		tv_myfootprint_service.setOnClickListener(this);
		tv_myfootprint_pingou.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("我的拼购");
	}


	@Override
	public void onChildClick(View v) {

		switch (v.getId()) {
			case R.id.tv_myfootprint_real:
				select(0);
				break;
			case R.id.tv_myfootprint_service:
				select(1);
				break;
			case R.id.tv_myfootprint_pingou:
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
					tv_myfootprint_real.setTextColor(getResources().getColor(R.color.red));
					break;
				case 1:
					tv_myfootprint_service.setTextColor(getResources().getColor(R.color.red));
					break;
				case 2:
					tv_myfootprint_pingou.setTextColor(getResources().getColor(R.color.red));
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
				LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_myfootprint_tab.getLayoutParams();
				layoutParam.leftMargin = (int) (currentPosition * tabWidth + tabWidth
						* positionOffset);
				fl_myfootprint_tab.setLayoutParams(layoutParam);
			}
			// 向左滑动
			else if (currentPosition > position) {
				LinearLayout.LayoutParams layoutParam = (LinearLayout.LayoutParams) fl_myfootprint_tab.getLayoutParams();
				layoutParam.leftMargin = (int) (currentPosition * tabWidth - tabWidth
						* (1 - positionOffset));
				fl_myfootprint_tab.setLayoutParams(layoutParam);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};
	private void reSet() {
		tv_myfootprint_real.setTextColor(getResources().getColor(R.color.black));
		tv_myfootprint_service.setTextColor(getResources().getColor(R.color.black));
		tv_myfootprint_pingou.setTextColor(getResources().getColor(R.color.black));
	}

	private void select(int position) {
		Log.i(TAG, "select: "+position);
		reSet();
		switch (position) {
			case 0:
				tv_myfootprint_real.setTextColor(getResources().getColor(R.color.red));
				vp_mine_myfootprint.setCurrentItem(0);
				break;
			case 1:
				tv_myfootprint_service.setTextColor(getResources().getColor(R.color.red));
				vp_mine_myfootprint.setCurrentItem(1);
				break;
			case 2:
				tv_myfootprint_pingou.setTextColor(getResources().getColor(R.color.red));
				vp_mine_myfootprint.setCurrentItem(2);
				break;
			default:
				break;
		}
	}

}
