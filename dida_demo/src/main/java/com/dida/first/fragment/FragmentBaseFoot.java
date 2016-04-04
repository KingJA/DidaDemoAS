/**
 * 
 */
package com.dida.first.fragment;

import com.lidroid.xutils.view.annotation.event.OnChildClick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * @author		KingJA 
 * @data		2015-9-24 下午3:21:24 
 * @use			
 *
 */
public abstract class FragmentBaseFoot extends Fragment implements OnClickListener{
	protected FragmentActivity context;
	protected View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = initCreateView();
		return view;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initFragmentView();
		initFragmentNet();
		initFragmentEvent();
		initFragmentData();
		
	}
	public abstract View initCreateView();
	public abstract void initFragmentView();
	public abstract void initFragmentNet();
	public abstract void initFragmentEvent();
	public abstract void initFragmentData();
	public abstract void onChildClick(View v);

	@Override
	public void onClick(View v) {
		onChildClick(v);
		
	}
}
