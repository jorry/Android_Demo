/**
 * 
 */
package com.iamyjx.looperdemo;

import java.util.concurrent.CountDownLatch;

import android.os.Handler;
import android.os.Looper;

/**
 * 创建一个Looper线程类
 * 
 * 1.每个线程有且最多只能有一个Looper对象，它是一个ThreadLocal
 * 
 * 2.Looper内部有一个消息队列MQ，loop()方法调用后线程开始不断从队列中取出消息执行
 * 
 * 3.Looper使一个线程变成Looper线程。
 * 
 * 4.一个线程可以有多个Handler，但是只能有一个Looper！
 * 
 * 
 * @author iamyjx
 * 
 */
public class LooperThread extends Thread {
	// 处理Looper线程的MQ，包括添加Message,处理Message
	private LooperHandler handler;
	private Handler handler2;
	private MainActivityHandler mainActivityHandler;
	// 通过此计时器来阻塞getLooperHandler，
	private CountDownLatch conuntDownLatch;

	public LooperThread(MainActivityHandler mainActivityHandler) {
		super();
		this.mainActivityHandler = mainActivityHandler;
		// 当为0时，将不再阻塞
		conuntDownLatch = new CountDownLatch(1);
	}

	/**
	 * 如何保证返回的handler已经实例化？,使用
	 * 
	 * @return
	 */
	public LooperHandler getLooperHandler() {
		try {
			// 若countDownLatch还没实例化将阻塞，仅为0时才不阻塞
			conuntDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handler;
	}

	/*
	 * 覆盖父类的run方法，将普通Thread转变为Looper Thread
	 */
	@Override
	public void run() {
		super.run();
		// 将此Thread转变为Looper线程
		Looper.prepare();
		// handler创建时会关联一个looper，默认的构造方法将关联当前线程的looper，也可以用set方法?
		handler = new LooperHandler(mainActivityHandler);
		// 计数器由1减为0,放开阻塞，保证实例LooperHandler后方能调用get方法
		conuntDownLatch.countDown();
		handler2 = new Handler();
		// 开始MQ的循环，当执行完一任务继续等待执行下一次任务
		Looper.loop();
	}

}
