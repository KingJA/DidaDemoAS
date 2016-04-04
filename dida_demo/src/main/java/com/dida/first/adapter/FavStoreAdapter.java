package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanFavStore;
import com.dida.first.utils.UrlUtil;
import com.dida.first.view.KingJA_GradeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by KingJA on 2016-1-19.
 */
public class FavStoreAdapter extends BaseLvGvAdapter<BeanFavStore.ResEntity.ShopListEntity> {
    public FavStoreAdapter(List<BeanFavStore.ResEntity.ShopListEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=View.inflate(activity, R.layout.item_mine_stores,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.sdvstoreicon.setImageURI(UrlUtil.getUri(list.get(position).getThumb()));
        viewHolder.tvstorename.setText(list.get(position).getName());
        viewHolder.tvstorefance.setText(list.get(position).getFansCount()+" 位粉丝");
        viewHolder.kgvstoregrade.setGrade(list.get(position).getSellercredits());
        return convertView;
    }

    public class ViewHolder {
        public final SimpleDraweeView sdvstoreicon;
        public final TextView tvstorename;
        public final TextView tvstorefance;
        public final KingJA_GradeView kgvstoregrade;
        public final View root;

        public ViewHolder(View root) {
            sdvstoreicon = (SimpleDraweeView) root.findViewById(R.id.sdv_store_icon);
            tvstorename = (TextView) root.findViewById(R.id.tv_store_name);
            tvstorefance = (TextView) root.findViewById(R.id.tv_store_fance);
            kgvstoregrade = (KingJA_GradeView) root.findViewById(R.id.kgv_store_grade);
            this.root = root;
        }
    }
}
