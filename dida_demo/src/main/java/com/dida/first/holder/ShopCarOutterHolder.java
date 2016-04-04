package com.dida.first.holder;

import java.util.ArrayList;
import java.util.List;
import com.dida.first.R;
import com.dida.first.activity.Mine_ShopCar_Activity;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.ShopCarOutterBean;
import com.dida.first.entity.ShopCarOutterBean.ShopCarInnerBean;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author 		KingJA
 * @data 		2015-9-1 下午1:06:46
 * @use			待购订单外层Holder，负责编辑按钮的事件触发
 * 
 */
public class ShopCarOutterHolder extends BaseHolder<ShopCarOutterBean>
		implements OnClickListener {

	private TextView tv_mine_shopcar_edit;
	private TextView tv_mine_shopcar_shopname;
	private LinearLayout ll_mine_shopcar_shop;
	private MyListView mylv_shopcar_inner;
	public static boolean ifEdit;
	public static List<InnerAdapter> innerAdapterList = new ArrayList<InnerAdapter>();
	private InnerAdapter innerAdapter;

	@Override
	public View initView() {
		View inflate = UIUtils.inflate(R.layout.item_shopcar_out);
		tv_mine_shopcar_edit = (TextView) inflate
				.findViewById(R.id.tv_mine_shopcar_edit);
		tv_mine_shopcar_shopname = (TextView) inflate
				.findViewById(R.id.tv_mine_shopcar_shopname);
		ll_mine_shopcar_shop = (LinearLayout) inflate
				.findViewById(R.id.ll_mine_shopcar_shop);
		mylv_shopcar_inner = (MyListView) inflate
				.findViewById(R.id.mylv_shopcar_inner);
		return inflate;
	}

	@Override
	public void refreshView() {
		reSet();
		ShopCarOutterBean data = getData();
		tv_mine_shopcar_shopname.setText(data.shopName);
		List<ShopCarInnerBean> innerBeanList = data.innerBeanList;
		innerAdapter = new InnerAdapter(innerBeanList);
		innerAdapterList.add(innerAdapter);
		mylv_shopcar_inner.setAdapter(innerAdapter);
		tv_mine_shopcar_edit.setOnClickListener(this);
		ll_mine_shopcar_shop.setOnClickListener(this);

	}

	/**
	 * 解决删除后下一个Item编辑状态为完成的BUG
	 */
	public void reSet() {
		ifEdit = false;
		tv_mine_shopcar_edit.setText("编辑");
	}

	class InnerAdapter extends MyBaseAdapter<ShopCarInnerBean> {
		public InnerAdapter(List<ShopCarInnerBean> list) {
			super(list);
		}

		public void deleteItem(int positionIn) {
			// 删掉内层
			list.remove(positionIn);
			notifyDataSetChanged();
			// 如果内层已经删完则删掉外层Item
			if (list.size() == 0) {
				Mine_ShopCar_Activity.outAdapter
						.deleteItem(ShopCarOutterHolder.this.position);
			}
		}

		@Override
		public BaseHolder<ShopCarInnerBean> getHolder() {
			return new ShopCarInnerHolder(ShopCarOutterHolder.this.position);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_mine_shopcar_edit:
			ToastUtil.showMyToast("修改" + position);
			if (!ifEdit) {
				ifEdit = true;
				tv_mine_shopcar_edit.setText("完成");
			} else {
				ifEdit = false;
				tv_mine_shopcar_edit.setText("编辑");
			}
			innerAdapter.setBoolean(ifEdit);
			innerAdapter.notifyDataSetChanged();
			break;
		case R.id.ll_mine_shopcar_shop:
			ToastUtil.showMyToast("店铺详情");
			break;

		default:
			break;
		}

	}
}
