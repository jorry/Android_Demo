package com.iamyjx.looperdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * ���̱߳������Looper�߳�,ֻ���Handler�Ϳɽ���MQ�Ĵ���
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

		// �Զ��󶨵������߳�,Ϊ�δ�this��
		mainActivityHandler = new MainActivityHandler(this);
		// ������һ���߳�
		looperTherad = new LooperThread(mainActivityHandler);
		looperTherad.start();
		looperHandler = looperTherad.getLooperHandler();
		// Looper �߳�ͨ��Handler���д���MQ
		// ����Message,�ж��ַ���
		mainActivityHandler.sendEmptyMessage(R.id.Main_1);
	}

	public LooperHandler getLooperHandler() {
		return looperHandler;
	}

}
