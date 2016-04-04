package com.dida.first.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dida.first.R;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.DataCleanManager;
import com.dida.first.utils.ToastUtil;
import com.dida.first.dialog.DialogProgress;

/**
 * @author KingJA
 * @data 2015-9-30 下午4:13:59
 * @use
 * 
 */
public class Mine_Setting_Activity extends BackTitleActivity {

	private TextView tv_mine_setting_size;
	private TextView tv_mine_setting_version;
	private Button btn_mine_setting_quit;
	private LinearLayout ll_root;

	@Override
	public View setView() {
		view = View.inflate(Mine_Setting_Activity.this,
				R.layout.activity_mine_setting, null);
		return view;
	}

	@Override
	public void initView() {
		tv_mine_setting_version = (TextView) view
				.findViewById(R.id.tv_mine_setting_version);
		tv_mine_setting_size = (TextView) view
				.findViewById(R.id.tv_mine_setting_size);
		RelativeLayout rl_mine_setting_share = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_share);
		RelativeLayout rl_mine_setting_report = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_report);
		RelativeLayout rl_mine_setting_help = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_help);
		RelativeLayout rl_mine_setting_update = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_update);
		RelativeLayout rl_mine_setting_cache = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_cache);
		RelativeLayout rl_mine_setting_about = (RelativeLayout) view
				.findViewById(R.id.rl_mine_setting_about);
		ll_root = (LinearLayout) view
				.findViewById(R.id.ll_root);
		btn_mine_setting_quit = (Button) view
				.findViewById(R.id.btn_mine_setting_quit);

		rl_mine_setting_share.setOnClickListener(this);
		rl_mine_setting_report.setOnClickListener(this);
		rl_mine_setting_help.setOnClickListener(this);
		rl_mine_setting_update.setOnClickListener(this);
		rl_mine_setting_cache.setOnClickListener(this);
		rl_mine_setting_about.setOnClickListener(this);
	}

	@Override
	public void initDoNet() {
		btn_mine_setting_quit.setOnClickListener(this);

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		setBackTitle("设置");
		tv_mine_setting_version.setText(getVersion());
		try {
			Log.i("getCacheDir()", getCacheDir().toString());
			Log.i("getExternalCacheDir()", getExternalCacheDir().toString());
			tv_mine_setting_size.setText(DataCleanManager
					.getCacheSize(getExternalCacheDir()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.rl_mine_setting_share:

			break;
		case R.id.rl_mine_setting_report:
			goActivity(Setting_Report_Activity.class);
			break;
		case R.id.rl_mine_setting_help:
			ActivityUtil.goActivity(Mine_Setting_Activity.this, Setting_Help_Activity.class);
			break;
		case R.id.rl_mine_setting_update:

			break;
		case R.id.rl_mine_setting_cache:
			final DialogProgress dialogProgress = new DialogProgress(
					Mine_Setting_Activity.this);
			dialogProgress.show();

			DataCleanManager.cleanExternalCache(Mine_Setting_Activity.this);
			try {
				tv_mine_setting_size.setText(DataCleanManager
						.getCacheSize(getExternalCacheDir()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dialogProgress.dismiss();
			ToastUtil.showMyToast("清理完成");

			break;
		case R.id.rl_mine_setting_about:
			goActivity(Setting_About_Activity.class);
			break;
		case R.id.btn_mine_setting_quit:
			Snackbar.make(ll_root," Hello,I am Snackbar!", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("onClick","onClick");
				}
			}).show();
			break;

		default:
			break;
		}

	}

	@Override
	public void setBackClick() {

	}

	private void goActivity(Class clazz) {
		Intent intent = new Intent(Mine_Setting_Activity.this, clazz);
		startActivity(intent);
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String versionName = info.versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	
}
