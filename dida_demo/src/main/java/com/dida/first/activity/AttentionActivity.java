
package com.dida.first.activity;

import android.view.View;

import com.dida.first.R;
import com.dida.first.adapter.PingouTeamStoreAdapter;
import com.dida.first.adapter.PingouTeamUserAdapter;
import com.dida.first.entity.BeanDetailPingouStore;
import com.dida.first.entity.BeanDetailPingouUser;
import com.dida.first.utils.UIUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-8-20 下午4:53:02
 * @use	关注列表
 * 
 */
public class AttentionActivity extends BackTitleActivity {

	private PullToRefreshListView plv_attention_list;
	private List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity> pingou_user;
	private List<BeanDetailPingouStore.ResEntity.ComGroupDetailEntity.ParticipatesEntity> pingou_store;

	@Override
	public void onChildClick(View v) {
		
	}

	@Override
	public View setView() {
		view = UIUtils.inflate(R.layout.list_attention);
		return view;
	}

	@Override
	public void initView() {
		plv_attention_list = (PullToRefreshListView) view.findViewById(R.id.plv_attention_list);
	}
	@Override
	public void initDoNet() {
		if ("USER".equals(getIntent().getExtras().getString("TYPE"))) {
			pingou_user = (List<BeanDetailPingouUser.ResEntity.ComGroupDetailEntity.ParticipatesEntity>) getIntent().getSerializableExtra("PINGOU_TEAM");
			plv_attention_list.setAdapter(new PingouTeamUserAdapter(pingou_user,this));
		} else {
			pingou_store = (List<BeanDetailPingouStore.ResEntity.ComGroupDetailEntity.ParticipatesEntity>) getIntent().getSerializableExtra("PINGOU_TEAM");
			plv_attention_list.setAdapter(new PingouTeamStoreAdapter(pingou_store,this));
		}


	}
	@Override
	public void initEvent() {

	}

	@Override
	public void initData() {
		setBackTitle("团员列表");
	}


	@Override
	public void setBackClick() {
		finish();
		
	}
}
