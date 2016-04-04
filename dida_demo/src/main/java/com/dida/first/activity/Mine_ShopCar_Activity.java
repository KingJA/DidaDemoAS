/**
 * 
 */
package com.dida.first.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseAdapter;
import com.dida.first.entity.ShopCarOutterBean;
import com.dida.first.entity.ShopCarOutterBean.ShopCarInnerBean;
import com.dida.first.holder.BaseHolder;
import com.dida.first.holder.ShopCarOutterHolder;
import com.dida.first.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-31 下午3:48:23
 * @use 待购订单Activity，主要处理待购订单的支付，修改，删除等。
 * 
 */
public class Mine_ShopCar_Activity extends BackTitleActivity {
	private ListView lv_shopcar;
	private CheckBox cb_mine_shopcar;
	private TextView tv_mine_shopcar_price;
	private TextView tv_mine_shopcar_count;
	private RelativeLayout rl_mine_shopcar_calculate;
	private List<ShopCarOutterBean> OutterBean = new ArrayList<ShopCarOutterBean>();
	public static ShopCarAdapter outAdapter;

	@Override
	public View setView() {
		view = View.inflate(this, R.layout.activity_mine_shopcar, null);
		return view;
	}

	@Override
	public void initView() {
		lv_shopcar = (ListView) view.findViewById(R.id.lv_shopcar);
		cb_mine_shopcar = (CheckBox) view.findViewById(R.id.cb_mine_shopcar);
		tv_mine_shopcar_price = (TextView) view
				.findViewById(R.id.tv_mine_shopcar_price);
		tv_mine_shopcar_count = (TextView) view
				.findViewById(R.id.tv_mine_shopcar_count);
		rl_mine_shopcar_calculate = (RelativeLayout) view
				.findViewById(R.id.rl_mine_shopcar_calculate);

	}

	@Override
	public void initEvent() {
		rl_mine_shopcar_calculate.setOnClickListener(this);
		outAdapter = new ShopCarAdapter(OutterBean);
		lv_shopcar.setAdapter(outAdapter);

	}

	@Override
	public void initDoNet() {
		ShopCarOutterHolder.innerAdapterList.clear();
		initShopCarData();

	}

	/**
	 * 模拟数据
	 */
	private void initShopCarData() {
		ShopCarOutterBean shopCarOutterBean1 = new ShopCarOutterBean("八仟客");
		ShopCarOutterBean shopCarOutterBean2 = new ShopCarOutterBean(
				"Vovooo网上旗舰店");
		ShopCarOutterBean shopCarOutterBean3 = new ShopCarOutterBean("耐克专营店");
		shopCarOutterBean1.innerBeanList = new ArrayList<ShopCarInnerBean>();
		shopCarOutterBean1.innerBeanList
				.add(shopCarOutterBean1.new ShopCarInnerBean(
						"viishow2015春秋男士卫衣 青年欧美运动开衫修身连帽大码潮男外套", "XXL 修身",
						"156", "288", 1));
		shopCarOutterBean1.innerBeanList
				.add(shopCarOutterBean1.new ShopCarInnerBean(
						"【ABOW】 ABOW字母印花卫衣潮男高街范连帽卫衣暗黑简约长袖T恤", "长款紧身", "346",
						"558", 2));

		shopCarOutterBean2.innerBeanList = new ArrayList<ShopCarInnerBean>();
		shopCarOutterBean2.innerBeanList
				.add(shopCarOutterBean2.new ShopCarInnerBean(
						"衬衫男长袖格子男士长袖字母印花衬衣时尚潮男嘻哈美式休闲男装", "XL小号", "226", "448",
						4));

		shopCarOutterBean3.innerBeanList = new ArrayList<ShopCarInnerBean>();
		shopCarOutterBean3.innerBeanList
				.add(shopCarOutterBean3.new ShopCarInnerBean(
						"九分裤男大码男加肥加大休闲裤子胖子夏装宽松弹力大裆裤 拼接潮", "180大号", "666",
						"1098", 2));
		shopCarOutterBean3.innerBeanList
				.add(shopCarOutterBean3.new ShopCarInnerBean("阿诺斯瓦辛格同款健美短裤",
						"190大号", "8666", "9098", 2));

		OutterBean.add(shopCarOutterBean1);
		OutterBean.add(shopCarOutterBean2);
		OutterBean.add(shopCarOutterBean3);

	}

	@Override
	public void initData() {
		setBackTitle("待购订单");
		setBackNum(OutterBean.size() + "");
	}

	@Override
	public void setBackClick() {
		finish();

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.rl_mine_shopcar_calculate:
			ToastUtil.showMyToast("结算");
			break;

		default:
			break;
		}
	}

	public class ShopCarAdapter extends MyBaseAdapter<ShopCarOutterBean> {

		public ShopCarAdapter(List<ShopCarOutterBean> list) {
			super(list);
		}

		/**
		 * 删除外层Item，并清空内层Item适配器集合
		 * 
		 * @param positionOut
		 */
		public void deleteItem(int positionOut) {
			list.remove(positionOut);
			ShopCarOutterHolder.innerAdapterList.clear();
			setNotyfyData(list);
		}

		@Override
		public BaseHolder<ShopCarOutterBean> getHolder() {
			return new ShopCarOutterHolder();
		}
	}

}
