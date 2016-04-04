package com.dida.first.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.dida.first.R;

/***
 * 在Android系统中，一个视图（View）从创建到显示过程中的主要方法
 *  1.构造方法 
 *  2.测量-onMeasure(int,int);当前View是ViewGroup,还有义务测量孩子的宽和高 
 *  3.指定位置和大小-onLayout(boolean,int,int,int,int)当前View是ViewGroup，
 *  必须要指定孩子的位置和大小 
 *  4.绘制-onDraw(canvas);
 */
public class MarketSwitchButton extends View implements View.OnClickListener {
	private OnSwitchListener onSwitchListener;
	private Bitmap rightBitmap;
	private Bitmap leftBitmap;
	private Bitmap currentBitmap;
	private Paint paint;
	private boolean isLeft=true;

	public MarketSwitchButton(Context context) {
		this(context, null);
	}

	public MarketSwitchButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MarketSwitchButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		paint = new Paint();
		paint.setAntiAlias(true);

		rightBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg_btn_service);
		leftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_btn_real);
		setOnClickListener(this);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isLeft) {
			currentBitmap = leftBitmap;
		} else {
			currentBitmap = rightBitmap;
		}

		canvas.drawBitmap(currentBitmap, 0, 0, paint);

	}

	public interface OnSwitchListener {
		public abstract void onSwitch(boolean isLeft);

	}

	public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
		this.onSwitchListener = onSwitchListener;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(rightBitmap.getWidth(), rightBitmap.getHeight());
	}

	@Override
	public void onClick(View v) {
		isLeft = !isLeft;
		if (isLeft) {
			// 关》开

			currentBitmap = leftBitmap;
			if (onSwitchListener != null) {
				onSwitchListener.onSwitch(isLeft);
			}
		} else {
			// 开》关
			currentBitmap = rightBitmap;
			if (onSwitchListener != null) {
				onSwitchListener.onSwitch(isLeft);
			}
		}
		invalidate();

	}

	public void setOpend(boolean opend) {
		if (isLeft != opend) {
			isLeft = opend;
			invalidate();
		}

	}

	public boolean isOpened() {
		return isLeft;
	}

}

