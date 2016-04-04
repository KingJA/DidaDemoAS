/**
 * 
 */
package com.dida.first.holder;

import com.dida.first.R;
import com.dida.first.activity.AttentionActivity;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.UIUtils;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;

/**
 * @author		KingJA 
 * @data		2015-8-18 下午1:31:56 
 * @use			
 *
 */
public class GDetail_Team_Holder extends BaseHolder {

	private GridView gv_group_detail_team;
	private RelativeLayout rl_gd_more_team;

	@Override
	public View initView() {
		View initView=UIUtils.inflate(R.layout.group_detail_team);
		rl_gd_more_team = (RelativeLayout) initView.findViewById(R.id.rl_gd_more_team);
		openMore();
		gv_group_detail_team = (GridView) initView.findViewById(R.id.gv_group_detail_team);
		return initView;
	}

	private void openMore() {
		rl_gd_more_team.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ActivityFactory.mainActivity,AttentionActivity.class);
				ActivityFactory.mainActivity.startActivity(intent);
//				ActivityFactory.groupActivity.overridePendingTransition(R.anim.next_in, R.anim.next_out);
			}
		});
	}

	@Override
	public void refreshView() {
		gv_group_detail_team.setAdapter(new TeamIconAdapter());
	}
	class TeamIconAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 8;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView==null) {
				Log.i("convertView", "convertView="+position);
				convertView=UIUtils.inflate(R.layout.item_group_detail_team);
			}
			return convertView;
		}
		
	}

}
