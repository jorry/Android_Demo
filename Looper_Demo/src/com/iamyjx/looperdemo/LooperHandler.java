/**
 * 
 */
package com.iamyjx.looperdemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author iamyjx
 * 
 */
public class LooperHandler extends Handler {
	private static final String TAG = LooperHandler.class.getSimpleName();
	private MainActivityHandler mainActivityHandler;

	public LooperHandler(MainActivityHandler mainActivityHandler) {
		super();
		this.mainActivityHandler = mainActivityHandler;
	}

	/**
	 * 覆盖父类Handler的handleMessage可进行Message的处理
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case R.id.LooperThread_1:
			Log.i(TAG, "执行LooperThread_1");
			// 执行LooperTherad_1的任务，传递过来的Message中有相关value
			// Message有public的默认构造方法，但应该通过Message.obtain()来从消息池中获得空消息对象，以节省资源
			Message message = Message.obtain();
			message.what = R.id.Main_2;
			message.setTarget(mainActivityHandler);
			message.sendToTarget();
			break;
		case R.id.LooperThread_2:
			//
			Log.i(TAG, "执行LooperThread_2");
			mainActivityHandler.sendEmptyMessage(R.id.Main_3);
			break;
		case R.id.LooperThread_3:
			Log.i(TAG, "执行LooperThread_3,完毕");
			break;
		}

	}

}
