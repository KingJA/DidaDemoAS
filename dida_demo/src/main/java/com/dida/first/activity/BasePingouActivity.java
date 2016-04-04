package com.dida.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dida.first.R;
import com.dida.first.utils.ToastUtil;

/**
 * Created by KingJA on 2016-1-5.
 */
public abstract class BasePingouActivity extends BaseNomalActivity {
    protected Bundle mBundle=new Bundle();
    protected int mTeamCount;

    private View rootView;

    @Override
    protected void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pingou_detail_back:
                finish();
                break;
            case R.id.iv_pingou_detail_chat:
                ToastUtil.showMyToast("聊天界面");
                break;
            default:
                break;
        }
        onSonClick(v);

    }


    @Override
    protected View setView() {
        rootView = View.inflate(this, R.layout.top_pingou_detail, null);
        return rootView;
    }

    @Override
    protected void initView() {
        ImageView iv_pingou_detail_back = (ImageView) rootView.findViewById(R.id.iv_pingou_detail_back);
        ImageView iv_pingou_detail_chat = (ImageView) rootView.findViewById(R.id.iv_pingou_detail_chat);
        FrameLayout fl_pingou_detail_content = (FrameLayout) rootView.findViewById(R.id.fl_pingou_detail_content);
        iv_pingou_detail_back.setOnClickListener(this);
        iv_pingou_detail_chat.setOnClickListener(this);
        fl_pingou_detail_content.addView(setSonView());
        initSonView();
    }

    protected abstract View setSonView();

    protected abstract void initSonView();

    @Override
    protected abstract void initNet();

    @Override
    protected abstract void initEvent();

    @Override
    protected abstract void initData();

    protected abstract void onSonClick(View v);
}
