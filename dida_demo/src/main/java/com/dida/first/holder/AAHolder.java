package com.dida.first.holder;

import com.dida.first.R;
import com.dida.first.entity.YaoYueBean.Res;
import com.dida.first.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;
import com.meg7.widget.CircleImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author KingJA
 * @data 2015-8-14 上午10:23:35
 * @use
 * 
 */
public class AAHolder extends BaseHolder<Res> {

	private TextView tv_yaoyue_aa_count;
	private TextView tv_yaoyue_aa_price;
	private TextView tv_yaoyue_aa_title;
	private ImageView iv_yaoyue_aa_image;
	private CircleImageView iv_yaoyue_aa_touxiang;
	private BitmapUtils bitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_yaoyue_aa);
		iv_yaoyue_aa_touxiang = (CircleImageView) view
				.findViewById(R.id.iv_yaoyue_aa_touxiang);
		iv_yaoyue_aa_image = (ImageView) view
				.findViewById(R.id.iv_yaoyue_aa_image);
		tv_yaoyue_aa_title = (TextView) view
				.findViewById(R.id.tv_yaoyue_aa_title);
		tv_yaoyue_aa_price = (TextView) view
				.findViewById(R.id.tv_yaoyue_aa_price);
		tv_yaoyue_aa_count = (TextView) view
				.findViewById(R.id.tv_yaoyue_aa_count);
		return view;
	}

	@Override
	public void refreshView() {
		Res res=getData();
		bitmapUtils=new BitmapUtils(UIUtils.getContext());
		bitmapUtils.display(iv_yaoyue_aa_touxiang, "http://www.jzwifi.cn/upload/image/20150814/20150814132344_882.jpg");
		tv_yaoyue_aa_title.setText(res.des);
		tv_yaoyue_aa_price.setText(res.price+"");
		tv_yaoyue_aa_count.setText(res.newCount+"");
	}

}
