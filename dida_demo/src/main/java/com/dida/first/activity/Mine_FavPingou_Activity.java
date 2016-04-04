/**
 * 
 */
package com.dida.first.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.fragment.FavPingou_Fragment;
import com.dida.first.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-10-16 上午11:03:56
 * @use
 * 
 */
public class Mine_FavPingou_Activity extends BackTitleActivity {

	private ViewPager vp_mine_product;
	private FrameLayout iv_product_tab;
	private TextView tv__product_real;
	private TextView tv__product_service;
	private TextView tv__product_group;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private static final int TAB_COUNT = 3;

	@Override
	public View setView() {
		view = View.inflate(Mine_FavPingou_Activity.this,
				R.layout.activity_mine_product, null);
		return view;
	}

	@Override
	public void initView() {
		vp_mine_product = (ViewPager) view.findViewById(R.id.vp_mine_product);
		iv_product_tab = (FrameLayout) view.findViewById(R.id.iv_product_tab);
		tv__product_real = (TextView) view.findViewById(R.id.tv__product_real);
		tv__product_service = (TextView) view
				.findViewById(R.id.tv__product_service);
		tv__product_group = (TextView) view
				.findViewById(R.id.tv__product_group);
		myAdapter = new MyAdapter(getSupportFragmentManager());

	}

	@Override
	public void initDoNet() {
		initTab();
		fragmentList.add( FavPingou_Fragment.getInstance(99));
		fragmentList.add( FavPingou_Fragment.getInstance(4));
		fragmentList.add( FavPingou_Fragment.getInstance(2));

	}

	private void initTab() {
		screenWidth = UIUtils.getScreenWidth();
		tabWidth = screenWidth / TAB_COUNT;
		LinearLayout.LayoutParams layoutParam = (LayoutParams) iv_product_tab.getLayoutParams();
		layoutParam.width = tabWidth;
		iv_product_tab.setLayoutParams(layoutParam);
//		Log.i("screenWidth", screenWidth+"");
//		Log.i("tabWidth", tabWidth+"");
	}

	@Override
	public void initEvent() {
		
		vp_mine_product.setAdapter(myAdapter);
		vp_mine_product.addOnPageChangeListener(onPageChangeListener);
		vp_mine_product.setOffscreenPageLimit(TAB_COUNT-1);
		tv__product_real.setOnClickListener(this);
		tv__product_service.setOnClickListener(this);
		tv__product_group.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("收藏的拼购");

	}

	@Override
	public void onChildClick(View v) {
		
		switch (v.getId()) {
		case R.id.tv__product_real:
			select(0);
			break;
		case R.id.tv__product_service:
			select(1);
			break;
		case R.id.tv__product_group:
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

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
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
				tv__product_real.setTextColor(getResources().getColor(R.color.red));
			
				break;
			case 1:
				tv__product_service.setTextColor(getResources().getColor(R.color.red));
				
				break;
			case 2:
				tv__product_group.setTextColor(getResources().getColor(R.color.red));
				
				break;

			default:
				break;
			}

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
//			Log.i("onPageScrolled", "currentPosition="+currentPosition+"  position="+position+"  偏移百分比="+positionOffset+"  偏移像素="+positionOffsetPixels);
			// 向右滑动
			if (currentPosition == position) {
				LinearLayout.LayoutParams layoutParam =(LayoutParams) iv_product_tab.getLayoutParams();
				layoutParam.leftMargin = (int) (currentPosition * tabWidth + tabWidth
						* positionOffset);
				iv_product_tab.setLayoutParams(layoutParam);
			}
			// 向左滑动
			else if (currentPosition > position) {
				LinearLayout.LayoutParams layoutParam = (LayoutParams) iv_product_tab.getLayoutParams();
				layoutParam.leftMargin = (int) (currentPosition * tabWidth - tabWidth
						* (1-positionOffset));
				iv_product_tab.setLayoutParams(layoutParam);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};
	private void reSet(){
		tv__product_real.setTextColor(getResources().getColor(R.color.black));
		tv__product_service.setTextColor(getResources().getColor(R.color.black));
		tv__product_group.setTextColor(getResources().getColor(R.color.black));
	}
	private void select(int position){
		reSet();
		switch (position) {
		case 0:
			tv__product_real.setTextColor(getResources().getColor(R.color.red));
			vp_mine_product.setCurrentItem(0);
			break;
		case 1:
			tv__product_service.setTextColor(getResources().getColor(R.color.red));
			vp_mine_product.setCurrentItem(1);
			break;
		case 2:
			tv__product_group.setTextColor(getResources().getColor(R.color.red));
			vp_mine_product.setCurrentItem(2);
			break;

		default:
			break;
		}
	}
	private LayoutParams layoutParams;
	private int screenWidth;
	private int tabWidth;
	private MyAdapter myAdapter;

}
