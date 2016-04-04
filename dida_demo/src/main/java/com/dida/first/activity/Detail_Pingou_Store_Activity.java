/**
 *
 */
package com.dida.first.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dida.first.R;
import com.dida.first.dialog.DialogProgress;
import com.dida.first.entity.BeanDetailPingouStore;
import com.dida.first.entity.BeanRes;
import com.dida.first.holder.GDetail_Comment_Store_Holder;
import com.dida.first.holder.GDetail_Des_Holder;
import com.dida.first.holder.GDetail_Item_Holder;
import com.dida.first.holder.GDetail_Store_Head_Holder;
import com.dida.first.interfaces.OnCollectListener;
import com.dida.first.interfaces.OnShareListener;
import com.dida.first.popupwindow.PopupWindowParamStore;
import com.dida.first.popupwindow.PopupWindowSelectStore;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;
import com.dida.first.utils.VolleyGsonRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KingJA
 * @data 2015-8-17 下午1:15:10
 * @use
 */
public class Detail_Pingou_Store_Activity extends BasePingouActivity implements OnShareListener, OnCollectListener {
    private static final int RES_OK = 1;
    private static final int RES_ERR = -1;
    private static final int RES_COLLECT_OK = 2;
    private static final String TAG = "Detail_Pingou_User_Activity";
    private static final int RES_COLLECT_ERR = -2;
    private FrameLayout fl_group_detail_head;
    private FrameLayout fl_group_detail_item;
    private FrameLayout fl_group_detail_des;
    private FrameLayout fl_group_detail_comment;
    private GDetail_Comment_Store_Holder commentHolder;
    private GDetail_Des_Holder desHolder;
    private GDetail_Item_Holder itemHolder;
    private RelativeLayout rl_pingou_detail_team_more;
    private RelativeLayout rl_pingou_store_detail_param;
    private RelativeLayout rl_market_pingou_store_detail_select;
    private TextView tv_pingou_detail_team_count;
    private GDetail_Store_Head_Holder headHolder;
    private TextView tv_pingou_store_join;
    private TextView tv_pingou_store_fav;
    private ImageView iv_pingou_store_fav;
    private LinearLayout ll_pingou_store_fav;
    private LinearLayout ll_pingou_store_store;
    private LinearLayout ll_pingou_store_service;
    private LinearLayout ll_parent_store_detail;
    private BeanDetailPingouStore mDetailPingou = new BeanDetailPingouStore();
    private String serviceId;
    private String serviceType;
    private PopupWindowSelectStore selectPopupWindowStore;
    private PopupWindowParamStore paramPopupWindowStore;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RES_OK:
                    mLoadingAndRetryManager.showContent();
                    initPopupWindow();
                    setData(mDetailPingou);
                case RES_COLLECT_OK:
                    onShowCollect(mIsCollection);
                    break;
                case RES_COLLECT_ERR:
                    mDialogProgress.dismiss();
                    break;
            }
        }
    };
    private List<BeanDetailPingouStore.ResEntity.ComGroupDetailEntity.ParticipatesEntity> participates;


    @Override
    protected void onSonClick(View v) {
        switch (v.getId()) {

            case R.id.rl_pingou_store_detail_param:
                ToastUtil.showMyToast("查看参数");
                paramPopupWindowStore.showPopupWindow();
                break;
            case R.id.rl_market_pingou_store_detail_select:
                ToastUtil.showMyToast("选择规格");
                selectPopupWindowStore.showPopupWindow();
                break;
            case R.id.rl_pingou_detail_team_more:
                ToastUtil.showMyToast("团员列表");
                if (mTeamCount==0){
                    ToastUtil.showMyToast("赶紧邀请好友加入吧");
                }else{
                    mBundle.putString("TYPE", "STORE");
                    mBundle.putSerializable("PINGOU_TEAM", (Serializable) participates);
                    ActivityUtil.goActivityWithBundle(Detail_Pingou_Store_Activity.this, AttentionActivity.class, mBundle);
                }
                break;
            case R.id.ll_pingou_store_fav:
                ToastUtil.showMyToast("添加/删除收藏");
                onNetCollect(serviceId,serviceType, "fb9a38d82cd3405a9b60ec54cdb5ecdf", mIsCollection);
                break;
            case R.id.ll_pingou_store_store:
                ToastUtil.showMyToast("进入店铺");
                enterStore();
                break;
            case R.id.ll_pingou_store_service:
                ToastUtil.showMyToast("客服");
                enterService();
                break;
            case R.id.tv_pingou_store_join:
                ToastUtil.showMyToast("立即加入");
                onJoin();
                break;
        }
    }


    @Override
    protected View setSonView() {
        View view = UIUtils.inflate(R.layout.activity_group_detail_store);
        /**
         * 标题块
         */
        fl_group_detail_head = (FrameLayout) view.findViewById(R.id.fl_group_detail_head);
        headHolder = new GDetail_Store_Head_Holder(this);
        fl_group_detail_head.addView(headHolder.getRootView());
        /**
         * 团员块
         */
        rl_pingou_detail_team_more = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_team_more);
        tv_pingou_detail_team_count = (TextView) view.findViewById(R.id.tv_pingou_detail_team_count);
        /**
         * 评论块
         */
        fl_group_detail_comment = (FrameLayout) view.findViewById(R.id.fl_group_detail_comment);
        commentHolder = new GDetail_Comment_Store_Holder(this);
        fl_group_detail_comment.addView(commentHolder.getRootView());
        return view;
    }

    @Override
    protected void initSonView() {
        tv_pingou_store_join = (TextView) view.findViewById(R.id.tv_pingou_store_join);
        tv_pingou_store_fav = (TextView) view.findViewById(R.id.tv_pingou_store_fav);
        iv_pingou_store_fav = (ImageView) view.findViewById(R.id.iv_pingou_store_fav);
        tv_pingou_store_join = (TextView) view.findViewById(R.id.tv_pingou_store_join);
        ll_pingou_store_fav = (LinearLayout) view.findViewById(R.id.ll_pingou_store_fav);
        ll_pingou_store_store = (LinearLayout) view.findViewById(R.id.ll_pingou_store_store);
        ll_pingou_store_service = (LinearLayout) view.findViewById(R.id.ll_pingou_store_service);
        ll_parent_store_detail = (LinearLayout) view.findViewById(R.id.ll_parent_store_detail);
        rl_pingou_store_detail_param = (RelativeLayout) view.findViewById(R.id.rl_pingou_store_detail_param);
        rl_market_pingou_store_detail_select = (RelativeLayout) view.findViewById(R.id.rl_market_pingou_store_detail_select);
    }

    @Override
    protected void initEvent() {
        rl_pingou_detail_team_more.setOnClickListener(this);
        tv_pingou_store_join.setOnClickListener(this);
        ll_pingou_store_fav.setOnClickListener(this);
        ll_pingou_store_store.setOnClickListener(this);
        ll_pingou_store_service.setOnClickListener(this);
        rl_pingou_store_detail_param.setOnClickListener(this);
        rl_market_pingou_store_detail_select.setOnClickListener(this);
        headHolder.setOnShareListener(this);
    }

    @Override
    protected void initNet() {
        mLoadingAndRetryManager.showLoading();
        serviceId = getIntent().getStringExtra("serviceId");
        serviceType = getIntent().getStringExtra("serviceType");
        mDialogProgress = new DialogProgress(this);
    }

    @Override
    protected void initData() {
        doNetInit(serviceId, "fb9a38d82cd3405a9b60ec54cdb5ecdf");

    }

    private void initPopupWindow() {
        selectPopupWindowStore = new PopupWindowSelectStore(
                ll_parent_store_detail, Detail_Pingou_Store_Activity.this, mDetailPingou.getRes().getPurchaseAttrs());
        paramPopupWindowStore = new PopupWindowParamStore(
                ll_parent_store_detail, Detail_Pingou_Store_Activity.this, mDetailPingou.getRes().getCustomAttrs());
    }

    /**
     * 访问网络-初始化页面
     *
     * @param serviceId
     * @param userId
     */
    private void doNetInit(final String serviceId, final String userId) {
        VolleyGsonRequest<BeanDetailPingouStore> initRequest = new VolleyGsonRequest<BeanDetailPingouStore>(UrlUtil.HOST + UrlUtil.PINGOU_DETAIL, BeanDetailPingouStore.class, new Response.Listener<BeanDetailPingouStore>() {
            @Override
            public void onResponse(BeanDetailPingouStore bean) {
                mDetailPingou = bean;
                mTeamCount = bean.getRes().getComGroupDetail().getParticipates().size();
                mIsCollection = bean.getRes().getComGroupDetail().getIsCollection();
                participates = bean.getRes().getComGroupDetail().getParticipates();
                mHandler.sendEmptyMessage(RES_OK);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(RES_ERR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("serviceId", serviceId);
                map.put("userId", userId);
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(initRequest);
    }

    private void setData(BeanDetailPingouStore bean) {
        headHolder.setData(bean);
        tv_pingou_detail_team_count.setText(mTeamCount + "");
        commentHolder.setData(bean);
    }

    @Override
    public void onShare() {
        ToastUtil.showMyToast("分享");
    }

    @Override
    public void onNetCollect(final String serviceId, final String serviceType, final String userId, final int isCollection) {
        mDialogProgress.show();
        VolleyGsonRequest<BeanRes> collectRequest = new VolleyGsonRequest<BeanRes>(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_PINGOU_ADD_CANCLE_COLLECT), BeanRes.class, new Response.Listener<BeanRes>() {
            @Override
            public void onResponse(BeanRes res) {
                mIsCollection = res.getRes();
                mHandler.sendEmptyMessage(RES_COLLECT_OK);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                mHandler.sendEmptyMessage(RES_COLLECT_ERR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("serviceId", serviceId);
                map.put("serviceType", serviceType);
                map.put("userId", userId);
                map.put("isCollection", isCollection + "");
                map.put("app", "1");
                return map;
            }
        };
        mQueue.add(collectRequest);
    }
    @Override
    public void onShowCollect(int hasCollect) {
        mDialogProgress.dismiss();
        iv_pingou_store_fav.setBackgroundResource(hasCollect == 0 ? R.drawable.btn_fav_nor : R.drawable.btn_fav_sel);
        tv_pingou_store_fav.setTextColor(hasCollect == 0 ? ContextCompat.getColor(this, R.color.gray_tip) : ContextCompat.getColor(this, R.color.red));
        tv_pingou_store_fav.setText(hasCollect == 0 ? "收藏" : "已收藏");
    }


    private void enterService() {

    }

    private void enterStore() {

    }

    private void onJoin() {
        ActivityUtil.goActivity(this, Pingou_Show_Activity.class);
    }

}
