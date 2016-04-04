package com.dida.first.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanAddressList;

import java.util.List;

/**
 * Created by KingJA on 2016-1-18.
 */
public class AddressAdapter extends BaseLvGvAdapter<BeanAddressList.ResEntity.DeliveryAdressListEntity> {
    public AddressAdapter(List<BeanAddressList.ResEntity.DeliveryAdressListEntity> list, Activity activity) {
        super(list, activity);
    }

    @Override
    protected View baseGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(activity, R.layout.item_mine_personal_address, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvaddressname.setText(list.get(position).getReceiverName());
        viewHolder.tvaddressphone.setText(list.get(position).getMobileNo());
        viewHolder.tvaddressaddress.setText(list.get(position).getProvinceName()+list.get(position).getCityName()+list.get(position).getStrictName()+list.get(position).getDetailAddress());
        viewHolder.tvaddressdefault.setVisibility(list.get(position).getIsDefault()==1?View.VISIBLE:View.GONE);

        return convertView;
    }

    public void updateAddress(BeanAddressList.ResEntity.DeliveryAdressListEntity bean){
        if (bean.getIsDefault()==1){
            setNoDefault();
        }
        for (int i = 0; i < list.size(); i++) {

            if(bean.getDeliveryAddressId().equals(list.get(i).getDeliveryAddressId())){
                list.set(i,bean);
            }

        }
        this.notifyDataSetChanged();
    }
    private void setNoDefault(){
        for (BeanAddressList.ResEntity.DeliveryAdressListEntity bean :list) {
            bean.setIsDefault(0);
        }
    }

    public class ViewHolder {
        public final TextView tvaddressname;
        public final TextView tvaddressdefault;
        public final TextView tvaddressphone;
        public final TextView tvaddressaddress;
        public final View root;

        public ViewHolder(View root) {
            tvaddressname = (TextView) root.findViewById(R.id.tv_address_name);
            tvaddressdefault = (TextView) root.findViewById(R.id.tv_address_default);
            tvaddressphone = (TextView) root.findViewById(R.id.tv_address_phone);
            tvaddressaddress = (TextView) root.findViewById(R.id.tv_address_address);
            this.root = root;
        }
    }
}
