package com.dida.first.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.dida.first.R;

/**
 * Created by KingJA on 2016-1-25.
 */
public class KingJA_EditText extends EditText {
    public KingJA_EditText(Context context) {
        this(context,null);
    }

    public KingJA_EditText(Context context, AttributeSet attrs) {
        this(context, attrs,R.style.KingJaEditText);
    }

    public KingJA_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.style.KingJaEditText);


    }
}
