package com.dida.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dida.first.R;
import com.dida.first.adapter.AddressAdapter;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanAddressList;
import com.dida.first.utils.ActivityUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @author KingJA
 * @data 2015-9-21 下午2:30:35
 * @use
 */
public class Personal_Address_Activity extends BackTitleActivity implements OnItemClickListener {

    private ListView lv_single;
    private List<BeanAddressList.ResEntity.DeliveryAdressListEntity> deliveryAdressList = new ArrayList<>();
    private AddressAdapter addressAdapter;
    private static int RES_EDIT_ADDRESS=1;

    @Override
    public View setView() {
        view = View.inflate(this, R.layout.single_lv, null);
        return view;
    }

    @Override
    public void initView() {
        lv_single = (ListView) view.findViewById(R.id.lv_single);

    }

    @Override
    public void initDoNet() {
        doNetAddress("fb9a38d82cd3405a9b60ec54cdb5ecdf", 1);

    }

    private void doNetAddress(String userId, int page) {
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_ADDRESS_LIST))
                .addParams("userId", userId)
                .addParams("page", String.valueOf(page))
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanAddressList>(BeanAddressList.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showMyToast("服务器君在开小差！");
                    }

                    @Override
                    public void onResponse(BeanAddressList bean) {
                        deliveryAdressList = bean.getRes().getDeliveryAdressList();
                        addressAdapter.setData(deliveryAdressList);
                    }
                });
    }

    @Override
    public void initEvent() {
        addressAdapter = new AddressAdapter(deliveryAdressList, this);
        lv_single.setOnItemClickListener(this);

    }

    @Override
    public void initData() {
        lv_single.setAdapter(addressAdapter);
        setBackTitle("收货地址管理");
        setOnTextClickListener("添加", new OnTextClickListener() {

            @Override
            public void onTextClick() {
                ToastUtil.showMyToast("添加");
                Intent intent = new Intent(Personal_Address_Activity.this, Personal_AddAddress_Activity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setBackClick() {
        finish();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        BeanAddressList.ResEntity.DeliveryAdressListEntity itemAtPosition = (BeanAddressList.ResEntity.DeliveryAdressListEntity) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ADDRESS_DETAIL",itemAtPosition);
        ActivityUtil.goActivityForResultWithBundle(Personal_Address_Activity.this, Detail_Address_Activity.class,bundle,RES_EDIT_ADDRESS);
//        ActivityUtil.goActivityWithBundle(Personal_Address_Activity.this, Detail_Address_Activity.class,bundle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (data!=null){
            if (requestCode==RES_EDIT_ADDRESS){
//                BeanAddressList.ResEntity.DeliveryAdressListEntity result_address = (BeanAddressList.ResEntity.DeliveryAdressListEntity) data.getSerializableExtra("ADDRESS_DETAIL");
//                addressAdapter.updateAddress(result_address);
                doNetAddress("fb9a38d82cd3405a9b60ec54cdb5ecdf", 1);
            }
//        }
    }
}
