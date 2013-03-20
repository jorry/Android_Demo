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

	// ��fragment�ж���һ���ӿڣ��ڱ�ʵ�֣�ʵ��fragment��activity�Ľ���
	public interface Fragment_1_interface {
		public void onBtnClick(View view);
	}

	// ��ϵͳ����Activity���ֵ�ʱ��,����ʵ����ÿ�������е�ÿ��fragment,
	// ����ÿ��fragment��onCreateView()������ȡ��ÿ��fragment����ͼ.
	// ϵͳ��fragment���ص���ͼֱ�Ӳ��뵽<fragment>��ǩ���ڵĵط�.
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// �˷������뷵��һ����ͼ
		return inflater.inflate(R.layout.fragment_1_view, container, false);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// ͨ���������ť���click�ص�����,ͨ��xml button��ǩ��onclick���ƹ�interface
		Button btn = (Button) activity.findViewById(R.id.test_Btn);
		//��Fragment��xml�ж���ʱ����Ϊactivity��û��ʼ�����ʱ�Ϳ�ʼfragment�Ļص�������findViewByIdȡ����
		//����XML�е�btnû�е������
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
		// ��֤������activityʵ�ֽӿ�
		try {
			mCallback = (Fragment_1_interface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

}
