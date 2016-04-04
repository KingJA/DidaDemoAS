package com.dida.first.popupwindow;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanDetailPingouStore;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-15 下午1:38:47
 * @use
 */
public class PopupWindowParamStore extends PopupWindowBaseDown<List<BeanDetailPingouStore.ResEntity.CustomAttrsEntity>> {

    /**
     * @param parentView
     * @param activity
     * @param data
     */
    public PopupWindowParamStore(View parentView, Activity activity,
                                 List<BeanDetailPingouStore.ResEntity.CustomAttrsEntity> data) {
        super(parentView, activity, data);
        // TODO Auto-generated constructor stub
    }

    View popupView;

    /**
     * @param activity
     */


    @Override
    public View setPopupView(Activity activity) {
        popupView = View.inflate(activity, R.layout.popup_param, null);
        return popupView;
    }

    @Override
    public void initChildView() {
        ImageView iv_market_detail_param_close = (ImageView) popupView.findViewById(R.id.iv_market_detail_param_close);
        iv_market_detail_param_close.setOnClickListener(this);
        ListView mylv_market_detail_param = (ListView) popupView.findViewById(R.id.mylv_market_detail_param);
        mylv_market_detail_param.setAdapter(new MyAdapter(data));
    }

    @Override
    public void OnChildClick(View v) {
        switch (v.getId()) {
            case R.id.iv_market_detail_param_close:
                dismiss();
                break;

            default:
                break;
        }

    }

    private class MyAdapter extends BaseAdapter {

        private List<BeanDetailPingouStore.ResEntity.CustomAttrsEntity> list;

        public MyAdapter(List<BeanDetailPingouStore.ResEntity.CustomAttrsEntity> list) {

            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if (convertView == null) {
                convertView = View.inflate(activity, R.layout.item_market_detail_param, null);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tvmarketdetailparamName.setText(list.get(position).getAttributeName());
            viewHolder.tvmarketdetailparamContent.setText(list.get(position).getAttributeValue());
            return convertView;
        }

        private class ViewHolder {
            public final TextView tvmarketdetailparamName;
            public final TextView tvmarketdetailparamContent;
            public final View root;

            public ViewHolder(View root) {
                tvmarketdetailparamName = (TextView) root.findViewById(R.id.tv_market_detail_paramName);
                tvmarketdetailparamContent = (TextView) root.findViewById(R.id.tv_market_detail_paramContent);
                this.root = root;
            }
        }
    }

    @Override
    public int setPopupHeight() {
        // TODO Auto-generated method stub
        return screenHeight * 3 / 5;
    }


}
