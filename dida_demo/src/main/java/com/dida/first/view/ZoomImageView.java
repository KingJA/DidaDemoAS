package com.dida.first.view;

import java.io.IOException;
import java.util.Date;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;

import com.dida.first.dialog.DialogSaveImg;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.ImageUtils;
import com.dida.first.dialog.DialogSaveImg.OnSaveListener;

/**
 * @author KingJA
 * @data 2015-8-24 下午2:49:03
 * @use 多点控制，缩放，平移ImageView.
 * 
 *      1.加载适配 |-1.1 监听图片加载完成 1.2注册接口1.3设置缩放比 2.手动缩放 |-2.1
 *      ScaleGestureDetector手势缩放识别 |-2.2 onScale()进行手势缩放 3.放大平移 |-3.1
 *      onTouch()捕获平移 4.双击缩放 |-4.1
 *      GestureDetector.SimpleOnGestureListener()手势识别实现类 |-4.2
 *      重写onDoubleTap()双击方法 |-4.3 实现Runnable来实现动态缩放
 */
public class ZoomImageView extends ImageView implements OnGlobalLayoutListener,
		OnScaleGestureListener, OnTouchListener, OnLongClickListener {
	private int vW;
	private int vH;
	/* 初始化缩放值 */
	private float initScale;
	/* 双击缩放值 */
	private float midScale;
	/* 最大缩放值 */
	private float maxScale;
	private boolean mOnce;
	private Matrix matrix;
	/* 捕获用户多点缩放 */
	private ScaleGestureDetector scaleGestureDetector;

	/* ============自由移动=============== */
	private int lastPointCount;
	private float lastX;
	private float lastY;
	private boolean isCheckHorizontal;
	private boolean isCheckVertical;
	private int touchSlop;
	private boolean isCanDrag;
	/* ============双击缩放=============== */
	private GestureDetector gestureDetector;
	public void keepCenter (){
			vW = getWidth();
			vH = getHeight();
			Drawable drawable = getDrawable();
			if (drawable != null) {
				int dW = drawable.getIntrinsicWidth();
				int dH = drawable.getIntrinsicHeight();
				float scale = 1.0f;
				/**
				 * 如果图片的宽度>控件的宽度,进行缩小。
				 */
				if (dW > vW && dH < vH) {
					scale = vW * 1.0f / dW;
				}
				/**
				 * 如果图片的高度>控件的高度,进行缩小。
				 */
				if (dW < vW && dH > vH) {
					scale = vH * 1.0f / dH;
				}
				/**
				 * 如果图片的宽度>控件的宽度，高度>控件的高度,取较小值进行缩放。
				 */
				if ((dW > vW && dH > vH) || (dW < vW && dH < vH)) {
					scale = Math.min(vW * 1.0f / dW, vH * 1.0f / dH);
				}
				/**
				 * 初始化各个缩放值
				 */
				initScale = scale;
				midScale = initScale * 2;
				maxScale = initScale * 4;
				/**
				 * 将图片移动到控件中间
				 */
				Log.i("将图片移动到控件中间", "将图片移动到控件中间");
				float dx = vW / 2 - dW / 2;
				float dy = vH / 2 - dH / 2;
				matrix.postTranslate(dx, dy);
				matrix.postScale(initScale, initScale, vW / 2, vH / 2);
				setImageMatrix(matrix);
		}

	}


	/* 避免缩放和长按冲突 */
	public ZoomImageView(Context context) {
		this(context, null);
	}
	public ZoomImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		matrix = new Matrix();
		super.setScaleType(ScaleType.MATRIX);
		scaleGestureDetector = new ScaleGestureDetector(context, this);
		this.setOnTouchListener(this);
		this.setOnLongClickListener(this);
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		/**
		 * 手势识别——双击
		 */
		gestureDetector = new GestureDetector(context,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onSingleTapConfirmed(MotionEvent e) {
						Log.i("ZoomImageView", "onClick");
						ActivityFactory.myZoomActivity.finish();
						return true;
					}

					@Override
					public void onLongPress(MotionEvent e) {
						super.onLongPress(e);
						DialogSaveImg textDialog = new DialogSaveImg(
								ActivityFactory.zoomActivity);
						textDialog.show();
						textDialog.setOnSaveListener(new OnSaveListener() {
							@Override
							public void OnSave() {
								try {
									ImageUtils.saveMyBitmap(
											new Date().toString(), 100,
											ZoomImageView.this);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					}

					@Override
					public boolean onDoubleTap(MotionEvent e) {
						float x = e.getX();
						float y = e.getY();
						if (getScale() < midScale) {
							post(new AutoScaleRunnable(midScale, x, y));
						} else if (getScale() < maxScale) {
							post(new AutoScaleRunnable(maxScale, x, y));
						} else {
							post(new AutoScaleRunnable(initScale, x, y));
						}
						return true;
					}
				});
	}

	class AutoScaleRunnable implements Runnable {

		private float targetScale;
		private float x;
		private float y;
		private float BIGGER = 1.07f;
		private float SMALLER = 0.93f;
		private float offset;

		public AutoScaleRunnable(float targetScale, float x, float y) {
			super();
			this.targetScale = targetScale;
			this.x = x;
			this.y = y;
			// 小于目标则进行放大
			if (getScale() < targetScale) {
				offset = BIGGER;
			}
			// 大于目标则进行缩小
			if (getScale() > targetScale) {
				offset = SMALLER;
			}
		}

		@Override
		public void run() {
			matrix.postScale(offset, offset, x, y);
			checkCenterAndBorderWhenScale();
			setImageMatrix(matrix);
			float currentScale = getScale();
			if ((offset == BIGGER && currentScale < targetScale)
					|| (offset == SMALLER && currentScale > targetScale)) {
				postDelayed(this, 10);
			} else {
				float scale = targetScale / currentScale;
				matrix.postScale(scale, scale, x, y);
				checkCenterAndBorderWhenScale();
				setImageMatrix(matrix);
			}
		}
	}

	

	/**
	 * 监听ImageView加载完成
	 */
	@Override
	public void onGlobalLayout() {
		if (!mOnce) {
			mOnce = true;
			vW = getWidth();
			vH = getHeight();
			Drawable drawable = getDrawable();
			if (drawable != null) {
				int dW = drawable.getIntrinsicWidth();
				int dH = drawable.getIntrinsicHeight();
				float scale = 1.0f;
				/**
				 * 如果图片的宽度>控件的宽度,进行缩小。
				 */
				if (dW > vW && dH < vH) {
					scale = vW * 1.0f / dW;
				}
				/**
				 * 如果图片的高度>控件的高度,进行缩小。
				 */
				if (dW < vW && dH > vH) {
					scale = vH * 1.0f / dH;
				}
				/**
				 * 如果图片的宽度>控件的宽度，高度>控件的高度,取较小值进行缩放。
				 */
				if ((dW > vW && dH > vH) || (dW < vW && dH < vH)) {
					scale = Math.min(vW * 1.0f / dW, vH * 1.0f / dH);
				}
				/**
				 * 初始化各个缩放值
				 */
				initScale = scale;
				midScale = initScale * 2;
				maxScale = initScale * 4;
				/**
				 * 将图片移动到控件中间
				 */
				Log.i("将图片移动到控件中间", "将图片移动到控件中间");
				float dx = vW / 2 - dW / 2;
				float dy = vH / 2 - dH / 2;
				matrix.postTranslate(dx, dy);
				matrix.postScale(initScale, initScale, vW / 2, vH / 2);
				setImageMatrix(matrix);
			}
		}

	}

	/**
	 * View依附在Window上时调用,进行全局监听
	 */
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		Log.i("View依附在Window上", "View依附在Window上");
		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}

	/**
	 * View在Window上时移除,取消全局监听
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getViewTreeObserver().removeGlobalOnLayoutListener(this);
	}

	/**
	 * 获取缩放后的图片宽高及上下左右距离
	 * 
	 * @return
	 */
	private RectF getMatrixRectF() {
		Matrix rMatrix = matrix;
		RectF rectF = new RectF();
		Drawable drawable = getDrawable();
		if (drawable != null) {
			rectF.set(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			rMatrix.mapRect(rectF);
		}
		return rectF;

	}

	/**
	 * 获取当前图片的缩放值
	 * 
	 * @return
	 */
	private float getScale() {
		float[] values = new float[9];
		matrix.getValues(values);
		return values[Matrix.MSCALE_X];

	}

	/**
	 * 缩放区间initScale-maxScale
	 */
	@Override
	public boolean onScale(ScaleGestureDetector detector) {

		float scale = getScale();
		float scaleFactor = detector.getScaleFactor();
		if (getDrawable() != null) {
			if ((scale < maxScale && scaleFactor > 1.0f)
					|| (scale > initScale && scaleFactor < 1.0f)) {
				if (scale * scaleFactor < initScale) {
					scaleFactor = initScale / scale;
				}
				if (scale * scaleFactor > maxScale) {
					scaleFactor = maxScale / scale;
				}
				matrix.postScale(scaleFactor, scaleFactor,
						detector.getFocusX(), detector.getFocusY());
				checkCenterAndBorderWhenScale();
				setImageMatrix(matrix);
			}
		}
		return true;
	}

	/**
	 * onScaleBegin中return true
	 */
	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {

	}

	/**
	 * onTouch中return true传给scaleGestureDetector处理
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 捕获双击触摸
		if (gestureDetector.onTouchEvent(event)) {
			return true;
		}
		// 捕获缩放触摸
		scaleGestureDetector.onTouchEvent(event);

		/**
		 * 平移触摸
		 */
		// 多点触控各个点的坐标
		float x = 0;
		float y = 0;
		// 拿到多点触控的数量
		int pointerCount = event.getPointerCount();
		for (int i = 0; i < pointerCount; i++) {
			x += event.getX(i);
			y += event.getY(i);
		}
		// 多点触控中心点的坐标
		x /= pointerCount;
		y /= pointerCount;
		if (lastPointCount != pointerCount) {
			isCanDrag = false;
			lastX = x;
			lastY = y;
		}
		lastPointCount = pointerCount;
		RectF rect = getMatrixRectF();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (rect.width() > vW || rect.height() > vH) {
				if (getParent() instanceof ViewPager) {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("ACTION_MOVE", "ACTION_MOVE");
			if (rect.width() > vW || rect.height() > vH) {
				if (getParent() instanceof ViewPager) {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			}
			float dx = x - lastX;
			float dy = y - lastY;
			if (!isCanDrag) {
				isCanDrag = isMoveAction(dx, dy);
			}
			if (isCanDrag) {
				RectF rectF = rect;

				if (getDrawable() != null) {
					isCheckHorizontal = isCheckVertical = true;
					if (rectF.width() < getWidth()) {
						isCheckHorizontal = false;
						dx = 0;
					}
					if (rectF.height() < getHeight()) {
						isCheckVertical = false;
						dy = 0;
					}
					matrix.postTranslate(dx, dy);
					checkBorderWhenTrans();
					setImageMatrix(matrix);
				}
			}

			lastX = x;
			lastY = y;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			Log.i("ACTION_UP", "ACTION_UP");
			lastPointCount = 0;
			break;
		default:
			break;
		}

		return false;
	}

	/**
	 * 保持在移动过程中不过界
	 */
	private void checkBorderWhenTrans() {
		RectF rectF = getMatrixRectF();
		float disX = 0;
		float disY = 0;
		if (rectF.left > 0 && isCheckHorizontal) {
			disX = -rectF.left;
			if (getParent() instanceof ViewPager) {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
		}
		if (rectF.top > 0 && isCheckVertical) {
			disY = -rectF.top;
		}
		if (rectF.right < vW && isCheckHorizontal) {
			disX = vW - rectF.right;
			if (getParent() instanceof ViewPager) {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
		}
		if (rectF.bottom < vH && isCheckVertical) {
			disY = vH - rectF.bottom;
		}
		matrix.postTranslate(disX, disY);
		setImageMatrix(matrix);

	}

	/**
	 * 在缩放时进行边界和位置的控制
	 */
	private void checkCenterAndBorderWhenScale() {
		RectF rect = getMatrixRectF();
		float disX = 0;
		float disY = 0;
		if (rect.width() >= vW) {
			if (rect.left > 0) {
				disX = -rect.left;
			}
			if (rect.right < vW) {
				disX = vW - rect.right;
			}
		}
		if (rect.height() >= vH) {
			if (rect.top > 0) {
				disY = -rect.top;
			}
			if (rect.bottom < vH) {
				disY = vH - rect.bottom;
			}
		}
		if (rect.width() < vW) {
			disX = vW / 2 - rect.right + rect.width() / 2;
		}
		if (rect.height() < vH) {
			disY = vH / 2 - rect.bottom + rect.height() / 2;
		}
		matrix.postTranslate(disX, disY);

	}

	/**
	 * 判断是否能触发移动
	 * 
	 * @param dx
	 * @param dy
	 * @return
	 */
	private boolean isMoveAction(float dx, float dy) {
		return Math.sqrt(dx * dx + dy * dy) > touchSlop;
	}

	/**
	 * 使缩放，长按生效
	 */
	@Override
	public boolean onLongClick(View v) {
		return true;
	}

}
