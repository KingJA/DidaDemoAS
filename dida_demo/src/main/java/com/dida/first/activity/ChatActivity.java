package com.dida.first.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.dida.first.R;

/**
 * @author		KingJA 
 * @data		2015-10-12 上午10:53:23 
 * @use			
 *
 */
public class ChatActivity extends Activity {
	private String token;
	private EditText et_userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
	}

//	public void login(View v){
//
//
//		RongIM.connect(token, new  ConnectCallback() {
//
//			@Override
//			public void onSuccess(String arg0) {
//				Toast.makeText(ChatActivity.this,
//						"connect onSuccess", Toast.LENGTH_SHORT)
//						.show();
//				Intent intent=new Intent(ChatActivity.this,RongSelectActivity.class);
//				startActivity(intent);
//			}
//
//			@Override
//			public void onError(ErrorCode arg0) {
//				Toast.makeText(ChatActivity.this,
//						"connect onError", Toast.LENGTH_SHORT)
//						.show();
//			}
//
//			@Override
//			public void onTokenIncorrect() {
//				Toast.makeText(ChatActivity.this,
//						"onTokenIncorrect", Toast.LENGTH_SHORT)
//						.show();
//			}
//		});
//
//	}
//	public void register(View v){
//		et_userid = (EditText) findViewById(R.id.et_userid);
//		String userId = et_userid.getText().toString().trim();
//		getToken(userId);
//
//	}
//	public void getToken(final String userId) {
//		// HttpPost方式请求
//		new Thread() {
//			public void run() {
//
//				String path = "https://api.cn.ronghub.com/user/getToken.json";
//				// 新建HttpPost对象
//				HttpPost httpPost = new HttpPost(path);
//				// Post参数
//				String appKey = "lmxuhwagx81hd";
//				String appSecret = "jru3jGYRtV";
//
//				String random = Math.random() * 1000000 + "";
//				String currentTimeMillis = System.currentTimeMillis() / 1000
//						+ "";
//				httpPost.addHeader("RC-App-Key", appKey);
//				httpPost.addHeader("RC-Nonce", random);
//				httpPost.addHeader("RC-Timestamp", currentTimeMillis);
//				Log.i("currentTimeMillis", currentTimeMillis);
//				try {
//					Log.i("getSha1", HttpUtils.getSha1(appSecret
//							+ random + currentTimeMillis));
//					httpPost.addHeader("RC-Signature", HttpUtils.getSha1(appSecret
//							+ random + currentTimeMillis));
//					List<NameValuePair> params = new ArrayList<NameValuePair>();
//					params.add(new BasicNameValuePair("userId", userId));
//					params.add(new BasicNameValuePair("name", "wxy5"));
//					params.add(new BasicNameValuePair("portraitUri", "http://file-www.sioe.cn/201109/14/222211817.jpg"));
//					// 设置字符集
//					HttpEntity entity = new UrlEncodedFormEntity(params,
//							HTTP.UTF_8);
//					// 设置参数实体
//					httpPost.setEntity(entity);
//					// 获取HttpClient对象
//					HttpClient httpClient = new DefaultHttpClient();
//					// 获取HttpResponse实例
//					HttpResponse httpResp = httpClient.execute(httpPost);
//					// 判断是够请求成功
//					if (httpResp.getStatusLine().getStatusCode() == 200) {
//						// 获取返回的数据
//						String result = EntityUtils.toString(
//								httpResp.getEntity(), "UTF-8");
//						Log.i("", "HttpPost方式请求成功，返回数据如下：");
//						Log.i("", result);
//						token= HttpUtils.processJson(result);
//						SharedPreferencesUtils.saveStringData("TOKEN", token);
//						runOnUiThread(new Runnable() {
//							public void run() {
//								Toast.makeText(ChatActivity.this,token, 1).show();
//							}
//						});
//					} else {
//						Log.i("", "HttpPost方式请求失败");
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			};
//		}.start();
//
//	}
}
