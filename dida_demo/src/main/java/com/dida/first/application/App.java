package com.dida.first.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dida.first.R;
import com.dida.first.rongyun.RongYunEvent;
import com.dida.first.utils.CustomConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhy.base.loadandretry.LoadingAndRetryManager;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.model.Conversation.ConversationType;

public class App extends Application {
	private static Context context;
	private static int mainThreadId;
	private static Thread mainThread;
	private static Handler handler;
	private static SharedPreferences sp;
	public static RequestQueue mQueue;
	

	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
		//初始化LoadPager
		initLoadPager();
		//初始化Android-Universal-Image-Loader
		initImageLoader(getApplicationContext());
		// 初始化融云
		initRongYun();
		// 初始化图片加载
        initUniversalImageLoader();  
		// 清理图片缓存
		removeTempFromPref();
		// Context
		context = getApplicationContext();
		// mainThreadId
		mainThreadId = android.os.Process.myTid();
		// Thread-->object
		mainThread = Thread.currentThread();
		// Handler
		handler = new Handler();
		// SharedPreferences
		sp = getSharedPreferences(CustomConstants.APPLICATION_NAME,
				MODE_PRIVATE);
	}

	private void initLoadPager() {
		LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
		LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
		LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;
	}

	/**
	 * 初始化Android-Universal-Image-Loader
	 * @param applicationContext
	 */
	private void initImageLoader(Context applicationContext) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.threadPoolSize(3)//线程池内加载的数量
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs()
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024)) //建议内存设在5-10M,可以有比较好的表现
				.memoryCacheSize(5 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheFileCount(100) //缓存的文件数量
				.build();
		ImageLoader.getInstance().init(config);
	}

	public static RequestQueue getQueue(){
		if (mQueue==null) {
			mQueue = Volley.newRequestQueue(context);
		}
		return mQueue;
	}

	private void initRongYun() {
		/**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            sp = getSharedPreferences("config", Context.MODE_PRIVATE);
    		RongYunEvent.init(this);
    		//扩展功能只显示图片，相机
    		 ExtendProvider [] ep = {new ImageInputProvider(RongContext.getInstance()),new CameraInputProvider(RongContext.getInstance())};  
    		 //我需要让他在什么会话类型中的 bar 展示    
    		 RongIM.resetInputExtensionProvider(ConversationType.PRIVATE, ep);  
        }
	}

	/**
	 * 初始化Android-Universal-Image-Loader
	 */
	private void initUniversalImageLoader() {
		//创建默认的ImageLoader配置参数  
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
                .createDefault(this);  
          
        //Initialize ImageLoader with configuration.  
        ImageLoader.getInstance().init(configuration);
	}

	private void removeTempFromPref() {
		SharedPreferences sp = getSharedPreferences(
				CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
		sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
	}

	public static SharedPreferences getSP() {
		return sp;
	}
	public static boolean getHasLogin() {
		boolean hasLogin = sp.getBoolean(CustomConstants.HASLOGIN, false);
		return hasLogin;
	}

	public static Context getContext() {
		return context;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

	public static Thread getMainThread() {
		return mainThread;
	}

	public static Handler getHandler() {
		return handler;
	}
	 /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
