/**
 *
 */
package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.ImgAdapter;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyGridView;
import com.meg7.widget.CircleImageView;

import java.util.List;


/**
 * @author KingJA
 * @data 2015-9-18 下午1:24:00
 * @use
 */
public class MDetail_Comment_Holder extends BaseHolder<BeanDetailMarket> implements OnClickListener {


    private TextView tv_comment_market_name;
    private TextView tv_comment_market_content;
    private TextView tv_comment_market_after;
    private TextView tv_comment_market_date;
    private TextView tv_comment_market_param;
    private TextView tv_market_detail_comment_count;

    private CircleImageView civ_comment_market_icon;
    private MyGridView mgv_comment_market_image;
    private MyGridView mgv_comment_market_afterimage;

    private RelativeLayout rl_market_detail_comment_more;
    private LinearLayout ll_item_market_commentContent;
    private Activity activity;

    public MDetail_Comment_Holder(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.market_detail_comment);
        init();
        return view;
    }

    @Override
    public void refreshView() {
        int evaluationCount = getData().getRes().getEvaluationCount();
        List<BeanDetailMarket.ResEntity.FeedBackVOsEntity> feedBackVOs = getData().getRes().getFeedBackVOs();

        tv_comment_market_after.setVisibility(View.GONE);
        tv_market_detail_comment_count.setText(evaluationCount==0?"暂无评价":"评价("+evaluationCount+")");
        ll_item_market_commentContent.setVisibility(evaluationCount== 0 ? View.GONE : View.VISIBLE);
        if (feedBackVOs.size() > 0) {
            BeanDetailMarket.ResEntity.FeedBackVOsEntity feedBackVOsEntity = feedBackVOs.get(0);
            tv_comment_market_name.setText(feedBackVOsEntity.getUserName());
            tv_comment_market_content.setText(feedBackVOsEntity.getDes());
            tv_comment_market_date.setText(feedBackVOsEntity.getCreateTime());
            tv_comment_market_param.setText(feedBackVOsEntity.getOrderAttrValues());
            ImgAdapter commentImgAdapter = new ImgAdapter(feedBackVOsEntity.getImageUrlList(), activity);
//            commentImgAdapter.setImgSize((UIUtils.getScreenWidth() - UIUtils.dip2px(8) * 6) / 5,(UIUtils.getScreenWidth() - UIUtils.dip2px(8) * 6) / 5);
            mgv_comment_market_image.setAdapter(commentImgAdapter);
            if (feedBackVOsEntity.getAfterComment().size() > 0) {
                tv_comment_market_after.setVisibility(View.VISIBLE);
                String afterString="[当天追加]: ";
                int addDays = feedBackVOsEntity.getAfterComment().get(0).getAddDays();
               if (addDays>0){
                   afterString="["+addDays+"天后追加]: ";
               }
                tv_comment_market_after.setText(afterString+feedBackVOsEntity.getAfterComment().get(0).getDes());
                ImgAdapter addImgAdapter = new ImgAdapter(feedBackVOsEntity.getImageUrlList(), activity);
//                addImgAdapter.setImgSize((UIUtils.getScreenWidth() - UIUtils.dip2px(8) * 6) / 5,(UIUtils.getScreenWidth() - UIUtils.dip2px(8) * 6) / 5);
                mgv_comment_market_afterimage.setAdapter(addImgAdapter);
            }
        }
    }

    private void init() {
        tv_comment_market_name = (TextView) view.findViewById(R.id.tv_comment_market_name);
        tv_comment_market_content = (TextView) view.findViewById(R.id.tv_comment_market_content);
        tv_comment_market_after = (TextView) view.findViewById(R.id.tv_comment_market_after);
        tv_comment_market_date = (TextView) view.findViewById(R.id.tv_comment_market_date);
        tv_comment_market_param = (TextView) view.findViewById(R.id.tv_comment_market_param);
        tv_market_detail_comment_count = (TextView) view.findViewById(R.id.tv_market_detail_comment_count);
        civ_comment_market_icon = (CircleImageView) view.findViewById(R.id.civ_comment_market_icon);
        mgv_comment_market_image = (MyGridView) view.findViewById(R.id.mgv_comment_market_image);
        mgv_comment_market_afterimage = (MyGridView) view.findViewById(R.id.mgv_comment_market_afterimage);
        rl_market_detail_comment_more = (RelativeLayout) view.findViewById(R.id.rl_market_detail_comment_more);
        ll_item_market_commentContent = (LinearLayout) view.findViewById(R.id.ll_item_market_commentContent);
        rl_market_detail_comment_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_item_market_comment:
                ToastUtil.showMyToast("打开评价列表");
                break;

            default:
                break;
        }

    }

}
