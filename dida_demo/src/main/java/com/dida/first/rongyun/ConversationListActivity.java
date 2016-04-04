package com.dida.first.rongyun;



import com.dida.first.R;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.OnReceiveUnreadCountChangedListener;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConversationListActivity extends FragmentActivity implements OnClickListener,OnReceiveMessageListener {
	/*消息提示音*/
	public static boolean messageVoice =true;
	private static SoundPool soundPool;// 声明一个SoundPool
	private int music;// 定义一个整型用load()来设置suondID
	private LinearLayout ll_top_chat_switch;
	private TextView tv_top_chat_friend;
	private TextView tv_top_chat_msg;
	private FrameLayout fl_chat_friend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conversation_list);
		/**
		 * 接受信息监听器
		 */
		 RongIM.getInstance().getRongIMClient().setOnReceiveMessageListener(this);
		initVoice();
		initView();
		initEvent();
		
		
	}
	/**
	 * 
	 */
	private void initEvent() {
		ll_top_chat_switch.setOnClickListener(this);
		tv_top_chat_msg.setOnClickListener(this);
		tv_top_chat_friend.setOnClickListener(this);
		
	}
	private void initView() {
		fl_chat_friend = (FrameLayout) findViewById(R.id.fl_chat_friend);
		tv_top_chat_msg = (TextView) findViewById(R.id.tv_top_chat_msg);
		tv_top_chat_friend = (TextView) findViewById(R.id.tv_top_chat_friend);
		ll_top_chat_switch = (LinearLayout) findViewById(R.id.ll_top_chat_switch);
		
	}

	/* ======================================================================== */
	/**
	 * 未读信息音效
	 */
	private void initVoice() {
		/**
		 * 1.同时播放数据流的最大个数如果是1则每次覆盖同一个音效
		 * 2.数据流类型，在这里标识为系统声音，除此之外还有AudioManager.STREAM_RING以及AudioManager.STREAM_MUSIC等，系统会根据不同的声音为其标志不同的优先级和缓冲区
		 * 3.声音质量，品质越高，声音效果越好，但耗费更多的系统资源
		 */
		soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
		/**
		 *  1.上下文	2.资源文件	3.音乐的优先级
		 */
		music = soundPool.load(this, R.raw.key_sound, 1);
	}

	@Override
	public boolean onReceived(Message message, int left) {
		Log.i("onReceived", "剩余信息数="+left);
		soundPool.play(music, 1, 1, 0, 0, 1);
		return false;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_top_chat_msg:
			fl_chat_friend.setVisibility(View.GONE);
			ll_top_chat_switch.setBackgroundResource(R.drawable.chat_switch_msg);
			tv_top_chat_msg.setTextColor(getResources().getColor(R.color.black));
			tv_top_chat_friend.setTextColor(getResources().getColor(R.color.white));
			break;
		case R.id.tv_top_chat_friend:
			fl_chat_friend.setVisibility(View.VISIBLE);
			ll_top_chat_switch.setBackgroundResource(R.drawable.chat_switch_friend);
			tv_top_chat_msg.setTextColor(getResources().getColor(R.color.white));
			tv_top_chat_friend.setTextColor(getResources().getColor(R.color.black));
			break;

		default:
			break;
		}
		
	}

}
