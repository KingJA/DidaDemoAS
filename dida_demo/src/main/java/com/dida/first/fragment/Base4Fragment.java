package com.dida.first.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public abstract class Base4Fragment extends Fragment implements OnClickListener{
	public View view;
	public Context context;
	public FragmentManager fragmentManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context=getActivity();
		fragmentManager=getActivity().getSupportFragmentManager();
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=initFragmentView();
		return view;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		initFragmentData();
		super.onActivityCreated(savedInstanceState);
	}
	public abstract View initFragmentView();
	public abstract void initFragmentData();
	public abstract void doNet();

}
