package com.dida.first.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dida.first.R;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanUserInfo;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.SharedPreferencesUtils;
import com.dida.first.utils.StringUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Request;

/**
 * @author KingJA
 * @data 2015-6-25 下午1:16:41
 * @use
 * 
 */
public class NameActivity extends BackTitleActivity {

	private static final String TAG = "NameActivity";
	private ImageView iv_suiji;
	private EditText et_name_name;
	private Button btn_name;
	private List<String> list;
	private String userName;
	private List<String> minganList;
	private String userPhone;
	private String userPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 子线程读取随机名字和敏感词汇存入内存(List)
		 */
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ReadAssert(CustomConstants.RANDOMNAMES_FILE);
				ReadMinGan(CustomConstants.MINGANWORDS_FILE);
			}
		}){}.start();
		
	}
	/**
	 * 返回按钮等同于X按钮
	 */
	@Override
	public void onBackPressed() {
		if (checkName()) {
			ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
			finish();
		}
	}

	/**
	 * 检查是否可以注册
	 */
	private boolean checkName() {
		userName = et_name_name.getText().toString().trim();
		/**
		 * 非空判断
		 */
		if (TextUtils.isEmpty(userName)) {
			ToastUtil.showMyToast("请输入大名");
			return false;
		}
		/**
		 * 超长判断(<=15个字符)
		 */
		if (userName.length() > 15) {
			ToastUtil.showMyToast( "亲，整个短点名字成不");
			return false;
		}
		/**
		 * 检查名字是否包含敏感字符
		 */
		for (String s : minganList) {
			if (s.contains(userName)) {
				ToastUtil.showMyToast("包含敏感词:"+ userName);
				return false;
			}
		}
		
		/**
		 * TODO 检查名字是否已经注册过
		 */

		ToastUtil.showMyToast("你的名字:" + userName);
		return true;

	}

	/**
	 * 设定随机名字
	 */
	private void setRandomName() {
		et_name_name.setText(list.get(new Random().nextInt(list.size())));
	}

	@Override
	public void initDoNet() {
		Bundle bundle = getIntent().getExtras();
		userPhone = bundle.getString("USER_PHONE");
		userPassword = bundle.getString("USER_PASSWORD");
		Log.i(TAG, "userPhone: "+userPhone+"userPassword: "+userPassword);
	}

	@Override
	public View setView() {
		view = View.inflate(NameActivity.this, R.layout.activity_name, null);
		return view;
	}

	@Override
	public void initView() {
		btn_name = (Button) view.findViewById(R.id.btn_name);
		et_name_name = (EditText) view.findViewById(R.id.et_name_name);
		iv_suiji = (ImageView) view.findViewById(R.id.iv_suiji);

	}

	@Override
	public void initEvent() {
		iv_suiji.setOnClickListener(this);
		btn_name.setOnClickListener(this);

	}

	/**
	 * 初始名字为UUID去"-"后前10位字符
	 */
	@Override
	public void initData() {
		setBackTitle("2/2  完善用户名");
		et_name_name.setText(StringUtil.getUUIDString());

	}



	

	/**
	 * 读取assets下的随机名字文件
	 * 
	 * @param fileName
	 */
	private void ReadAssert(String fileName) {
		AssetManager am = this.getResources().getAssets();
		InputStream is = null;

		try {
			is = am.open(fileName);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			String lineString = "";
			list=new ArrayList<String>();
			while ((lineString = reader.readLine()) != null) {
				list.add(lineString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取assets下的敏感词文件
	 * 
	 * @param fileName
	 */
	private void ReadMinGan(String fileName) {
		AssetManager am = this.getAssets();
		InputStream is = null;
		
		try {
			is = am.open(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			String lineString = "";
			minganList=new ArrayList<String>();
			while ((lineString = reader.readLine()) != null) {
				String s[] = lineString.split("\\|");
				if(s.length!=2){
					continue;
				}
				minganList.add(s[0].trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void setBackClick() {
		if (checkName()) {
			ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
			finish();
		}
		
	}
	@Override
	public void onChildClick(View v) {

		switch (v.getId()) {
		case R.id.iv_suiji:
			setRandomName();
			break;
		case R.id.btn_name:
			if (checkName()) {
				Log.i(TAG, "checkName: ");
				loadNet(userName,userPassword,userPhone);
			}
			break;
		}

	
	}

	private void loadNet(String userName, String userPassword, String userPhone) {
		mDialogProgress.show();
		OkHttpUtils
				.post()
				.url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_REGISTER))
				.addParams("nickName", userName)
				.addParams("passWord", userPassword)
				.addParams("phone", userPhone)
				.addParams("app", "1")
				.build()
				.execute(new JsonCallBack<BeanUserInfo>(BeanUserInfo.class) {
					@Override
					public void onError(Request request, Exception e) {
						mDialogProgress.dismiss();
					}

					@Override
					public void onResponse(BeanUserInfo bean) {
						Log.i(TAG, "USER_NAME: "+bean.getRes().getNickName());
						SharedPreferencesUtils.saveStringData("USER_NAME",bean.getRes().getNickName());
						SharedPreferencesUtils.saveStringData("USER_ID",bean.getRes().getUserId());
						SharedPreferencesUtils.saveIntData("USER_GENDER",bean.getRes().getSex());
						SharedPreferencesUtils.saveStringData("USER_ICON",bean.getRes().getThumb());
						SharedPreferencesUtils.saveStringData("USER_TOKEN",bean.getRes().getToken());
						mDialogProgress.dismiss();
						ActivityUtil.goActivityAndFinish(NameActivity.this, MainActivity.class);
						ToastUtil.showMyToast("Welcome AAMAI！");
					}
				});
	}
}
