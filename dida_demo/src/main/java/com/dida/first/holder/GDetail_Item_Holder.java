/**
 * 
 */
package com.dida.first.holder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UImageLoaderUitl;

import java.util.List;

/**
 * @author		KingJA 
 * @data		2015-8-18 上午10:15:14 
 * @use			
 *
 */
public class GDetail_Item_Holder extends BaseHolder<BeanDetailPingouUser> implements AdapterView.OnItemClickListener {
	private ListView lv_group_detail_item;
	protected Activity activity;

	public GDetail_Item_Holder(Activity activity) {
		this.activity=activity;
	}

	@Override
	public View initView() {
		View inView=UIUtils.inflate(R.layout.group_detail_item);
		lv_group_detail_item = (ListView) inView.findViewById(R.id.lv_group_detail_item);
		return inView;
	}

	@Override
	public void refreshView() {
		BeanDetailPingouUser data = getData();
		List<BeanDetailPingouUser.ResEntity.PrepayordersEntity> prepayorders = data.getRes().getPrepayorders();
		lv_group_detail_item.setAdapter(new GroupItemAdapter(prepayorders));
		lv_group_detail_item.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		ActivityUtil.goActivity(activity, Detail_Market_Activity.class);
	}

	class GroupItemAdapter extends BaseAdapter{

		private List<BeanDetailPingouUser.ResEntity.PrepayordersEntity> list;

		public GroupItemAdapter(List<BeanDetailPingouUser.ResEntity.PrepayordersEntity> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder=null;
			if (convertView==null){
				convertView=UIUtils.inflate(R.layout.item_group_detail_item);
				viewHolder=new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}


			viewHolder.tvitemgroupdetailtitle.setText(list.get(position).getOrderName());
			viewHolder.tvitemgroupdetailprice.setText("¥"+list.get(position).getPrice());
			viewHolder.tvitemgroupdetailcount.setText("X"+list.get(position).getCount());
			viewHolder.tvitemgroupdetailparam.setText(list.get(position).getOrderattrvalue());
			viewHolder.tv_item_group_detail_edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
			UImageLoaderUitl.displayGvMidImage(list.get(position).getProductThumb(),viewHolder.ivitemgroupdetailicon);

			return convertView;
		}

		private class ViewHolder {
			public final ImageView ivitemgroupdetailicon;
			public final TextView tvitemgroupdetailtitle;
			public final TextView tvitemgroupdetailparam;
			public final TextView tvitemgroupdetailprice;
			public final TextView tvitemgroupdetailcount;
			public final TextView tv_item_group_detail_edit;
			public final View root;

			public ViewHolder(View root) {
				ivitemgroupdetailicon = (ImageView) root.findViewById(R.id.iv_item_group_detail_icon);
				tvitemgroupdetailtitle = (TextView) root.findViewById(R.id.tv_item_group_detail_title);
				tvitemgroupdetailparam = (TextView) root.findViewById(R.id.tv_item_group_detail_param);
				tvitemgroupdetailprice = (TextView) root.findViewById(R.id.tv_item_group_detail_price);
				tvitemgroupdetailcount = (TextView) root.findViewById(R.id.tv_item_group_detail_count);
				tv_item_group_detail_edit = (TextView) root.findViewById(R.id.tv_item_group_detail_edit);
				this.root = root;
			}
		}
	}
}
