package com.dida.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.adapter.PublishAdapter;
import com.dida.first.dialog.DialogDouble;
import com.dida.first.entity.PrepayBean;
import com.dida.first.entity.YaoYueBean;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;
import com.dida.first.view.AbsListView.MyListView;
import com.dida.first.wheelview.ChangeDeadlineDialog;
import com.meg7.widget.CircleImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-11-5.
 */
public class Publish_Activity extends BackTitleActivity {
    private static final String TAG = "Publish_Activity";
    private List<YaoYueBean.Res> list = new ArrayList<YaoYueBean.Res>();
    private List<PrepayBean.ResEntity.PrepayOrderListEntity> prepayOrderList = new ArrayList<PrepayBean.ResEntity.PrepayOrderListEntity>();
    private PrepayBean prepayBean = new PrepayBean();
    private RelativeLayout rl_publish_invote_friends;
    private EditText et_publish_edit_title;
    private EditText et_publish_edit_content;
    private ScrollView sv_publish_root;
    private LinearLayout ll_invate_frineds_list;
    private LinearLayout ll_invate_frineds_root;
    private LinearLayout ll_invate_frineds_icons;
    private TextView tv_publish_edit_deadline;
    private TextView tv_publish_count;
    private TextView tv_invated_count;
    private RelativeLayout rl_publish_edit_deadline;
    private SeekBar sb_publish_count;
    private Button btn_publish;
    private MyListView mylv_publish_products;
    private int mCount;
    private String title;
    private boolean mOnce;
    private boolean lintenAble;
    private static final int INVATE_FRIENDS = 100;
    private static final String INVATE_FRIENDS_IDS = "invate_friends_ids";
    private static final int MAX_ICON_COUNT = 8;
    private static final int ICON_MARGIN_DP = 8;
    private int totleFriends;
    private PublishAdapter publishAdapter;
    private String prepayId;
    private Bundle bundle = new Bundle();
    private List<PrepayBean.ResEntity.FriendsListEntity> friendsList = new ArrayList<PrepayBean.ResEntity.FriendsListEntity>();
    private List<PrepayBean.ResEntity.FriendsListEntity> invateFriendsList = new ArrayList<PrepayBean.ResEntity.FriendsListEntity>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //关闭刷新
            switch (msg.what) {
                case RES_REFRESH:
                    //显示内容
                    mLoadingAndRetryManager.showContent();
                    publishAdapter.setData(prepayOrderList);
                    break;
                case RES_ERROR:
                    ToastUtil.showMyToast("服务器被都敏俊拐走了！");
                    mLoadingAndRetryManager.showRetry();
                    break;
            }
        }
    };


    @Override
    public View setView() {
        view = View.inflate(this, R.layout.activity_publish, null);
        return view;
    }


    @Override
    public void initView() {
        ll_invate_frineds_list = (LinearLayout) view.findViewById(R.id.ll_invate_frineds_list);
        ll_invate_frineds_root = (LinearLayout) view.findViewById(R.id.ll_invate_frineds_root);
        ll_invate_frineds_icons = (LinearLayout) view.findViewById(R.id.ll_invate_frineds_icons);
        btn_publish = (Button) view.findViewById(R.id.btn_publish);
        rl_publish_invote_friends = (RelativeLayout) view.findViewById(R.id.rl_publish_invote_friends);
        sv_publish_root = (ScrollView) view.findViewById(R.id.sv_publish_root);
        et_publish_edit_title = (EditText) view.findViewById(R.id.et_publish_edit_title);
        et_publish_edit_content = (EditText) view.findViewById(R.id.et_publish_edit_content);
        tv_publish_edit_deadline = (TextView) view.findViewById(R.id.tv_publish_edit_deadline);
        tv_publish_count = (TextView) view.findViewById(R.id.tv_publish_count);
        tv_invated_count = (TextView) view.findViewById(R.id.tv_invated_count);
        sb_publish_count = (SeekBar) view.findViewById(R.id.sb_publish_count);
        mylv_publish_products = (MyListView) view.findViewById(R.id.mylv_publish_products);
        rl_publish_edit_deadline = (RelativeLayout) view.findViewById(R.id.rl_publish_edit_deadline);

    }

    @Override
    public void initDoNet() {
        Bundle bundle = getIntent().getExtras();
        prepayId = bundle.getString("prepayId");
        Log.i(TAG, "prepayId: " + prepayId);
        mLoadingAndRetryManager.showLoading();
    }


    @Override
    public void initEvent() {
        rl_publish_edit_deadline.setOnClickListener(this);
        sb_publish_count.setMax(120);
        sb_publish_count.setOnSeekBarChangeListener(onSeekBarChangeListener);
        rl_publish_invote_friends.setOnClickListener(this);
        btn_publish.setOnClickListener(this);
        ll_invate_frineds_list.setOnClickListener(this);
        publishAdapter = new PublishAdapter(prepayOrderList, this);
        mylv_publish_products.setAdapter(publishAdapter);
    }


    /**
     * 设置标题和右边按钮
     */
    @Override
    public void initData() {
        setBackTitle("团购拼购");
        setChatClickVisibility();
        refresh(prepayId, RES_REFRESH);
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            /**
             * 弹出截止日期
             */
            case R.id.rl_publish_edit_deadline:

                deadLine();
                break;
            /**
             * 发布
             */
            case R.id.btn_publish:
                publish();
                break;
            /**
             * 查看所有好友
             */
            case R.id.ll_invate_frineds_list:
                //TODO
                break;
            /**
             * 打开邀请好友界面
             */
            case R.id.rl_publish_invote_friends:
                bundle.putSerializable("PrepayBean", (Serializable) friendsList);
                ActivityUtil.goActivityForResultWithBundle(Publish_Activity.this, Publish_InvoteFriends_Activity.class, bundle, INVATE_FRIENDS);
                break;

        }
    }

    /**
     * 发布
     */
    private void publish() {
        if (checkContent()) {
            ToastUtil.showMyToast("发布成功！");
        }
    }

    /**
     * 检查发布的内容
     *
     * @return
     */
    private boolean checkContent() {
        //标题
        String title = et_publish_edit_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showMyToast("亲，请输入标题");
            return false;
        }
        //点评
        String content = et_publish_edit_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showMyToast("亲，请输入点评内容");
            return false;
        }
        //图片
        //TODO
        //截止日期
        String deadDate = tv_publish_edit_deadline.getText().toString().trim();
        if (TextUtils.isEmpty(deadDate)) {
            ToastUtil.showMyToast("亲，请设定截止日止");
            return false;
        }
        //份数
        if (mCount == 0) {
            ToastUtil.showMyToast("亲，请设定份数");
            return false;
        }
        //好友
        //TODO
        return true;
    }

    @Override
    public void setBackClick() {
        finish();
    }

    @Override
    public void setMyRetryEvent(View retryView) {

    }

    @Override
    public void onBackPressed() {
        DialogDouble dialogDouble = new DialogDouble(Publish_Activity.this, "您确定要退出发布?", "退出", "取消");
        dialogDouble.setOnDoubleClickListener(new DialogDouble.OnDoubleClickListener() {
            @Override
            public void onLeft() {
                Publish_Activity.this.finish();
            }

            @Override
            public void onRight() {

            }
        });
    }

    /**
     * 选择截止日期
     */
    private void deadLine() {
        ChangeDeadlineDialog mChangeDeadlineDialog = new ChangeDeadlineDialog(
                Publish_Activity.this);
        mChangeDeadlineDialog.show();
        mChangeDeadlineDialog.setBirthdayListener(new ChangeDeadlineDialog.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                String deadLine = year + "-" + month + "-" + day;
                tv_publish_edit_deadline.setText(deadLine);
            }
        });


    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress > 1) {
                mCount = progress;
                tv_publish_count.setText(progress + "");
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 接受邀请好友IDS字符串
         */
        if (requestCode == INVATE_FRIENDS) {
            if (data != null) {
                friendsList = (List<PrepayBean.ResEntity.FriendsListEntity>) data.getSerializableExtra("AllFriendList");
                invateFriendsList = (List<PrepayBean.ResEntity.FriendsListEntity>) data.getSerializableExtra("InvatedFriendList");
                Log.i(TAG, "onActivityResult: " + friendsList.size());
                showFriendIcon(invateFriendsList);
            }
        }
    }

    /**
     * 显示邀请的好友头像
     */
    private void showFriendIcon(List<PrepayBean.ResEntity.FriendsListEntity> invatedList) {
        tv_invated_count.setText(invatedList.size() + "");
        ll_invate_frineds_root.setVisibility(View.VISIBLE);
        ll_invate_frineds_icons.removeAllViews();
        //屏幕宽度
        int screenWidth = UIUtils.getScreenWidth();
        //1/8屏幕宽度
        int imgWidth = (screenWidth - UIUtils.dip2px(ICON_MARGIN_DP) * (MAX_ICON_COUNT + 1)) / MAX_ICON_COUNT;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgWidth, imgWidth);
        layoutParams.setMargins(0, 0, UIUtils.dip2px(ICON_MARGIN_DP), 0);
        for (int i = 0; i < (invatedList.size() > 8 ? 8 : invatedList.size()); i++) {
            CircleImageView icon = (CircleImageView) View.inflate(Publish_Activity.this, R.layout.view_invate_friends_icon, null);
            UImageLoaderUitl.displaySmallImage(invatedList.get(i).getThumb(), icon);
            ll_invate_frineds_icons.addView(icon, layoutParams);
            Log.i(TAG, "addView i: " + i);
        }
    }

    /**
     * 刷洗·加载 网络操作
     *
     * @param prepayIds
     * @param requestCode
     */
    private void refresh(final String prepayIds, final int requestCode) {
        VolleyGsonRequest<PrepayBean> pingouRequest = new VolleyGsonRequest<PrepayBean>(UrlUtil.HOST + UrlUtil.SHOW_EDIT, PrepayBean.class, new Response.Listener<PrepayBean>() {
            @Override
            public void onResponse(PrepayBean bean) {
                prepayBean = bean;
                prepayOrderList = bean.getRes().getPrepayOrderList();
                friendsList = bean.getRes().getFriendsList();
                Log.i(TAG, "onResponse: " + prepayOrderList.size());
                mHandler.sendEmptyMessage(requestCode);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERROR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("prepayIds", prepayIds);
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(pingouRequest);
    }
}
