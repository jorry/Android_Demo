package com.iamyjx.fragment_demo;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements Fragment_1.Fragment_1_interface   {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addFragment2Activity();
	}
	private void addFragment2Activity(){
		Fragment_1 frag1=new  Fragment_1();
		if(findViewById(R.id.frame_1)!=null){
			//代码添加
			getSupportFragmentManager().beginTransaction().add(R.id.frame_1,frag1).commit();
		}
	}
	@Override
	public void onBtnClick(View view) {
		TextView text=(TextView)findViewById(R.id.text_view);
		text.setText("点击了Fragment的按钮");
		
	}
}
