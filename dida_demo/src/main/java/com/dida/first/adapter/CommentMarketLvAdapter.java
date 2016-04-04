package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailMarket;
import com.dida.first.view.AbsListView.MyGridView;
import com.meg7.widget.CircleImageView;

import java.util.List;

/**
 * Created by KingJA on 2015-12-23.
 * 集市评论Adapter
 */
public class CommentMarketLvAdapter extends BaseLvGvAdapter<BeanDetailMarket.ResEntity.FeedBackVOsEntity> {
    public CommentMarketLvAdapter(List<BeanDetailMarket.ResEntity.FeedBackVOsEntity> list, Activity activity) {
        super(list, activity);
    }



    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=View.inflate(activity, R.layout.include_market_comment,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public class ViewHolder {
        public final CircleImageView civcommentmarketicon;
        public final TextView tvcommentmarketname;
        public final TextView tvcommentmarketcontent;
        public final MyGridView mgvcommentmarketimage;
        public final TextView tvcommentmarketafter;
        public final MyGridView mgvcommentmarketafterimage;
        public final TextView tvcommentmarketdate;
        public final TextView tvcommentmarketparam;
        public final View root;

        public ViewHolder(View root) {
            civcommentmarketicon = (CircleImageView) root.findViewById(R.id.civ_comment_market_icon);
            tvcommentmarketname = (TextView) root.findViewById(R.id.tv_comment_market_name);
            tvcommentmarketcontent = (TextView) root.findViewById(R.id.tv_comment_market_content);
            mgvcommentmarketimage = (MyGridView) root.findViewById(R.id.mgv_comment_market_image);
            tvcommentmarketafter = (TextView) root.findViewById(R.id.tv_comment_market_after);
            mgvcommentmarketafterimage = (MyGridView) root.findViewById(R.id.mgv_comment_market_afterimage);
            tvcommentmarketdate = (TextView) root.findViewById(R.id.tv_comment_market_date);
            tvcommentmarketparam = (TextView) root.findViewById(R.id.tv_comment_market_param);
            this.root = root;
        }
    }
}
