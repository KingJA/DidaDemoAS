package com.dida.first.view.AbsListView;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.GridViewWithHeaderAndFooter;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义下拉刷新，上拉加载
 * @author Administrator
 */
public class PullPushGridView extends GridViewWithHeaderAndFooter implements OnScrollListener,
		OnTouchListener {
	private View footView;
	/* 最后一个可见Item */
	private int lastVisibleItem;
	/* 总Item数 */
	private int totalItemCount;
	/* 第一个可见Item */
	private int firstVisibleItem;
	private OnFrushLoadMore onFrushLoadMore;
	private OnBackTop onBackTop;
	private LinearLayout ll_loadMore;
	private boolean isLoading;
	private boolean isFrush;
	private View headView;
	private RelativeLayout rl_frush;
	private int startY;
	private int moveY;
	private int frushHeight;
	private int paddingHeight;
	private ProgressBar pd_frush;
	private ImageView iv_frush;
	private int distanceY;
	private TextView tv_frush;
	private LinearLayout ll_lunbotu_head;
	private LinearLayout ll_noMore;
	
	/**
	 * 实例用的构造函数
	 */
	public PullPushGridView(Context context) {
		this(context,null);
	}

	/**
	 * 通过XML创建的构造函数
	 */
	public PullPushGridView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	/**
	 * 通过XML创建的构造函数且有Sytle样式
	 */
	public PullPushGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHead(context);
		initFoot(context);
		this.setOnScrollListener(this);
		this.setOnTouchListener(this);
	}

	/**
	 * 初始化刷新布局
	 */
	public void initHead(Context context) {
		headView = View.inflate(context, R.layout.head_listview, null);
		ll_lunbotu_head = (LinearLayout) headView
				.findViewById(R.id.ll_lunbotu_head);
		tv_frush = (TextView) headView.findViewById(R.id.tv_frush);
		rl_frush = (RelativeLayout) headView.findViewById(R.id.rl_frush);
		pd_frush = (ProgressBar) headView.findViewById(R.id.pd_frush);
		iv_frush = (ImageView) headView.findViewById(R.id.iv_frush);
		/**
		 * 测量并获取刷新头高度
		 */
		rl_frush.measure(0, 0);
		frushHeight = rl_frush.getMeasuredHeight();
		rl_frush.setPadding(0, -frushHeight, 0, 0);
		pd_frush.setVisibility(View.GONE);
		iv_frush.setVisibility(View.VISIBLE);
		this.addHeaderView(headView);
	}

	/**
	 * 初始化底部加载布局
	 */
	private void initFoot(Context context) {
		footView = View.inflate(context, R.layout.foot_listview, null);
		ll_loadMore = (LinearLayout) footView.findViewById(R.id.ll_loadMore);
		ll_noMore = (LinearLayout) footView.findViewById(R.id.ll_noMore);
		setLoadViewGone();
		this.addFooterView(footView);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		/**
		 * 当ListView滚动到底部，判断依据(lastVisibleItem == totalItemCount)
		 * 停止滚动SCROLL_STATE_IDLE
		 */
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (lastVisibleItem == totalItemCount) {
				// 21.加载更多
				if (!isLoading) {
					// 弹出加载View
					setLoadViewVisibility();
					// 执行加载逻辑
					if (onFrushLoadMore!=null) {
						onFrushLoadMore.onLoadMore();
					}
				}
			}
			// 2.返回顶部
			if (onBackTop != null) {
				if (firstVisibleItem > 5) {
					// 返回顶部按钮显示
					onBackTop.onTopVisible();
				} else {
					// 返回顶部按钮消失
					onBackTop.onTopGone();
				}
			}

		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
		this.totalItemCount = totalItemCount;
		lastVisibleItem = firstVisibleItem + visibleItemCount;

	}

	/***
	 * 设置加载布局可见
	 */
	public void setLoadViewVisibility() {
		isLoading = true;
		ll_loadMore.setVisibility(View.VISIBLE);

	}

	/***
	 * 设置加载布局不可见
	 */
	public void setLoadViewGone() {
		isLoading = false;
		ll_loadMore.setVisibility(View.GONE);

	}

	/***
	 * 设置刷新布局可见
	 */
	public void setFrushViewVisibility() {
		isFrush = true;
		pd_frush.setVisibility(View.VISIBLE);
		iv_frush.setVisibility(View.GONE);

	}

	/***
	 * 设置刷新布局不可见
	 */
	public void setFrushViewGone() {
		isFrush = false;
		pd_frush.setVisibility(View.GONE);
		iv_frush.setVisibility(View.VISIBLE);
		tv_frush.setText("刷新好啦^-^");
		ValueAnimator heightAnimator = ValueAnimator.ofInt(0, -frushHeight);
		heightAnimator.setDuration(400);
		heightAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer animatedValue = (Integer) animation.getAnimatedValue();
				rl_frush.setPadding(0, animatedValue, 0, 0);
			}
		});
		heightAnimator.start();

	}
/**
 * 
 * @author		KingJA 
 * @data		2015-8-17 上午10:39:50 
 * @use			刷新加载接口
 *
 */
	public interface OnFrushLoadMore {
		public void onFrush();

		public void onLoadMore();
	}

	public void setOnFrushLoadMore(OnFrushLoadMore onFrushLoadMore) {
		this.onFrushLoadMore = onFrushLoadMore;
	}
/**
 * 
 * @author		KingJA 
 * @data		2015-8-17 上午10:40:27 
 * @use			返回顶部接口
 *
 */
	public interface OnBackTop {
		public void onTopVisible();
		public void onTopGone();
	}

	public void setOnBackTop(OnBackTop onBackTop) {
		this.onBackTop = onBackTop;
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("ACTION_DOWN", "ACTION_DOWN");
			distanceY = 0;
			startY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			moveY = (int) event.getY();
			distanceY = (int) ((moveY - startY) * 0.4);
			if (distanceY < frushHeight) {
				tv_frush.setText("下拉刷新~");
			} else if (distanceY > frushHeight + 30) {
				tv_frush.setText("释放立即刷新~");
			}

			paddingHeight = (int) (-frushHeight + distanceY);
			// 满足1.滑到顶部2.向下滑动3.没在刷新触发下拉刷新头
			if (distanceY > 0 && firstVisibleItem == 0 && !isFrush) {
				rl_frush.setPadding(0, paddingHeight, 0, 0);
			}
			Log.i("ACTION_MOVE", "distanceY=" + distanceY);
			break;
		case MotionEvent.ACTION_UP:
			// 如果下拉高度<刷新头高度自动弹出不刷新
			if (distanceY > 0 && distanceY < frushHeight
					&& firstVisibleItem == 0 && !isFrush) {
				valueAnimator(paddingHeight, -frushHeight);
				break;
			}
			// 如果下拉高度>刷新头高度则进行刷新
			if (distanceY > frushHeight && firstVisibleItem == 0 && !isFrush) {
				tv_frush.setText("正在刷新...");
				valueAnimator(paddingHeight, 0);
				break;
			}
			break;
		}
		return false;
	}

	private void valueAnimator(int from, int to) {
		ValueAnimator heightAnimator = ValueAnimator.ofInt(from, to);
		heightAnimator.setDuration(200);
		heightAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer animatedValue = (Integer) animation.getAnimatedValue();
				rl_frush.setPadding(0, animatedValue, 0, 0);
				// 刷新逻辑
				if (animatedValue == 0) {
					if (!isFrush && onFrushLoadMore != null) {
						setFrushViewVisibility();
						if (UIUtils.getNetworkType() == 0) {
							ToastUtil.showMyToast(
									"网络不给力啊");
							setFrushViewGone();
							return;
						}
						onFrushLoadMore.onFrush();
					}
				}
			}
		});
		heightAnimator.start();
	}

	/**
	 * 显示没有更多内容
	 */
	public void setNoMoreVisibility() {
		ll_loadMore.setVisibility(View.GONE);
		isLoading = true;
		ll_noMore.setVisibility(View.VISIBLE);

	}

	/**
	 * 显示没有更多内容
	 */
	public void setNoMoreGone() {
		setLoadViewGone();
		ll_noMore.setVisibility(View.GONE);

	}

}
