package com.iamyjx.looperdemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivityHandler extends Handler {
	private static final String TAG = MainActivityHandler.class.getSimpleName();
	private LooperHandler looperHandler;
	private MainActivity mainActivity;

	public MainActivityHandler(MainActivity mainActivity) {
		super();
		this.mainActivity = mainActivity;
	}

	@Override
	public void handleMessage(Message msg) {
		looperHandler = mainActivity.getLooperHandler();
		switch (msg.what) {
		case R.id.Main_1:
			Log.i(TAG, "执行 Main_1 Message,");
			// 添加message到LooperTherad的MQ中
			Message message = Message.obtain(looperHandler, R.id.LooperThread_1, 1, 2);
			message.sendToTarget();
			break;
		case R.id.Main_2:
			Log.i(TAG, "执行 Main_2 Message");
			// 添加任务到另一个Therad中
			looperHandler.sendEmptyMessage(R.id.LooperThread_2);
			break;
		case R.id.Main_3:
			Log.i(TAG, "执行 Main_3 Message");
			// 发送延迟执行的任务到LooperTherad的MQ中
			looperHandler.sendEmptyMessageDelayed(R.id.LooperThread_3, 10000);
			break;
		}
	}

}
