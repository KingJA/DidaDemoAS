/**
 *
 */
package com.dida.first.holder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.activity.ZoomImageActivity;
import com.dida.first.adapter.ImgAdapter;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;
import com.dida.first.view.AbsListView.MyListView;
import com.dida.first.view.KingJA_GradeView;
import com.meg7.widget.CircleImageView;

/**
 * @author KingJA
 * @data 2015-8-18 下午4:52:24
 * @use
 */
public class GDetail_Des_Holder extends BaseHolder<BeanDetailPingouUser> implements OnItemClickListener, View.OnClickListener {

    private MyListView mlv_group_detail_des;
    private TextView tv_pingou_detail_des_content;
    private TextView tv_pingou_detail_des_leader;
    private CircleImageView civ_pingou_detail_des_icon;
    private KingJA_GradeView kgv_pingou_detail_des_grade;
    protected Activity activity;

    public GDetail_Des_Holder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.group_detail_des);
        mlv_group_detail_des = (MyListView) view.findViewById(R.id.mlv_group_detail_des);
        tv_pingou_detail_des_content = (TextView) view.findViewById(R.id.tv_pingou_detail_des_content);
        tv_pingou_detail_des_leader = (TextView) view.findViewById(R.id.tv_pingou_detail_des_leader);
        civ_pingou_detail_des_icon = (CircleImageView) view.findViewById(R.id.civ_pingou_detail_des_icon);
        kgv_pingou_detail_des_grade = (KingJA_GradeView) view.findViewById(R.id.kgv_pingou_detail_des_grade);

        return view;
    }

    @Override
    public void refreshView() {
        BeanDetailPingouUser data = getData();
        UImageLoaderUitl.displaySmallImage(data.getRes().getThumb(),civ_pingou_detail_des_icon);
        tv_pingou_detail_des_leader.setText(data.getRes().getUserName());
        tv_pingou_detail_des_content.setText(data.getRes().getPureDes());
        kgv_pingou_detail_des_grade.setGrade(data.getRes().getUserCredits());
        ImgAdapter imgAdapter = new ImgAdapter(data.getRes().getImageJson(), activity);
        imgAdapter.setImgSize(UIUtils.getScreenWidth(),UIUtils.getScreenWidth());
        mlv_group_detail_des.setAdapter(imgAdapter);
        mlv_group_detail_des.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.civ_pingou_detail_des_icon:
//                ActivityUtil.goActivity(activity, Personal_Detail_Activity.class);
//                break;
        }

    }

    class DesIconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = UIUtils.inflate(R.layout.item_group_detail_des);
            return itemView;
        }

    }

    /**
     * 打开图片浏览ZoomImageActivity
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(ActivityFactory.mainActivity, ZoomImageActivity.class);
        ActivityFactory.mainActivity.startActivity(intent);

    }

}
