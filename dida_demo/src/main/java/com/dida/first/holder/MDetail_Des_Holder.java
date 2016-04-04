package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.ImgAdapter;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-15 下午5:09:33
 * @use 集市图文详情
 * 
 */
public class MDetail_Des_Holder extends BaseHolder<BeanDetailMarket> {

	private TextView tv_market_detail_des_content;
	private MyListView mlv_market_detail_des;
	private Activity mActivity;

	public MDetail_Des_Holder(Activity activity) {
		this.mActivity = activity;
	}

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.market_detail_des);
		tv_market_detail_des_content = (TextView) view.findViewById(R.id.tv_market_detail_des_content);
		mlv_market_detail_des = (MyListView) view.findViewById(R.id.mlv_market_detail_des);
		return view;
	}

	@Override
	public void refreshView() {
		String des = getData().getRes().getTimeOrPhyProduct().getMobileDes();
		List<String> imgList = getData().getRes().getImageJson();
		tv_market_detail_des_content.setText(des);
		if (imgList.size()>0){
			ImgAdapter imgAdapter = new ImgAdapter(imgList, mActivity);
			mlv_market_detail_des.setAdapter(imgAdapter);
		}
	}

}
