/**
 * 
 */
package com.iamyjx.looperdemo;

import java.util.concurrent.CountDownLatch;

import android.os.Handler;
import android.os.Looper;

/**
 * ����һ��Looper�߳���
 * 
 * 1.ÿ���߳��������ֻ����һ��Looper��������һ��ThreadLocal
 * 
 * 2.Looper�ڲ���һ����Ϣ����MQ��loop()�������ú��߳̿�ʼ���ϴӶ�����ȡ����Ϣִ��
 * 
 * 3.Looperʹһ���̱߳��Looper�̡߳�
 * 
 * 4.һ���߳̿����ж��Handler������ֻ����һ��Looper��
 * 
 * 
 * @author iamyjx
 * 
 */
public class LooperThread extends Thread {
	// ����Looper�̵߳�MQ���������Message,����Message
	private LooperHandler handler;
	private Handler handler2;
	private MainActivityHandler mainActivityHandler;
	// ͨ���˼�ʱ��������getLooperHandler��
	private CountDownLatch conuntDownLatch;

	public LooperThread(MainActivityHandler mainActivityHandler) {
		super();
		this.mainActivityHandler = mainActivityHandler;
		// ��Ϊ0ʱ������������
		conuntDownLatch = new CountDownLatch(1);
	}

	/**
	 * ��α�֤���ص�handler�Ѿ�ʵ������,ʹ��
	 * 
	 * @return
	 */
	public LooperHandler getLooperHandler() {
		try {
			// ��countDownLatch��ûʵ��������������Ϊ0ʱ�Ų�����
			conuntDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handler;
	}

	/*
	 * ���Ǹ����run����������ͨThreadת��ΪLooper Thread
	 */
	@Override
	public void run() {
		super.run();
		// ����Threadת��ΪLooper�߳�
		Looper.prepare();
		// handler����ʱ�����һ��looper��Ĭ�ϵĹ��췽����������ǰ�̵߳�looper��Ҳ������set����?
		handler = new LooperHandler(mainActivityHandler);
		// ��������1��Ϊ0,�ſ���������֤ʵ��LooperHandler���ܵ���get����
		conuntDownLatch.countDown();
		handler2 = new Handler();
		// ��ʼMQ��ѭ������ִ����һ��������ȴ�ִ����һ������
		Looper.loop();
	}

}
