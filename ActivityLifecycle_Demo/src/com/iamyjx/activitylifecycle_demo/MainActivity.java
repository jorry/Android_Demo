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
			//һ���ڴ˽���Activity״̬�Ļָ���onRestoreInstanceState()��onCreate(),onStart()���ִ��
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

	// ��Activity��ʼ Stop ʱ��ϵͳ����� onSaveInstanceState()
	// �ɽ�״̬��Ϣ���浽Bundle
	// ����:����Ҫ����onSaveInstanceState()�����ĸ���ʵ�֣�����Ĭ�ϵĸ���ʵ�ֲ��ܱ�����ͼ״̬����Ϣ��
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log("--onSaveInstanceState()");
		// ����״̬����
		savedInstanceState.putInt("testState", testState);
		super.onSaveInstanceState(savedInstanceState);

	}

	// onRestoreInstanceState()��������onStart()����֮��ִ�С�
	// onCreate()��onRestoreInstanceState()�ص����������յ���ͬ����Bundle,���ܽ���Activity״̬�Ļָ�
	// ���棺�뱣��Activity״̬һ�£��������onRestoreInstanceState���������ĸ���ʵ�֣�����Ĭ�ϵĸ���ʵ�ֲ��ܻ�ȡ��ͼ״̬����Ϣ
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
