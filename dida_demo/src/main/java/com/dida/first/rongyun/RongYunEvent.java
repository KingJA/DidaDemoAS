package com.dida.first.rongyun;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imkit.RongIM.OnReceiveUnreadCountChangedListener;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;

public class RongYunEvent implements UserInfoProvider,
		ConversationBehaviorListener{

	
	
	private static RongYunEvent mRongCloudInstance;
	private Context mContext;

	/**
	 * 构造方法。
	 * 
	 * @param context
	 *            上下文。
	 */
	private RongYunEvent(Context context) {
		mContext = context;
		initDefaultListener();
	}

	/**
	 * 初始化 RongCloud.
	 * 
	 * @param context
	 *            上下文。
	 */
	public static void init(Context context) {

		if (mRongCloudInstance == null) {

			synchronized (RongYunEvent.class) {

				if (mRongCloudInstance == null) {
					mRongCloudInstance = new RongYunEvent(context);
					Log.i("init", "new RongYunEvent(context)");
				}
			}
		}
	}

	/**
	 * RongIM.init(this) 后直接可注册的Listener。
	 */
	private void initDefaultListener() {
		RongIM.setUserInfoProvider(this, true);// 设置用户信息提供者。
		RongIM.setConversationBehaviorListener(this);// 设置会话界面操作的监听器。
		Log.i("initDefaultListener", "initDefaultListener");
	}

	/* ======================================================================== */
	/**
	 * 从本地数据库中按userID查找UserInfo
	 */
	@Override
	public UserInfo getUserInfo(String userId) {
		Log.i("getUserInfo", userId);
		if ("new".equals(userId)) {
			return new UserInfo(
					"new",
					"小心",
					Uri.parse("http://file-www.sioe.cn/201109/14/222211817.jpg"));
		} else if ("old".equals(userId)) {
			return new UserInfo(
					"old",
					"乔布斯",
					Uri.parse("http://img5q.duitang.com/uploads/item/201502/28/20150228102133_iJZRL.jpeg"));
		}
		return null;
	}

	/* ======================================================================== */
	/**
	 * 
	 * 当点击消息时执行 参数: context - 上下文。 view - 触发点击的 View。 message - 被点击的消息的实体信息。 返回:
	 * 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
	 */
	@Override
	public boolean onMessageClick(Context context, View view, Message message) {
		Log.i("onMessageClick", message.toString());

		/**
		 * demo 代码 开发者需替换成自己的代码。
		 */
		if (message.getContent() instanceof RichContentMessage) {
			RichContentMessage mRichContentMessage = (RichContentMessage) message
					.getContent();
			Log.d("Begavior", "extra:" + mRichContentMessage.getExtra());

		} else if (message.getContent() instanceof ImageMessage) {
			ImageMessage imageMessage = (ImageMessage) message.getContent();
			Intent intent = new Intent(context, PhotoActivity.class);

			intent.putExtra(
					"photo",
					imageMessage.getLocalUri() == null ? imageMessage
							.getRemoteUri() : imageMessage.getLocalUri());
			if (imageMessage.getThumUri() != null)
				intent.putExtra("thumbnail", imageMessage.getThumUri());

			context.startActivity(intent);
		}

		Log.d("Begavior",
				message.getObjectName() + ":" + message.getMessageId());

		return false;

	}

	/**
	 * 当点击链接消息时执行 参数: link - 被点击的链接。 返回: 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false,
	 * false 走融云默认处理方式。
	 */
	@Override
	public boolean onMessageLinkClick(String link) {
		Log.i("onMessageLinkClick", link);
		return false;
	}

	/**
	 * 当长按消息时执行。 参数: context - 上下文。 view - 触发点击的 View。 message - 被长按的消息的实体信息。
	 * 返回: 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
	 */
	@Override
	public boolean onMessageLongClick(Context arg0, View arg1, Message message) {
		Log.i("onMessageLongClick", message.toString());
		return false;
	}

	/**
	 * 当点击用户头像后执行。 参数: context - 上下文。 conversationType - 会话类型。 user - 被点击的用户的信息。
	 * 返回: 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
	 */
	@Override
	public boolean onUserPortraitClick(Context context,
			ConversationType conversationType, UserInfo user) {
		Log.i("onUserPortraitClick", user.getName());
		return false;
	}

	/**
	 * 当长按用户头像后执行。 参数: context - 上下文。 conversationType - 会话类型。 user - 被点击的用户的信息。
	 * 返回: 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
	 */
	@Override
	public boolean onUserPortraitLongClick(Context context,
			ConversationType conversationType, UserInfo user) {
		Log.i("onUserPortraitLongClick", user.getName());
		Log.i("onUserPortraitLongClick", user.getUserId());
		Log.i("onUserPortraitLongClick", user.getPortraitUri().toString());
		return true;
	}

	
}
