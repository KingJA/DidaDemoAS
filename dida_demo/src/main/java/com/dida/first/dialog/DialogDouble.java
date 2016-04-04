package com.dida.first.dialog;


import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;

public class DialogDouble extends DialogBaseAlert {
    private Context context;
    private String message;
    private TextView tv_doubledialog_message;
    private RelativeLayout rl_doubledialog_left;
    private RelativeLayout rl_doubledialog_right;
    private TextView tv_doubledialog_left;
    private TextView tv_doubledialog_right;
    private String title;
    private String leftString;
    private String rightString;
    private OnDoubleClickListener onDoubleClickListener;

    public DialogDouble(Context context, String message, String leftString, String rightString) {
        super(context);
        this.context = context;
        this.message = message;
        this.leftString = leftString;
        this.rightString = rightString;
        this.show();
    }

    @Override
    public void initView() {
        setContentView(R.layout.dialog_double);
        tv_doubledialog_message = (TextView) findViewById(R.id.tv_doubledialog_message);
        tv_doubledialog_left = (TextView) findViewById(R.id.tv_doubledialog_left);
        tv_doubledialog_right = (TextView) findViewById(R.id.tv_doubledialog_right);
        rl_doubledialog_left = (RelativeLayout) findViewById(R.id.rl_doubledialog_left);
        rl_doubledialog_right = (RelativeLayout) findViewById(R.id.rl_doubledialog_right);


    }

    @Override
    public void initNet() {

    }

    @Override
    public void initEvent() {
        rl_doubledialog_left.setOnClickListener(this);
        rl_doubledialog_right.setOnClickListener(this);

    }

    @Override
    public void initData() {
        tv_doubledialog_message.setText(message);
        tv_doubledialog_left.setText(leftString);
        tv_doubledialog_right.setText(rightString);

    }


    @Override
    public void childClick(View v) {
        switch (v.getId()) {
            case R.id.rl_doubledialog_left:
                dismiss();
                onDoubleClickListener.onLeft();
                break;
            case R.id.rl_doubledialog_right:
                dismiss();
                onDoubleClickListener.onRight();
                break;
        }
    }

    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.onDoubleClickListener = onDoubleClickListener;
    }

    public interface OnDoubleClickListener {
        public void onLeft();

        public void onRight();
    }

}
