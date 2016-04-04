/**
 *
 */
package com.dida.first.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingouStore;
import com.dida.first.interfaces.OnShareListener;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.NoTitleMoveImgView;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-17 下午4:19:59
 * @use
 */
public class GDetail_Store_Head_Holder extends BaseHolder<BeanDetailPingouStore>  implements View.OnClickListener{

    private Context context;
    private OnShareListener onShareListener;
    private TextView tv_pingou_store_detail_head_day;
    private TextView tv_pingou_store_detail_head_hour;
    private TextView tv_pingou_store_detail_head_minute;
    private TextView tv_pingou_store_detail_head_price;
    private TextView tv_pingou_store_detail_head_oldPrice;
    private TextView tv_pingou_store_detail_head_title;
    private TextView tv_pingou_store_detail_head_subject;
    private TextView tv_pingou_store_detail_head_left;
    private LinearLayout ll_pingou_store_detail_share;
    private LinearLayout ll_pingou_detail_store_deadlime;
    private FrameLayout fl_pingou_detail_store_imgs;
    private ImageView iv_pingou_store_detail_head_price;

    public GDetail_Store_Head_Holder(Context context) {
        this.context = context;
    }

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.pingou_detail_store_head);
        fl_pingou_detail_store_imgs = (FrameLayout) view.findViewById(R.id.fl_pingou_detail_store_imgs);
        tv_pingou_store_detail_head_subject = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_subject);
        tv_pingou_store_detail_head_left = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_left);
        tv_pingou_store_detail_head_day = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_day);
        tv_pingou_store_detail_head_hour = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_hour);
        tv_pingou_store_detail_head_minute = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_minute);
        tv_pingou_store_detail_head_price = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_price);
        tv_pingou_store_detail_head_oldPrice = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_oldPrice);
        tv_pingou_store_detail_head_title = (TextView) view.findViewById(R.id.tv_pingou_store_detail_head_title);
        ll_pingou_store_detail_share = (LinearLayout) view.findViewById(R.id.ll_pingou_store_detail_share);
        iv_pingou_store_detail_head_price = (ImageView) view.findViewById(R.id.iv_pingou_store_detail_head_price);
        ll_pingou_detail_store_deadlime = (LinearLayout) view.findViewById(R.id.ll_pingou_detail_store_deadlime);
        return view;
    }

    @Override
    public void refreshView() {

        BeanDetailPingouStore data = getData();

        List<String> productImgs = data.getRes().getProductImgs();
        addLVHead(productImgs);
        BeanDetailPingouStore.ResEntity.ComGroupDetailEntity comGroupDetail = data.getRes().getComGroupDetail();
        int[] deadTime = TimeUtils.getDeadTime(comGroupDetail.getCustomDueDate());

        int groupMode = comGroupDetail.getGroupMode();

        tv_pingou_store_detail_head_price.setText("¥ "+comGroupDetail.getPrice());
        tv_pingou_store_detail_head_oldPrice.setText("¥ "+comGroupDetail.getOldPrice());
        StringUtil.setRichText(tv_pingou_store_detail_head_title,R.drawable.bg_store,comGroupDetail.getGroupName());
        tv_pingou_store_detail_head_day.setText(deadTime[0]+"");
        tv_pingou_store_detail_head_hour.setText(deadTime[1]+"");
        tv_pingou_store_detail_head_minute.setText(deadTime[2]+"");
        iv_pingou_store_detail_head_price.setBackgroundResource(groupMode==1?R.drawable.tuangoujia:R.drawable.qianggoujia);

        tv_pingou_store_detail_head_subject.setText(comGroupDetail.getCount()+"");
        tv_pingou_store_detail_head_left.setText(comGroupDetail.getCount()-comGroupDetail.getBuyCount()+"");
        ll_pingou_detail_store_deadlime.setVisibility(groupMode==1?View.VISIBLE:View.GONE);
        ll_pingou_store_detail_share.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_pingou_store_detail_share:
                if (onShareListener !=null){
                    onShareListener.onShare();
                }
            break;
        }
    }
    public void setOnShareListener(OnShareListener onShareListener){
        this.onShareListener = onShareListener;

    }

    /**
     * ListView加入轮播图Head
     *
     * @param adsList
     */
    private void addLVHead(List<String> adsList) {


        NoTitleMoveImgView<String> adsLunBoTu=new NoTitleMoveImgView<String>(context) {
            @Override
            protected void onItemClick(List<String> mAdsList, int position) {
                ToastUtil.showMyToast("position ="+position);
            }

            @Override
            protected String setImgUrl(List<String> mAdsList, int position) {
                return mAdsList.get(position);
            }
        };
        adsLunBoTu.show(adsList);
        adsLunBoTu.setViewHeight(UIUtils.getScreenWidth());
        fl_pingou_detail_store_imgs.addView(adsLunBoTu);
    }
}
