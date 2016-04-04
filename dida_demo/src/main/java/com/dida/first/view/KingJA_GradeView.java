package com.dida.first.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.dida.first.R;

/**
 * Created by KingJA on 2015-12-31.
 */
public class KingJA_GradeView extends View {

    private Bitmap mRedBitmap;
    private Bitmap mYellowBitmap;
    private Bitmap mCapBitmap;
    private Bitmap mCurrentBitmap;
    private int mGrade=11;
    private Paint mPaint;

    public KingJA_GradeView(Context context) {
        this(context,null);
    }

    public KingJA_GradeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KingJA_GradeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b_rate_red);
        mYellowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b_rate_yellow);
        mCapBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b_rate_cap);

        mCurrentBitmap=mYellowBitmap;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurrentBitmap.getWidth()*5, mCurrentBitmap.getHeight());
    }
    private void setData(int grade){
       setBitmap(grade/10);
        mGrade=grade%10;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mGrade; i++) {
            canvas.drawBitmap(mCurrentBitmap,i*mCurrentBitmap.getWidth(),0,mPaint);
        }
    }

    /**
     * 根据等级确定图标1  红心  2  黄砖  3  皇冠
     * @param index
     */
    private void setBitmap (int index){
        switch (index){
            case 1:
                mCurrentBitmap=mRedBitmap;
                break;
            case 2:
                mCurrentBitmap=mYellowBitmap;
                break;
            case 3:
                mCurrentBitmap=mCapBitmap;
                break;

        }
    }

    /**
     * 根据积分确定等级
     * @param credits
     */
    public  void setGrade(double credits){
        if(credits <= 10){
            setData(11);
        }else if(credits >=11 && credits <= 40){
            setData(12);
        }else if(credits >=41 && credits <= 90){
            setData(13);
        }else if(credits >=91 && credits <= 150){
            setData(14);
        }else if(credits >=151 && credits <= 250){
            setData(15);
        }else if(credits >=251 && credits <= 500){
            setData(21);
        }else if(credits >=501 && credits <= 1000){
            setData(22);
        }else if(credits >=1001 && credits <= 2000){
            setData(23);
        }else if(credits >=2001 && credits <= 5000){
            setData(24);
        }else if(credits >=5001 && credits <= 10000){
            setData(25);
        }else if(credits >=10001 && credits <= 20000){
            setData(31);
        }else if(credits >=20001 && credits <= 50000){
            setData(32);
        }else if(credits >=50001 && credits <= 100000){
            setData(33);
        }else if(credits >=100001 && credits <= 200000){
            setData(34);
        }else if(credits >=200001/* && credits <= 500000*/){
            setData(35);
        }else{
            setData(11);
        }
    }

}
