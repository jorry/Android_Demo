package com.iamyjx.activitylifecycle_demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log("--onCreate()");
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
		super.onDestroy();
	}

	private void Log(String text) {
		if (null == text)
			return;
		android.util.Log.i(TAG, text);
	}

}
