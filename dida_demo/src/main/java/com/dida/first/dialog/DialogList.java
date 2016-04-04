package com.dida.first.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.view.AbsListView.MyListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015-11-18.
 */
public class DialogList extends DialogBaseAlert implements AdapterView.OnItemClickListener {

    private MyListView mlv_dialog_list;
    private OnListItemClickListener onListItemClickListener;
    private List<String> expressList = new ArrayList<String>();
    private String[] expressArr = {"顺风快递", "申通快递", "中通快递", "汇通快递", "EMS", "韵达快递"};

    public DialogList(Context context) {
        super(context);
        show();
    }

    @Override
    public void initView() {
        setContentView(R.layout.dialog_list);
        mlv_dialog_list = (MyListView) findViewById(R.id.mlv_dialog_list);

    }

    @Override
    public void initNet() {
        for (int i = 0; i < new Random().nextInt(3) + 3; i++) {
            expressList.add(expressArr[new Random().nextInt(3) + 2]);
        }

    }

    @Override
    public void initEvent() {
        MyAdapter myAdapter = new MyAdapter(expressList);
        mlv_dialog_list.setAdapter(myAdapter);
        mlv_dialog_list.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void childClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        String express = (String) parent.getItemAtPosition(position);
        onListItemClickListener.onListItemClick(express);
    }

    class MyAdapter extends MyBaseListViewAdapter<String> {

        public MyAdapter(List<String> list) {
            super(list);
        }

        @Override
        public View getItemView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.view_tv, null);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
            tv_item.setText(list.get(position));
            return view;
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(String express);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }
}
