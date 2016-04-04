/**
 * 
 */
package com.dida.first.activity;

import com.dida.first.R;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.view.ZoomImageView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-8-24 下午2:44:07 
 * @use			图片浏览Activity
 *
 */
public class ZoomImageActivity extends Activity {
	private ViewPager vp_zoom;
	private int[] iconArr={R.drawable.zoom1,R.drawable.zoom2,R.drawable.zoom3,R.drawable.zoom4};
	private ImageView[] imageArr=new ImageView[iconArr.length];
	private TextView tv_zoom_position;
	private TextView tv_zoom_total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityFactory.zoomActivity=this;
		setContentView(R.layout.activity_zoom_imageview);
		vp_zoom = (ViewPager) findViewById(R.id.vp_zoom);
		tv_zoom_position = (TextView) findViewById(R.id.tv_zoom_position);
		tv_zoom_total = (TextView) findViewById(R.id.tv_zoom_total);
		tv_zoom_position.setText(1+"");
		tv_zoom_total.setText(iconArr.length+"");
		vp_zoom.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tv_zoom_position.setText(position+1+"");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		vp_zoom.setAdapter(new PagerAdapter() {
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ZoomImageView zoomImageView = new ZoomImageView(getApplicationContext());
				zoomImageView.setImageResource(iconArr[position]);
				container.addView(zoomImageView);
				imageArr[position]=zoomImageView;
				return zoomImageView;
			}
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View)object);
			}
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return iconArr.length;
			}
		});
	}
}
