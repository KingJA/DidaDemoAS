package com.dida.first.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.entity.BeanParam;
import com.dida.first.holder.BaseHolder;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-5.
 */
public class Publish_Head_Holder extends BaseHolder{

    protected Context context;
    public Publish_Head_Holder(Context context) {
        super();
        this.context=context;
    }

    @Override
    public View initView() {
        view= UIUtils.inflate(R.layout.holder_publish_head);
        return view;
    }

    @Override
    public void refreshView() {
        MyListView mylv_publish_head = (MyListView) view.findViewById(R.id.mylv_publish_head);
        List<BeanParam> params=new ArrayList<BeanParam>();
        for (int i = 0; i < 3; i++) {
            params.add(new BeanParam("",""));
        }
        mylv_publish_head.setAdapter(new MyPublishHeadAdapter(params));

    }
    class MyPublishHeadAdapter extends MyBaseListViewAdapter<BeanParam>{

        public MyPublishHeadAdapter(List<BeanParam> list) {
            super(list);
        }

        @Override
        public View getItemView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View
                        .inflate(context, R.layout.item_publish_head, null);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
        }
    }
}
