package com.dida.first.rongyun;

import com.dida.first.R;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.RongIMClient.SendMessageCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.RichContentMessage;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RongSelectActivity extends Activity {
	private EditText et_friend;
	private String token;
	private String friend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rong_select);

		// 连接融云服务器
		this.findViewById(android.R.id.text1).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						RongIM.connect(token, new ConnectCallback() {

							@Override
							public void onError(ErrorCode arg0) {
								Toast.makeText(RongSelectActivity.this,
										"connect onError", Toast.LENGTH_SHORT)
										.show();
							}

							@Override
							public void onSuccess(String arg0) {
								Toast.makeText(RongSelectActivity.this,
										"connect onSuccess", Toast.LENGTH_SHORT)
										.show();
							}

							@Override
							public void onTokenIncorrect() {
								// TODO Auto-generated method stub

							}
						});

					}
				});

		// 启动会话列表
		this.findViewById(android.R.id.button1).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						RongIM.getInstance().startConversationList(
								RongSelectActivity.this);
					}
				});

		// 启动会话页面
		this.findViewById(android.R.id.button2).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						et_friend = (EditText) findViewById(R.id.et_friend);
						friend = et_friend.getText().toString().trim();

						// RongIM.getInstance().startConversation(MainActivity.this,
						// Conversation.ConversationType.PRIVATE, "9527",
						// "聊天标题");
						// 1.通过 Api 调用
						if (RongIM.getInstance() != null) {
							/**
							 * 启动单聊界面。
							 * 
							 * @param context
							 *            应用上下文。
							 * @param targetUserId
							 *            要与之聊天的用户 Id。
							 * @param title
							 *            聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
							 */
							RongIM.getInstance().startPrivateChat(
									RongSelectActivity.this, friend, "陌生人");
//							sendImage();
						}

					}
				});
	}

	public void buy(View v) {
		et_friend = (EditText) findViewById(R.id.et_friend);
		friend = et_friend.getText().toString().trim();
		// "聊天标题");
		// 1.通过 Api 调用
		if (RongIM.getInstance() != null) {
			/**
			 * 启动单聊界面。
			 * 
			 * @param context
			 *            应用上下文。
			 * @param targetUserId
			 *            要与之聊天的用户 Id。
			 * @param title
			 *            聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
			 */
			RongIM.getInstance().startPrivateChat(
					RongSelectActivity.this, friend, "陌生人");
		}

	
	}

	/**
	 * 图文信息
	 */
	private void sendImage() {
		RichContentMessage richContentMessage = RichContentMessage
				.obtain("168","夏日天堂劲爆金正日奥巴马同款手游，我在上海正式亮相，请大家赶紧来购买，时机不可错过，赶紧买一送一,现在买就送1比1真人奥特曼。",
						"http://img.name2012.com/uploads/allimg/2014-11/16-024509_590.jpg","http://www.baidu.com");

		/**
		 * 发送消息。
		 * 
		 * @param type
		 *            会话类型。
		 * @param targetId
		 *            目标 Id。根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组 Id 或聊天室
		 *            Id。
		 * @param content
		 *            消息内容。
		 * @param pushContent
		 *            push 内容，为空时不 push 信息。
		 * @param callback
		 *            发送消息的回调。
		 * @return
		 */
		RongIM.getInstance()
				.getRongIMClient()
				.sendMessage(Conversation.ConversationType.PRIVATE, "new",
						richContentMessage, null, null,
						new SendMessageCallback() {

							@Override
							public void onSuccess(Integer arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onError(Integer arg0, ErrorCode arg1) {
								// TODO Auto-generated method stub

							}
						});
	}

}
