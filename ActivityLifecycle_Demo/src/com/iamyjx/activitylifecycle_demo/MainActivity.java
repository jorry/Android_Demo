package com.iamyjx.activitylifecycle_demo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private int testState = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log("--onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(null!=savedInstanceState){
			//一般在此进行Activity状态的恢复，onRestoreInstanceState()在onCreate(),onStart()后才执行
			Log("savedInstanceState!=null and testState="+savedInstanceState.getInt("testState"));
		}
		testState += 1;
		
	}

	@Override
	protected void onStart() {
		Log("--onStart()");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log("--onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log("--onResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log("--onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log("--onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log("--onDestroy");
		this.finish();
		super.onDestroy();
	}

	// 当Activity开始 Stop 时，系统会调用 onSaveInstanceState()
	// 可将状态信息保存到Bundle
	// 警告:必须要调用onSaveInstanceState()方法的父类实现，这样默认的父类实现才能保存视图状态的信息。
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log("--onSaveInstanceState()");
		// 测试状态保存
		savedInstanceState.putInt("testState", testState);
		super.onSaveInstanceState(savedInstanceState);

	}

	// onRestoreInstanceState()方法会在onStart()方法之后执行。
	// onCreate()与onRestoreInstanceState()回调方法都接收到了同样的Bundle,都能进行Activity状态的恢复
	// 警告：与保存Activity状态一致，必须调用onRestoreInstanceState（）方法的父类实现，这样默认的父类实现才能获取视图状态的信息
	//onRestoreInstanceState() is called only when recreating activity after it was killed by the OS. 
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log("--onRestoreInstanceState()"+"int testState="+savedInstanceState.getInt("testState"));
		
	}

	private void Log(String text) {
		if (null == text)
			return;
		android.util.Log.i(TAG, text);
	}

}
