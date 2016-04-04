/**
 * 
 */
package com.dida.first.holder;

import com.dida.first.R;
import com.dida.first.entity.ShopCarOutterBean.ShopCarInnerBean;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author		KingJA 
 * @data		2015-9-1 下午2:06:15 
 * @use			待购订单内层Holder，负责增加，减少，修改分类参数及删除内层Item。
 *
 */
public class ShopCarInnerHolder extends BaseHolder<ShopCarInnerBean> implements OnClickListener{

	private LinearLayout ll_shopcar_edit_press;
	private RelativeLayout rl_shopcar_edit_normal;
	private TextView tv_shopcar_inner_title;
	private TextView tv_shopcar_inner_param;
	private TextView tv_shopcar_inner_price;
	private TextView tv_shopcar_inner_oldprice;
	private TextView tv_shopcar_inner_count;
	private TextView tv_shopcar_inner_num;
	private TextView tv_shopcar_inner_param_press;
	private TextView tv_shopcar_inner_delete;
	private ImageView iv_shopcar_inner_reduce;
	private ImageView iv_shopcar_inner_add;
	private LinearLayout ll_shopcar_inner_downopen;
	private int count;
	private int positionOut;
	
	public ShopCarInnerHolder(int positionOut) {
		this.positionOut=positionOut;
	}
	@Override
	public View initView() {
		View inflate = UIUtils.inflate(R.layout.item_shopcar_inner);
		ll_shopcar_inner_downopen = (LinearLayout) inflate.findViewById(R.id.ll_shopcar_inner_downopen);
		iv_shopcar_inner_add = (ImageView) inflate.findViewById(R.id.iv_shopcar_inner_add);
		iv_shopcar_inner_reduce = (ImageView) inflate.findViewById(R.id.iv_shopcar_inner_reduce);
		tv_shopcar_inner_delete = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_delete);
		tv_shopcar_inner_param_press = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_param_press);
		tv_shopcar_inner_num = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_num);
		iv_shopcar_inner_reduce.setOnClickListener(this);
		iv_shopcar_inner_add.setOnClickListener(this);
		tv_shopcar_inner_delete.setOnClickListener(this);
		ll_shopcar_inner_downopen.setOnClickListener(this);
		
		tv_shopcar_inner_title = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_title);
		tv_shopcar_inner_param = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_param);
		tv_shopcar_inner_price = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_price);
		tv_shopcar_inner_oldprice = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_oldprice);
		tv_shopcar_inner_count = (TextView) inflate.findViewById(R.id.tv_shopcar_inner_count);
		ll_shopcar_edit_press = (LinearLayout) inflate.findViewById(R.id.ll_shopcar_edit_press);
		rl_shopcar_edit_normal = (RelativeLayout) inflate.findViewById(R.id.rl_shopcar_edit_normal);
		return inflate;
	}

	@Override
	public void refreshView() {
		ShopCarInnerBean innerBean = getData();
		count = innerBean.count;
		tv_shopcar_inner_num.setText(count+"");
		tv_shopcar_inner_param_press.setText(innerBean.param);
		tv_shopcar_inner_title.setText(innerBean.title);
		tv_shopcar_inner_param.setText(innerBean.param);
		tv_shopcar_inner_price.setText(innerBean.price);
		tv_shopcar_inner_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
		tv_shopcar_inner_oldprice.setText("￥"+innerBean.oldPrice);
		tv_shopcar_inner_count.setText(count+"");
		//切换编辑和完成布局
		if (b) {
			ll_shopcar_edit_press.setVisibility(View.VISIBLE);
			rl_shopcar_edit_normal.setVisibility(View.GONE);
		}else {
			ll_shopcar_edit_press.setVisibility(View.GONE);
			rl_shopcar_edit_normal.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_shopcar_inner_add:
			count++;
			tv_shopcar_inner_num.setText(count+"");
			tv_shopcar_inner_count.setText(count+"");
			break;
		case R.id.iv_shopcar_inner_reduce:
			if (count>1) {
				count--;
				tv_shopcar_inner_num.setText(count+"");
				tv_shopcar_inner_count.setText(count+"");
			}
			break;
		case R.id.tv_shopcar_inner_delete:
			ToastUtil.showMyToast("删除Item"+positionOut+"-"+position);
			ShopCarOutterHolder.innerAdapterList.get(positionOut).deleteItem(position);
			break;
		default:
			break;
		}
		
	}

}
