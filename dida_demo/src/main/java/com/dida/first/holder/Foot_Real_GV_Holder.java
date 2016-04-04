/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.dida.first.R;
import com.dida.first.entity.BeanFootPrint.ItemFootPrint;
import com.dida.first.utils.UIUtils;

/**
 * @author KingJA
 * @data 2015-9-24 下午4:40:53
 * @use
 * 
 */
public class Foot_Real_GV_Holder extends BaseHolder<ItemFootPrint> implements OnCheckedChangeListener{
	private RelativeLayout.LayoutParams param;
	private ImageView iv_foot_real_image;
	private CheckBox cb_foot_real;

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.item_foot_real_gv);
		return view;
	}

	@Override
	public void refreshView() {
		ItemFootPrint itemFootPrint = getData();
		cb_foot_real = (CheckBox) view.findViewById(R.id.cb_foot_real);
		cb_foot_real
				.setVisibility(itemFootPrint.ifShow == true ? View.VISIBLE
						: View.GONE);
		cb_foot_real
		.setChecked(itemFootPrint.ifCheck);
		cb_foot_real.setOnCheckedChangeListener(this);
		iv_foot_real_image = (ImageView) view
				.findViewById(R.id.iv_foot_real_image);
		int screenWidth = UIUtils.getScreenWidth();
		int itemWidth = (screenWidth - UIUtils.dip2px(8)) / 3;
		param = new RelativeLayout.LayoutParams(itemWidth, itemWidth);
		iv_foot_real_image.setLayoutParams(param);

	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

}
