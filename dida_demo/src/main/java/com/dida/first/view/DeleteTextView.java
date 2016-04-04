package com.dida.first.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by KingJA on 2015-12-18.
 */
public class DeleteTextView extends TextView {
    public DeleteTextView(Context context) {
        this(context,null);
    }

    public DeleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DeleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getPaint().setAntiAlias(true);
        this.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }
}
