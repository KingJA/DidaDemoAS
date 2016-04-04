package com.dida.first.activity;

import com.dida.first.LoadPage;
import com.dida.first.R;
import com.dida.first.LoadPage.ResultState;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.holder.Refund_Step1_Holder;
import com.dida.first.holder.Refund_Step2_Holder;
import com.dida.first.holder.Refund_Step3_Holder;
import com.dida.first.holder.Refund_Step4_Holder;
import com.dida.first.utils.UIUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author KingJA
 * @data 2015-9-30 上午9:53:52
 * @use
 * 
 */
public class Detail_Refund_Activity extends Activity {
	private LoadPage loadPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityFactory.mRefundActivity=this;
		loadPage = new LoadPage(UIUtils.getContext()) {

			@Override
			public View onCreateSuccessedView() {
				return Detail_Refund_Activity.this.onCreateSuccessedView();
			}

			@Override
			public ResultState onLoad() {
				return Detail_Refund_Activity.this.onLoad();
			}
		};
		setContentView(loadPage);
		// 手动促发onLoad方法的调用
		if (loadPage != null) {
			loadPage.show();
		}
	}

	/**
	 * @return
	 */
	protected ResultState onLoad() {
		return ResultState.STATE_SUCCESSED;
	}

	/**
	 * @return
	 */
	protected View onCreateSuccessedView() {
		View view = UIUtils.inflate(R.layout.activity_refund_detail);
		/* 第一步 */
		FrameLayout fl_refund_detail_step1 = (FrameLayout) view
				.findViewById(R.id.fl_refund_detail_step1);
		Refund_Step1_Holder step1_Holder = new Refund_Step1_Holder();
		fl_refund_detail_step1.addView(step1_Holder.getRootView());
		/* 第二步 */
		FrameLayout fl_refund_detail_step2 = (FrameLayout) view
				.findViewById(R.id.fl_refund_detail_step2);
		Refund_Step2_Holder step2_Holder = new Refund_Step2_Holder();
		fl_refund_detail_step2.addView(step2_Holder.getRootView());
		/* 第三步 */
		FrameLayout fl_refund_detail_step3 = (FrameLayout) view
				.findViewById(R.id.fl_refund_detail_step3);
		Refund_Step3_Holder step3_Holder = new Refund_Step3_Holder();
		fl_refund_detail_step3.addView(step3_Holder.getRootView());
		/* 第四步 */
		FrameLayout fl_refund_detail_step4 = (FrameLayout) view
				.findViewById(R.id.fl_refund_detail_step4);
		Refund_Step4_Holder step4_Holder = new Refund_Step4_Holder();
		fl_refund_detail_step4.addView(step4_Holder.getRootView());
		
		return view;
	}
}
