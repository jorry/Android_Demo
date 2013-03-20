package com.iamyjx.fragment_demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class Fragment_1 extends Fragment {
	Fragment_1_interface mCallback;

	// 在fragment中定义一个接口，在别处实现，实现fragment与activity的交互
	public interface Fragment_1_interface {
		public void onBtnClick(View view);
	}

	// 在系统创建Activity布局的时候,他会实例化每个布局中的每个fragment,
	// 调用每个fragment的onCreateView()方法来取回每个fragment的视图.
	// 系统把fragment返回的视图直接插入到<fragment>标签所在的地方.
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// 此方法必须返回一个视图
		return inflater.inflate(R.layout.fragment_1_view, container, false);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// 通过代码给按钮添加click回调方法,通过xml button标签的onclick会绕过interface
		Button btn = (Button) activity.findViewById(R.id.test_Btn);
		//若Fragment在xml中定义时，因为activity还没初始化完成时就开始fragment的回调，所以findViewById取不到
		//所以XML中的btn没有点击方法
		FrameLayout f=(FrameLayout)activity.findViewById(R.id.frame_1);
		if (null != btn) {
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mCallback.onBtnClick(v);
				}
			});
		}
		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		// 保证必须在activity实现接口
		try {
			mCallback = (Fragment_1_interface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

}
