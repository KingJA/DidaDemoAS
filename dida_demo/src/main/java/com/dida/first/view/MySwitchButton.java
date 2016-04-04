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
public class MySwitchButton extends View implements View.OnClickListener {
	private OnSwitchListener onSwitchListener;
	private Bitmap offBitmap;
	private Bitmap onBitmap;
	private Bitmap currentBitmap;
	private Paint paint;
	private boolean isOpend;

	public MySwitchButton(Context context) {
		this(context, null);
	}

	public MySwitchButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MySwitchButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		paint = new Paint();
		paint.setAntiAlias(true);

		offBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.off_button);
		onBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.on_button);
		setOnClickListener(this);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isOpend) {
			currentBitmap = onBitmap;
		} else {
			currentBitmap = offBitmap;
		}

		canvas.drawBitmap(currentBitmap, 0, 0, paint);

	}

	public interface OnSwitchListener {
		public abstract void onSwitch(boolean isOpen);

	}

	public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
		this.onSwitchListener = onSwitchListener;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(offBitmap.getWidth(), offBitmap.getHeight());
	}

	@Override
	public void onClick(View v) {
		isOpend = !isOpend;
		if (isOpend) {
			// 关》开

			currentBitmap = onBitmap;
			if (onSwitchListener != null) {
				onSwitchListener.onSwitch(isOpend);
			}
		} else {
			// 开》关
			currentBitmap = offBitmap;
			if (onSwitchListener != null) {
				onSwitchListener.onSwitch(isOpend);
			}
		}
		invalidate();

	}

	public void setOpend(boolean opend) {
		if (isOpend != opend) {
			isOpend = opend;
			invalidate();
		}

	}

	public boolean isOpened() {
		return isOpend;
	}

}

