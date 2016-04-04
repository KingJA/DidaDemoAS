package com.dida.first.interfaces;

import java.util.List;

/**
 * Created by Administrator on 2015-11-5.
 */
public interface OnShowItemListener<T> {
    String onShowItem(List<T> mData, int position);
}
