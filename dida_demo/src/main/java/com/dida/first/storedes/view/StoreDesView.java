package com.dida.first.storedes.view;

import com.dida.first.entity.BeanSimple;
import com.dida.first.entity.BeanStoreDes;
import com.dida.first.interfaces.CommonView;

/**
 * Created by KingJA on 2016-1-19.
 */
public interface StoreDesView extends CommonView<BeanStoreDes> {
    void showCollectedState(BeanSimple data);
    void showCollectErr();
    void showSmallProgress();
    void hideSmallProgress();
}
