package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.dida.first.R;
import com.dida.first.utils.FrescoManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by KingJA on 2015-12-29.
 * 用户发起的拼购 图片列表
 */
public class ImgAdapter extends BaseLvGvAdapter<String> {
    private int width;
    private int height;

    public ImgAdapter(List<String> list, Activity activity) {
        super(list, activity);
    }


    @Override
    public void setImgSize(int width, int height) {
        super.setImgSize(width, height);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.single_siv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FrescoManager.display(list.get(position),viewHolder.ivsingle);
        return convertView;
    }

    public class ViewHolder {
        public final SimpleDraweeView ivsingle;
        public final View root;

        public ViewHolder(View root) {
            ivsingle = (SimpleDraweeView) root.findViewById(R.id.iv_single);
            this.root = root;
        }
    }
}
