package com.dida.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.InvateAdapter;
import com.dida.first.entity.PrepayBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-10.
 */
public class Publish_InvoteFriends_Activity extends BackTitleActivity implements CompoundButton.OnCheckedChangeListener, OnCheckListener<PrepayBean.ResEntity.FriendsListEntity> {

    private static final String TAG = "InvoteFriends_Activity";
    private PullToRefreshListView plv_invate_friends;
    private OnCheckListener onCheckListener;
    private CheckBox cb_invate_checkall;
    private TextView tv_invote_enter;
    private TextView tv_invote_count;
    private List<PrepayBean.ResEntity.FriendsListEntity> friendList = new ArrayList<PrepayBean.ResEntity.FriendsListEntity>();
    private InvateAdapter invateAdapter;
    private static final int INVATE_FRIENDS = 100;
    private static final String INVATE_FRIENDS_IDS = "invate_friends_ids";

    @Override
    public View setView() {
        view = View.inflate(Publish_InvoteFriends_Activity.this, R.layout.activity_invote_friends, null);
        return view;
    }

    @Override
    public void initView() {
        plv_invate_friends = (PullToRefreshListView) view.findViewById(R.id.plv_invate_friends);
        tv_invote_enter = (TextView) view.findViewById(R.id.tv_invote_enter);
        tv_invote_count = (TextView) view.findViewById(R.id.tv_invote_count);
        cb_invate_checkall = (CheckBox) view.findViewById(R.id.cb_invate_checkall);

    }

    @Override
    public void initDoNet() {
        friendList = (List<PrepayBean.ResEntity.FriendsListEntity>) getIntent().getSerializableExtra("PrepayBean");
        mLoadingAndRetryManager.showContent();
    }

    @Override
    public void initEvent() {
        cb_invate_checkall.setOnCheckedChangeListener(this);
        tv_invote_enter.setOnClickListener(this);
        invateAdapter = new InvateAdapter(friendList, this);
        invateAdapter.setOnCheckListener(this);
        plv_invate_friends.setAdapter(invateAdapter);
    }

    @Override
    public void initData() {
        setBackTitle("邀请好友");
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.tv_invote_enter:
                startForResult();
                break;
        }
    }

    private void startForResult() {
        String friends_ids = getCheckFriendIds(invateAdapter.getList());
        if (!TextUtils.isEmpty(friends_ids)) {
            Intent intent = new Intent();
            intent.putExtra(INVATE_FRIENDS_IDS, friends_ids);
            Bundle bundle = new Bundle();
            bundle.putSerializable("InvatedFriendList", (Serializable) invateAdapter.getInvatedData());
            bundle.putSerializable("AllFriendList", (Serializable) invateAdapter.getData());
            intent.putExtras(bundle);
            Log.i(TAG, "startForResult: " + invateAdapter.getInvatedData().size());
            setResult(INVATE_FRIENDS, intent);
            finish();
        } else {
            ToastUtil.showMyToast("亲，您还没邀请好友呢！");
        }

    }

    @Override
    public void setBackClick() {
        finish();
    }

    /**
     * 选择全/全不选
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //屏蔽非点击设置触发，如sheChecked(boolean);
        tv_invote_count.setText(isChecked ? invateAdapter.getCount() + "" : 0 + "");
        if (!buttonView.isPressed())
            return;
        switch (buttonView.getId()) {
            case R.id.cb_invate_checkall:
                invateAdapter.checkAll(isChecked);
                break;
            default:
                break;
        }
    }


    /**
     * 点击选择/取消，显示已经选择的人数
     *
     * @param invateFriend
     * @param isChecked
     */
    @Override
    public void onCheck(PrepayBean.ResEntity.FriendsListEntity invateFriend, boolean isChecked) {
        invateFriend.setChecked(isChecked);
        int checkedCount = invateAdapter.getCheckedCount();
        tv_invote_count.setText(checkedCount + "");

    }

    /**
     * 获取选择的好友ID字符串
     *
     * @param list
     * @return
     */
    public String getCheckFriendIds(List<PrepayBean.ResEntity.FriendsListEntity> list) {
        StringBuilder sb = new StringBuilder();
        for (PrepayBean.ResEntity.FriendsListEntity invateFriend : list) {
            if (invateFriend.isChecked()) {
                sb.append(invateFriend.getUserId() + "#");
            }
        }
        return sb.toString();

    }

}
