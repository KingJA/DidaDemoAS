package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.PrepayBean;
import com.dida.first.interfaces.OnCheckListener;
import com.dida.first.utils.UImageLoaderUitl;
import com.meg7.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class InvateAdapter extends BaseAdapter {

    private List<PrepayBean.ResEntity.FriendsListEntity> list;
    private Activity activity;
    private OnCheckListener onCheckListener;
    public InvateAdapter(List<PrepayBean.ResEntity.FriendsListEntity> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }
    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public List<PrepayBean.ResEntity.FriendsListEntity> getList(){
        return list;
    }

    public List<PrepayBean.ResEntity.FriendsListEntity> getData() {
        return list;
    }

    public List<PrepayBean.ResEntity.FriendsListEntity> getInvatedData() {
        List<PrepayBean.ResEntity.FriendsListEntity> invated=new ArrayList<PrepayBean.ResEntity.FriendsListEntity>();
        for (PrepayBean.ResEntity.FriendsListEntity friendsListEntity : list) {
           if (friendsListEntity.isChecked()){
               invated.add(friendsListEntity);
            }
        }
        return invated;
    }

    public void setData(List<PrepayBean.ResEntity.FriendsListEntity> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
    public void addData(List<PrepayBean.ResEntity.FriendsListEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = View
                    .inflate(activity, R.layout.item_invate_friends, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cbinvateitem.setChecked(list.get(position).isChecked());
        viewHolder.ivpublishname.setText(list.get(position).getUserName());
        UImageLoaderUitl.displayGvMidImage(list.get(position).getThumb(), viewHolder.ivpublishicon);
        viewHolder.cbinvateitem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //屏蔽非点击设置触发，如sheChecked(boolean);不然会因为复用导致状态错误
                if (!buttonView.isPressed())
                    return;
                onCheckListener.onCheck(list.get(position), isChecked);
            }
        });
        return convertView;
    }
    /**
     * 选中的数量
     * @return
     */
    public int getCheckedCount() {
        int count = 0;
        for (PrepayBean.ResEntity.FriendsListEntity invateFriend : list) {
            if (invateFriend.isChecked()) {
                count++;
            }
        }
        return count;
    }
    /**
     * 选中全部
     * @param checked
     */
    public void checkAll(boolean checked) {

        for (PrepayBean.ResEntity.FriendsListEntity invateFriend : list) {
            invateFriend.setChecked(checked);
        }
        this.notifyDataSetChanged();
    }
    public class ViewHolder {
        public final CircleImageView ivpublishicon;
        public final TextView ivpublishname;
        public final CheckBox cbinvateitem;
        public final View root;

        public ViewHolder(View root) {
            ivpublishicon = (CircleImageView) root.findViewById(R.id.iv_publish_icon);
            ivpublishname = (TextView) root.findViewById(R.id.iv_publish_name);
            cbinvateitem = (CheckBox) root.findViewById(R.id.cb_invate_item);
            this.root = root;
        }
    }
}
