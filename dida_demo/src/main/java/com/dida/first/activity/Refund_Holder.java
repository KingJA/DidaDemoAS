/**
 * 
 */
package com.dida.first.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanRefund;
import com.dida.first.holder.BaseHolder;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-9-29 下午3:58:44 
 * @use			
 *
 */
public class Refund_Holder extends BaseHolder<BeanRefund> implements OnClickListener{
	private Activity activity;
	public Refund_Holder(Activity activity) {
		super();
		this.activity=activity;
	}
	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.item_mine_refund);
		return view;
	}

	@Override
	public void refreshView() {
		ImageView iv_mine_refund = (ImageView) view.findViewById(R.id.iv_mine_refund);
		
		TextView tv_mine_refund_store = (TextView) view.findViewById(R.id.tv_mine_refund_store);
		TextView tv_mine_refund_deal = (TextView) view.findViewById(R.id.tv_mine_refund_deal);
		TextView tv_mine_refund_param = (TextView) view.findViewById(R.id.tv_mine_refund_param);
		TextView tv_mine_refund_paymoney = (TextView) view.findViewById(R.id.tv_mine_refund_paymoney);
		TextView tv_mine_refund_refundmoney = (TextView) view.findViewById(R.id.tv_mine_refund_refundmoney);
		
		RelativeLayout rl_mine_refund_store = (RelativeLayout) view.findViewById(R.id.rl_mine_refund_store);
		RelativeLayout rl_mine_refund_detail = (RelativeLayout) view.findViewById(R.id.rl_mine_refund_detail);
		rl_mine_refund_store.setOnClickListener(this);
		rl_mine_refund_detail.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.rl_mine_refund_store:
		ToastUtil.showMyToast("进入店铺");
		break;
	case R.id.rl_mine_refund_detail:
		ToastUtil.showMyToast("进入退款流程页");
		goActivity(Detail_Refund_Activity.class);
		break;

	default:
		break;
	}
		
	}
	private void goActivity(Class clazz) {
		Intent intent = new Intent(activity, clazz);
		activity.startActivity(intent);
	}
}
