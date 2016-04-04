/**
 * 
 */
package com.dida.first.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.YaoYueBean.Res;
import com.dida.first.utils.TimeUtils;
import com.dida.first.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;
import com.meg7.widget.CircleImageView;

/**
 * @author KingJA
 * @data 2015-8-14 上午10:23:35
 * @use
 * 
 */
public class GroupHolder extends BaseHolder<Res> {

	private TextView tv_yaoyue_group_count;
	private TextView tv_yaoyue_group_minute;
	private TextView tv_yaoyue_group_hour;
	private TextView tv_yaoyue_group_day;
	private TextView tv_yaoyue_group_price;
	private TextView tv_yaoyue_group_title;
	private ImageView iv_yaoyue_group_image;
	private CircleImageView iv_yaoyue_group_touxinag;
	private BitmapUtils bitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_yaoyue_group);
		iv_yaoyue_group_touxinag = (CircleImageView) view
				.findViewById(R.id.iv_yaoyue_group_touxinag);
		iv_yaoyue_group_image = (ImageView) view
				.findViewById(R.id.iv_yaoyue_group_image);
		tv_yaoyue_group_title = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_title);
		tv_yaoyue_group_price = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_price);
		tv_yaoyue_group_day = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_day);
		tv_yaoyue_group_hour = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_hour);
		tv_yaoyue_group_minute = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_minute);
		tv_yaoyue_group_count = (TextView) view
				.findViewById(R.id.tv_yaoyue_group_count);
		return view;
	}

	@Override
	public void refreshView() {
		Res res=getData();
		bitmapUtils=new BitmapUtils(UIUtils.getContext());
		bitmapUtils.display(iv_yaoyue_group_touxinag, "http://www.jzwifi.cn/upload/image/20150814/20150814132344_882.jpg");
		tv_yaoyue_group_title.setText(res.des);
		tv_yaoyue_group_price.setText(res.price+"");
		tv_yaoyue_group_count.setText(res.newCount+"");
			int[] deadTime = TimeUtils.getDeadTime(res.customDueDate);
			tv_yaoyue_group_day.setText(deadTime[0]+"");
			tv_yaoyue_group_hour.setText(deadTime[1]+"");
			tv_yaoyue_group_minute.setText(deadTime[2]+"");
	}

}
