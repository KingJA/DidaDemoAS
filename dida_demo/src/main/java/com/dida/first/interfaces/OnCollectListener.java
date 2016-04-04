package com.dida.first.interfaces;

/**
 * Created by Administrator on 2015-11-5.
 */
public interface OnCollectListener {
    void onShowCollect(int hasCollect);
    void onNetCollect(final String serviceId, final String serviceType, final String userId, final int isCollection);
}
