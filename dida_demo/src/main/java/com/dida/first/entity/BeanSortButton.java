package com.dida.first.entity;

import com.dida.first.view.MyGridViewTextView;

/**
 * Created by Administrator on 2015-11-3.
 */
public class BeanSortButton {
    private MyGridViewTextView textView;
    private boolean isChecked;
    private int index;

    public MyGridViewTextView getTextView() {
        return textView;
    }

    public void setTextView(MyGridViewTextView textView) {
        this.textView = textView;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
