package com.dida.first.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.dida.first.R;
import com.dida.first.entity.BeanProduct;
import com.dida.first.utils.UIUtils;

/**
 * @author		KingJA 
 * @data		2015-10-16 下午3:52:49 
 * @use			
 *
 */
public class Product_Real_Holder extends BaseHolder<BeanProduct> {

	@Override
	public View initView() {
		view=UIUtils.inflate(R.layout.item_product_real);
		return view;
	}

	@Override
	public void refreshView() {
		ImageView iv_product_real_img = (ImageView) view.findViewById(R.id.iv_product_real_img);
		LayoutParams layoutParams = (LayoutParams) iv_product_real_img.getLayoutParams();
		layoutParams.weight=(UIUtils.getScreenWidth()-UIUtils.dip2px(4)*4)/3;
		layoutParams.height=(int) (layoutParams.weight*1.4f);
	}

}