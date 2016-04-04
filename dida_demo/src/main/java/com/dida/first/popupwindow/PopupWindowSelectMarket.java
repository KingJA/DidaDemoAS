/**
 * 
 */
package com.dida.first.popupwindow;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;
import com.dida.first.view.FlowLayout;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-18 下午3:18:50
 * @use
 * 
 */
public class PopupWindowSelectMarket extends
		PopupWindowBaseDown<List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity>> {
	private MyListView mylv_market_detail_select;
	private MyAdapter myAdapter;
	private TextView tv_param_select_count;
	private int count = 1;

	public PopupWindowSelectMarket(View parentView, Activity activity,
								   List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity> data) {
		super(parentView, activity, data);

	}

	@Override
	public int setPopupHeight() {
		return screenHeight * 3 / 5;
	}

	@Override
	public View setPopupView(Activity activity) {
		popupView = View.inflate(activity, R.layout.popup_select, null);
		return popupView;
	}

	@Override
	public void initChildView() {
		tv_param_select_count = (TextView) popupView
				.findViewById(R.id.tv_param_select_count);
		ImageView iv_param_select_add = (ImageView) popupView
				.findViewById(R.id.iv_param_select_add);
		ImageView iv_param_select_reduce = (ImageView) popupView
				.findViewById(R.id.iv_param_select_reduce);
		ImageView iv_mark_detail_close = (ImageView) popupView
				.findViewById(R.id.iv_mark_detail_close);
		mylv_market_detail_select = (MyListView) popupView
				.findViewById(R.id.mylv_market_detail_select);
		myAdapter = new MyAdapter(data);
		mylv_market_detail_select.setAdapter(myAdapter);
		iv_mark_detail_close.setOnClickListener(this);
		TextView tv_market_select = (TextView) popupView
				.findViewById(R.id.tv_market_select);
		tv_market_select.setOnClickListener(this);
		iv_param_select_add.setOnClickListener(this);
		iv_param_select_reduce.setOnClickListener(this);
	}

	@Override
	public void OnChildClick(View v) {
		switch (v.getId()) {
		case R.id.tv_market_select:
			String params = myAdapter.getParams();
			ToastUtil.showMyToast(params + count + "个");
			dismiss();
			break;
		case R.id.iv_mark_detail_close:
			dismiss();
			break;
		case R.id.iv_param_select_add:
			count++;
			tv_param_select_count.setText(String.valueOf(count));
			break;
		case R.id.iv_param_select_reduce:
			if (count > 1) {
				count--;
				tv_param_select_count.setText(String.valueOf(count));
			}
			break;

		default:
			break;
		}

	}

	class MyAdapter extends MyBaseListViewAdapter<BeanDetailMarket.ResEntity.PurchaseAttrListEntity> {
		public MyAdapter(List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getItemView(final int position, View convertView,
				ViewGroup parent) {
			View view = UIUtils.inflate(R.layout.item_param_select);
			TextView tv_param_slecet_name = (TextView) view
					.findViewById(R.id.tv_param_slecet_name);
			FlowLayout fl_param_slecet_params = (FlowLayout) view
					.findViewById(R.id.fl_param_slecet_params);

			String paramName = list.get(position).getAttributeName();
			List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity> attrValues = list.get(position).getAttrValues();
			tv_param_slecet_name.setText(paramName);
			for (final BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity attr : attrValues) {
				final TextView tv = (TextView) UIUtils
						.inflate(R.layout.textview_flowlayout);
				tv.setText(attr.getAttrValue());
				if (attr.isCheck()) {
					tv.setBackgroundResource(R.drawable.shape_lnull_bred_r4);
//					tv.setTextColor(activity.getResources().getColor(
//							R.color.white));
					tv.setTextColor(ContextCompat.getColor(activity, R.color.white));
				} else {
					tv.setBackgroundResource(R.drawable.shape_lnull_bgray_r4);
//					tv.setTextColor(activity.getResources().getColor(
//							R.color.gray_deep));
					tv.setTextColor(ContextCompat.getColor(activity, R.color.gray_deep));

				}
				tv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setFalse(position);
						attr.setCheck(true);
						notifyDataSetChanged();

					}
				});
				fl_param_slecet_params.addView(tv);
			}
			return view;
		}

		public void setFalse(int position) {
			List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity> attrValues = list.get(position).getAttrValues();
			for (BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity  attr : attrValues) {
				attr.setCheck(false);
			}
			notifyDataSetChanged();
		}

		public String getParams() {
			StringBuffer sb = new StringBuffer();
			for (BeanDetailMarket.ResEntity.PurchaseAttrListEntity outVallues : list) {
				List<BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity> attrValues = outVallues.getAttrValues();
				for (BeanDetailMarket.ResEntity.PurchaseAttrListEntity.AttrValuesEntity attr : attrValues) {
					if (attr.isCheck()) {
						sb.append(attr.getAttrValue());
					}
				}
			}
			return sb.toString();
		}
	}
}
