package com.dida.first.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015-11-16.
 */
public class DeadlineTextView extends TextView {
    public DeadlineTextView(Context context) {
        this(context, null);
    }

    public DeadlineTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0 );
    }

    public DeadlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);
    }

    private void initTypeFace(Context context) {
        AssetManager assets = context.getAssets();
        Typeface typeFace =Typeface.createFromAsset(assets,"fonts/Let_s go Digital Regular.ttf");
        this.setTypeface(typeFace);
    }
}
