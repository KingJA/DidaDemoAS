package com.dida.first.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dida.first.R;
import com.dida.first.dialog.DialogDouble;
import com.dida.first.dialog.DialogDouble.OnDoubleClickListener;
import com.dida.first.fragment.Index_Market_Fragment;
import com.dida.first.fragment.Index_Mine_Fragment;
import com.dida.first.fragment.Index_Pingou_Fragment;
import com.dida.first.fragment.Index_Show_Fragment;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 入口Activity，，负责四个标签页的切换及网络状态的广播
 *
 * @author KingJA
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    public FragmentManager mFragmentManager;
    private LinearLayout ll_tab_pingou;
    private LinearLayout ll_tab_market;
    private LinearLayout ll_tab_show;
    private LinearLayout ll_tab_mine;
    private ImageView iv_tab_pingou;
    private ImageView iv_tab_market;
    private ImageView iv_tab_mine;
    private ImageView iv_tab_show;
    private TextView tv_tab_pingou;
    private TextView tv_tab_market;
    private TextView tv_tab_mine;
    private TextView tv_tab_show;
    private Index_Pingou_Fragment mPingouFragment;
    private Index_Market_Fragment mMarketFragment;
    private Index_Show_Fragment mShowFragment;
    private Index_Mine_Fragment mMineFragment;
    private FragmentTransaction transaction;
    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.fragment_main);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mCurrentIndex= savedInstanceState.getInt("currentIndex");
            Log.i(TAG, "savedInstanceState: "+mCurrentIndex);
            mPingouFragment = (Index_Pingou_Fragment) mFragmentManager.findFragmentByTag("PINGOU");
            mMarketFragment = (Index_Market_Fragment) mFragmentManager.findFragmentByTag("MARKET");
            mShowFragment = (Index_Show_Fragment) mFragmentManager.findFragmentByTag("SHOW");
            mMineFragment = (Index_Mine_Fragment) mFragmentManager.findFragmentByTag("MINE");
        }
        registerDateTransReceiver();
        initView();
        initEvent();
        initData();
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.i(TAG, "onCreate: " + heapSize);


    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                Log.i(TAG, "onTrimMemory: ");
                break;
        }
    }

    /**
     * 查找控件
     */
    private void initView() {
        ll_tab_pingou = (LinearLayout) findViewById(R.id.ll_tab_pingou);
        ll_tab_market = (LinearLayout) findViewById(R.id.ll_tab_market);
        ll_tab_show = (LinearLayout) findViewById(R.id.ll_tab_show);
        ll_tab_mine = (LinearLayout) findViewById(R.id.ll_tab_mine);
        iv_tab_pingou = (ImageView) findViewById(R.id.iv_tab_pingou);
        iv_tab_market = (ImageView) findViewById(R.id.iv_tab_market);
        iv_tab_show = (ImageView) findViewById(R.id.iv_tab_show);
        iv_tab_mine = (ImageView) findViewById(R.id.iv_tab_mine);
        tv_tab_pingou = (TextView) findViewById(R.id.tv_tab_pingou);
        tv_tab_market = (TextView) findViewById(R.id.tv_tab_market);
        tv_tab_show = (TextView) findViewById(R.id.tv_tab_show);
        tv_tab_mine = (TextView) findViewById(R.id.tv_tab_mine);
    }

    /**
     * 添加事件
     */
    private void initEvent() {
        ll_tab_pingou.setOnClickListener(this);
        ll_tab_market.setOnClickListener(this);
        ll_tab_show.setOnClickListener(this);
        ll_tab_mine.setOnClickListener(this);
    }

    /**
     * 初始化布局
     */
    public void initData() {
        setTab(mCurrentIndex);
    }


    private void initConnection() {
        String token = SharedPreferencesUtils.getStringData("TOKEN", "");
        if (TextUtils.isEmpty(token)) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    ToastUtil.showMyToast("onSuccess==" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }

    }

    private long firstTime;

    @Override
    public void onBackPressed() {

        long secondTime = System.currentTimeMillis();
        if ((secondTime - firstTime) > 2000) {
            ToastUtil.singleToast(MainActivity.this, "再按一次退出程序");
            firstTime = secondTime;
        } else {
            finish();
        }
    }

    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private NetChanged netChanged;

    private void registerDateTransReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CONNECTIVITY_CHANGE_ACTION);
        filter.setPriority(1000);
        netChanged = new NetChanged();
        registerReceiver(netChanged, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab_pingou:
                setTab(0);
                break;
            case R.id.ll_tab_market:
                setTab(1);
                break;
            case R.id.ll_tab_show:
                setTab(2);
                break;
            case R.id.ll_tab_mine:
                setTab(3);
                break;

        }
    }

    /**
     * 选择对应的Fragment：0拼购    1集市 2晒单 3我的
     */
    // TODO 需要重构
    private void setTab(int position) {
        transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        // clearPopuWindow();
        resetState();
        mCurrentIndex=position;
        switch (position) {
            case 0:
                iv_tab_pingou.setBackgroundResource(R.drawable.pingou_sel);
                tv_tab_pingou.setTextColor(getResources().getColor(
                        R.color.red));
                if (mPingouFragment == null) {
                    Log.i(TAG, "mPingouFragment == null");
                    mPingouFragment = new Index_Pingou_Fragment();
                    transaction.add(R.id.fl_main_content, mPingouFragment,"PINGOU");
                } else {
                    Log.i(TAG, "show(mPingouFragment)");
                    transaction.show(mPingouFragment);
                }
                break;
            case 1:
                iv_tab_market.setBackgroundResource(R.drawable.market_sel);
                tv_tab_market.setTextColor(getResources().getColor(
                        R.color.red));
                if (mMarketFragment == null) {
                    Log.i(TAG, "mMarketFragment == null");
                    mMarketFragment = new Index_Market_Fragment();
                    transaction.add(R.id.fl_main_content, mMarketFragment,"MARKET");
                } else {
                    Log.i(TAG, "show(mMarketFragment)");
                    transaction.show(mMarketFragment);
                }
                break;
            case 2:
                iv_tab_show.setBackgroundResource(R.drawable.show_sel);
                tv_tab_show.setTextColor(getResources().getColor(
                        R.color.red));
                if (mShowFragment == null) {
                    mShowFragment = new Index_Show_Fragment();
                    transaction.add(R.id.fl_main_content, mShowFragment,"SHOW");
                } else {
                    transaction.show(mShowFragment);
                }
                break;
            case 3:
                iv_tab_mine.setBackgroundResource(R.drawable.mine_sel);
                tv_tab_mine.setTextColor(getResources().getColor(
                        R.color.red));
                if (mMineFragment == null) {
                    mMineFragment = new Index_Mine_Fragment();
                    transaction.add(R.id.fl_main_content, mMineFragment,"MINE");
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 复位TAB状态
     */
    private void resetState() {
        iv_tab_pingou.setBackgroundResource(R.drawable.pingou_nor);
        iv_tab_market.setBackgroundResource(R.drawable.market_nor);
        iv_tab_show.setBackgroundResource(R.drawable.show_nor);
        iv_tab_mine.setBackgroundResource(R.drawable.mine_nor);
        tv_tab_pingou.setTextColor(getResources().getColor(
                R.color.gray_content));
        tv_tab_market.setTextColor(getResources().getColor(
                R.color.gray_content));
        tv_tab_show.setTextColor(getResources().getColor(
                R.color.gray_content));
        tv_tab_mine.setTextColor(getResources()
                .getColor(R.color.gray_content));

    }

    /**
     * 隐藏所有Fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mPingouFragment != null) {
            transaction.hide(mPingouFragment);
        }
        if (mMarketFragment != null) {
            transaction.hide(mMarketFragment);
        }
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    /**
     * @author KingJA
     * @data 2015-6-26 下午3:07:38
     * @use 动态注册的监听网络状态变化的广播
     */
    class NetChanged extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (UIUtils.getNetworkType()) {
                case 0:
                    Toast.makeText(context, "没有网络:", Toast.LENGTH_SHORT).show();
                    /**
                     * 弹出网络设置对话框
                     */
                    setNetwork(MainActivity.this);
                    break;
                case 1:
                    Toast.makeText(context, "当前网络:WIFI", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(context, "当前网络:WAP网络", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(context, "当前网络:NET网络", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        /**
         * 获取当前网络类型
         *
         * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
         */

        private AlertDialog dialog;


        /**
         * 网络未连接时，调用设置方法
         */
        private void setNetwork(Context context) {
            final DialogDouble setNetDialog = new DialogDouble(
                    MainActivity.this, "网络不可用，请先设置网络！", "确定", "取消");
            setNetDialog.show();
            setNetDialog.setOnDoubleClickListener(new OnDoubleClickListener() {

                @Override
                public void onLeft() {

                    Intent intent = null;
                    /**
                     * 判断手机系统的版本！如果API大于10 就是3.0+
                     * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                     */
                    if (android.os.Build.VERSION.SDK_INT > 10) {
                        intent = new Intent(
                                android.provider.Settings.ACTION_WIFI_SETTINGS);
                    } else {
                        intent = new Intent();
                        ComponentName component = new ComponentName(
                                "com.android.settings",
                                "com.android.settings.WirelessSettings");
                        intent.setComponent(component);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    startActivity(intent);

                }

                @Override
                public void onRight() {

                }
            });

        }
    }

    /**
     * 解绑动态注册的广播
     */
    @Override
    protected void onDestroy() {

        Log.i(TAG, "onDestroy: ");
        unregisterReceiver(netChanged);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: "+mCurrentIndex);
        //取消保存状态，防止Fragment重叠
        outState.putInt("currentIndex",mCurrentIndex);
        super.onSaveInstanceState(outState);
    }
}
