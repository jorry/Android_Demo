package com.iamyjx.looperdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * 主线程本身就是Looper线程,只需绑定Handler就可进行MQ的处理
 * 
 * @author iamyjx
 * 
 */
public class MainActivity extends Activity {
	private MainActivityHandler mainActivityHandler;
	private LooperThread looperTherad;
	private LooperHandler looperHandler;
	private static final Long start = System.currentTimeMillis();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 自动绑定到所在线程,为何传this？
		mainActivityHandler = new MainActivityHandler(this);
		// 启动另一个线程
		looperTherad = new LooperThread(mainActivityHandler);
		looperTherad.start();
		looperHandler = looperTherad.getLooperHandler();
		// Looper 线程通过Handler进行处理MQ
		// 发送Message,有多种方法
		mainActivityHandler.sendEmptyMessage(R.id.Main_1);
	}

	public LooperHandler getLooperHandler() {
		return looperHandler;
	}

}
