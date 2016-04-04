package com.dida.first.activity;

import java.util.ArrayList;
import com.dida.first.R;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.ZoomImageView;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-8-24 下午2:44:07 
 * @use			图片浏览Activity
 *
 */
public class MyZoomImageActivity extends Activity {
	private ViewPager vp_zoom;
	private TextView tv_zoom_position;
	private TextView tv_zoom_total;
	private ArrayList<String> imageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityFactory.myZoomActivity=this;
		imageList = getIntent().getStringArrayListExtra("IMAGE_LIST");
		setContentView(R.layout.activity_zoom_imageview);
		vp_zoom = (ViewPager) findViewById(R.id.vp_zoom);
		tv_zoom_position = (TextView) findViewById(R.id.tv_zoom_position);
		tv_zoom_total = (TextView) findViewById(R.id.tv_zoom_total);
		tv_zoom_position.setText(String.valueOf(1));
		tv_zoom_total.setText(String.valueOf(imageList.size()));
		vp_zoom.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tv_zoom_position.setText(String.valueOf(position+1));
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		vp_zoom.setOffscreenPageLimit(3);
		vp_zoom.setAdapter(new MyPagerAdapter(imageList));
	}
	
	class MyPagerAdapter extends PagerAdapter{
		 private ArrayList<String>list;
		private BitmapUtils bitmapUtils;
		 MyPagerAdapter(ArrayList<String >list){
			bitmapUtils = new BitmapUtils(UIUtils.getContext());
			 this.list=list;
		 }
		
		@Override
		public Object instantiateItem(final ViewGroup container, final int position) {
				
			final ZoomImageView zoomImageView = new ZoomImageView(MyZoomImageActivity.this);
				ImageLoader.getInstance().loadImage(imageList.get(position), new ImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
					}
					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
					}
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						zoomImageView.setImageBitmap(loadedImage);  
						zoomImageView.keepCenter();
					}
					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						
					}});  
				
				container.addView(zoomImageView);
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
			return imageList.size();
		}
	}
}
