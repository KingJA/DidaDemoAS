package com.dida.first.holder;

import android.view.View;
import android.widget.EditText;

import com.dida.first.R;
import com.dida.first.interfaces.OnCheckPublishEditNullListener;
import com.dida.first.utils.UIUtils;

/**
 * Created by Administrator on 2015-11-5.
 */
public class Publish_Edit_Holder extends  BaseHolder{
    private OnCheckPublishEditNullListener onCheckPublishEditNullListener;

    private String title;

    @Override
    public View initView() {
        view= UIUtils.inflate(R.layout.holder_publish_edit);
        return view;
    }

    @Override
    public void refreshView() {
        EditText et_publish_edit_title = (EditText) view.findViewById(R.id.et_publish_edit_title);
        title = et_publish_edit_title.getText().toString().trim();
        onCheckPublishEditNullListener.onCheckPublishEditNull(title);

    }
    public void setOnCheckPublishEditNullListener(OnCheckPublishEditNullListener onCheckPublishEditNullListener){
        this.onCheckPublishEditNullListener=onCheckPublishEditNullListener;
    }
}
