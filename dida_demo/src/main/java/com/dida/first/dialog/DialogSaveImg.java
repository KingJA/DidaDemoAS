/**
 *
 */
package com.dida.first.dialog;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.dida.first.R;

/**
 * @author KingJA
 * @data 2015-8-26 下午2:53:07
 * @use
 */
public class DialogSaveImg extends DialogBaseAlert {

    private RelativeLayout rl_save_image;
    private OnSaveListener onSaveListener;

    /**
     * @param context
     */
    public DialogSaveImg(Context context) {
        super(context);
    }


    @Override
    public void initView() {
        setContentView(R.layout.dialog_text);
        rl_save_image = (RelativeLayout) findViewById(R.id.rl_save_image);
    }

    @Override
    public void initNet() {

    }

    @Override
    public void initEvent() {
        rl_save_image.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void childClick(View v) {
        if (v.getId() == R.id.rl_save_image) {
            onSaveListener.OnSave();
            dismiss();
        }
    }

    public interface OnSaveListener {
        void OnSave();
    }

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }
}
