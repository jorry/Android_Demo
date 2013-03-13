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
			Log.i(TAG, "ִ�� Main_1 Message,");
			// ���message��LooperTherad��MQ��
			Message message = Message.obtain(looperHandler, R.id.LooperThread_1, 1, 2);
			message.sendToTarget();
			break;
		case R.id.Main_2:
			Log.i(TAG, "ִ�� Main_2 Message");
			// ���������һ��Therad��
			looperHandler.sendEmptyMessage(R.id.LooperThread_2);
			break;
		case R.id.Main_3:
			Log.i(TAG, "ִ�� Main_3 Message");
			// �����ӳ�ִ�е�����LooperTherad��MQ��
			looperHandler.sendEmptyMessageDelayed(R.id.LooperThread_3, 10000);
			break;
		}
	}

}
