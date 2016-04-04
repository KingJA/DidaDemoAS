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
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.entity.BeanRes;
import com.dida.first.holder.GDetail_Comment_User_Holder;
import com.dida.first.holder.GDetail_Des_Holder;
import com.dida.first.holder.GDetail_Item_Holder;
import com.dida.first.holder.GDetail_User_Head_Holder;
import com.dida.first.interfaces.OnCollectListener;
import com.dida.first.interfaces.OnShareListener;
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
public class Detail_Pingou_User_Activity extends BasePingouActivity implements OnShareListener ,OnCollectListener{

    private static final String TAG = "Detail_Pingou_User_Activity";
    private FrameLayout fl_group_detail_head;
    private FrameLayout fl_group_detail_item;
    private FrameLayout fl_group_detail_des;
    private FrameLayout fl_group_detail_comment;
    private GDetail_Comment_User_Holder commentHolder;
    private GDetail_Des_Holder desHolder;
    private GDetail_Item_Holder itemHolder;
    private RelativeLayout rl_pingou_detail_team_more;
    private TextView tv_pingou_detail_team_count;
    private GDetail_User_Head_Holder titleHolder;
    private TextView tv_pingou_user_join;
    private BeanDetailPingouUser mDetailPingouUser = new BeanDetailPingouUser();
    private RelativeLayout rl_loading;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RES_OK:
                    mLoadingAndRetryManager.showContent();
                    setData(mDetailPingouUser);
                    break;
                case RES_COLLECT_OK:
                    onShowCollect(mIsCollection);
                    break;
                case RES_COLLECT_ERR:
                    mDialogProgress.dismiss();
                    break;
            }
        }
    };
    private String serviceId;
    private String serviceType;
    private LinearLayout ll_pingou_user_fav;
    private ImageView iv_pingou_user_fav;
    private TextView tv_pingou_user_fav;


    @Override
    protected View setSonView() {
        View view = UIUtils.inflate(R.layout.activity_group_detail_user);

        /**
         * 标题块
         */
        fl_group_detail_head = (FrameLayout) view.findViewById(R.id.fl_group_detail_head);
        titleHolder = new GDetail_User_Head_Holder();
        fl_group_detail_head.addView(titleHolder.getRootView());
        /**·
         * 商品列表块
         */
        fl_group_detail_item = (FrameLayout) view.findViewById(R.id.fl_group_detail_item);
        itemHolder = new GDetail_Item_Holder(this);
        fl_group_detail_item.addView(itemHolder.getRootView());
        /**
         * 团员块
         */
        rl_pingou_detail_team_more = (RelativeLayout) view.findViewById(R.id.rl_pingou_detail_team_more);
        tv_pingou_detail_team_count = (TextView) view.findViewById(R.id.tv_pingou_detail_team_count);
        /**
         * 评论块
         */
        fl_group_detail_comment = (FrameLayout) view.findViewById(R.id.fl_group_detail_comment);
        commentHolder = new GDetail_Comment_User_Holder(this);
        fl_group_detail_comment.addView(commentHolder.getRootView());
        /**
         * 团长块
         */
        fl_group_detail_des = (FrameLayout) view.findViewById(R.id.fl_group_detail_des);
        desHolder = new GDetail_Des_Holder(this);
        fl_group_detail_des.addView(desHolder.getRootView());


        return view;
    }

    @Override
    protected void initSonView() {
        tv_pingou_user_join = (TextView) view.findViewById(R.id.tv_pingou_user_join);
        ll_pingou_user_fav = (LinearLayout) view.findViewById(R.id.ll_pingou_user_fav);
        iv_pingou_user_fav = (ImageView) view.findViewById(R.id.iv_pingou_user_fav);
        tv_pingou_user_fav = (TextView) view.findViewById(R.id.tv_pingou_user_fav);
    }

    @Override
    protected void initNet() {
        mLoadingAndRetryManager.showLoading();
        serviceId = getIntent().getStringExtra("serviceId");
        serviceType = getIntent().getStringExtra("serviceType");
        ToastUtil.showMyToast("serviceId=" + serviceId);
        mDialogProgress = new DialogProgress(this);

    }

    @Override
    protected void initEvent() {
        rl_pingou_detail_team_more.setOnClickListener(this);
        tv_pingou_user_join.setOnClickListener(this);
        titleHolder.setOnShareListener(this);
        ll_pingou_user_fav.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        doNetInit(serviceId, "fb9a38d82cd3405a9b60ec54cdb5ecdf");

    }

    @Override
    protected void onSonClick(View v) {
        switch (v.getId()) {
            case R.id.rl_pingou_detail_team_more:
                toTeamActivity();
                break;
            case R.id.tv_pingou_user_join:
                onEnter();
            case R.id.ll_pingou_user_fav:
                onNetCollect(serviceId, serviceType, "fb9a38d82cd3405a9b60ec54cdb5ecdf", mIsCollection);
                break;
        }
    }


    /**
     * 访问网络-初始化页面
     *
     * @param serviceId
     * @param userId
     */
    private void doNetInit(final String serviceId, final String userId) {
        VolleyGsonRequest<BeanDetailPingouUser> initRequest = new VolleyGsonRequest<BeanDetailPingouUser>(UrlUtil.HOST + UrlUtil.PINGOU_DETAIL, BeanDetailPingouUser.class, new Response.Listener<BeanDetailPingouUser>() {
            @Override
            public void onResponse(BeanDetailPingouUser bean) {
                mDetailPingouUser = bean;
                mTeamCount = bean.getRes().getComGroupDetail().getParticipates().size();
                mIsCollection = bean.getRes().getComGroupDetail().getIsCollection();
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



    private void setData(BeanDetailPingouUser bean) {
        titleHolder.setData(bean);
        itemHolder.setData(bean);
        desHolder.setData(bean);
        commentHolder.setData(bean);
        tv_pingou_detail_team_count.setText(mTeamCount + "");
        onShowCollect(mIsCollection);
    }


    @Override
    public void onShare() {
        ToastUtil.showMyToast("分享");
    }



    private void onEnter() {
        ToastUtil.showMyToast("加入拼购");
        ActivityUtil.goActivity(this, Pingou_Show_Activity.class);
    }

    private void toTeamActivity() {
        List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> participates = mDetailPingouUser.getRes().getComGroupDetail().getParticipates();
        mBundle.putString("TYPE", "USER");
        mBundle.putSerializable("PINGOU_TEAM", (Serializable) participates);
        ActivityUtil.goActivityWithBundle(Detail_Pingou_User_Activity.this, AttentionActivity.class, mBundle);
    }


    @Override
    public void onShowCollect(int hasCollect) {
        mDialogProgress.dismiss();
        iv_pingou_user_fav.setBackgroundResource(hasCollect == 0 ? R.drawable.btn_fav_nor : R.drawable.btn_fav_sel);
        tv_pingou_user_fav.setTextColor(hasCollect == 0 ? ContextCompat.getColor(this, R.color.gray_tip) : ContextCompat.getColor(this, R.color.red));
        tv_pingou_user_fav.setText(hasCollect == 0 ? "收藏" : "已收藏");
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
}
