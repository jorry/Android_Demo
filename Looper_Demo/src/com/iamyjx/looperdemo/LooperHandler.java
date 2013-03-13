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
	 * ���Ǹ���Handler��handleMessage�ɽ���Message�Ĵ���
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case R.id.LooperThread_1:
			Log.i(TAG, "ִ��LooperThread_1");
			// ִ��LooperTherad_1�����񣬴��ݹ�����Message�������value
			// Message��public��Ĭ�Ϲ��췽������Ӧ��ͨ��Message.obtain()������Ϣ���л�ÿ���Ϣ�����Խ�ʡ��Դ
			Message message = Message.obtain();
			message.what = R.id.Main_2;
			message.setTarget(mainActivityHandler);
			message.sendToTarget();
			break;
		case R.id.LooperThread_2:
			//
			Log.i(TAG, "ִ��LooperThread_2");
			mainActivityHandler.sendEmptyMessage(R.id.Main_3);
			break;
		case R.id.LooperThread_3:
			Log.i(TAG, "ִ��LooperThread_3,���");
			break;
		}

	}

}
