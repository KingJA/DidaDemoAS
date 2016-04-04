/**
 *
 */
package com.dida.first.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanAddressList;
import com.dida.first.entity.BeanSimple;
import com.dida.first.utils.UrlUtil;
import com.dida.first.wheelview.ChangeAddressDialog;
import com.dida.first.wheelview.ChangeAddressDialog.OnAddressCListener;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * @author KingJA
 * @data 2015-9-21 下午3:49:44
 * @use
 */
public class Detail_Address_Activity extends BackTitleActivity {

    private static final String TAG = "Detail_Address_Activity";

    private TextView tv_address_update_area;
    private EditText et_address_update_address;
    private EditText et_address_update_name;
    private EditText et_address_update_phone;
    private RelativeLayout rl_address_update_area;

    private BeanAddressList.ResEntity.DeliveryAdressListEntity address_detail;
    private CheckBox cb_address_update_default;
    private String totelArea;
    private String address;
    private String name;
    private String phone;
    private String ifDefault;
    private Button btn_address_update_delete;
    private Button btn_address_update_confirm;
    private String provinceName;
    private String cityName;
    private String strictName;
    private String provinceId;
    private String cityId;
    private String strictId;
    private ChangeAddressDialog mChangeAddressDialog;


    @Override
    public View setView() {
        view = View.inflate(this, R.layout.activity_mine_personal_address_edit, null);
        return view;
    }

    @Override
    public void initView() {
        btn_address_update_delete = (Button) view.findViewById(R.id.btn_address_update_delete);
        btn_address_update_confirm = (Button) view.findViewById(R.id.btn_address_update_confirm);
        tv_address_update_area = (TextView) view.findViewById(R.id.tv_address_update_area);
        et_address_update_address = (EditText) view.findViewById(R.id.et_address_update_address);
        et_address_update_name = (EditText) view.findViewById(R.id.et_address_update_name);
        et_address_update_phone = (EditText) view.findViewById(R.id.et_address_update_phone);
        cb_address_update_default = (CheckBox) view.findViewById(R.id.cb_address_update_default);
        rl_address_update_area = (RelativeLayout) view.findViewById(R.id.rl_address_update_area);

    }

    @Override
    public void initDoNet() {
        address_detail = (BeanAddressList.ResEntity.DeliveryAdressListEntity) getIntent().getSerializableExtra("ADDRESS_DETAIL");

    }

    @Override
    public void initEvent() {
        rl_address_update_area.setOnClickListener(this);
        btn_address_update_delete.setOnClickListener(this);
        btn_address_update_confirm.setOnClickListener(this);

    }

    @Override
    public void initData() {
        provinceName = address_detail.getProvinceName();
        cityName = address_detail.getCityName();
        strictName = address_detail.getStrictName();
        provinceId = address_detail.getProvinceId();
        cityId = address_detail.getCityId();
        strictId = address_detail.getStrictId();
        tv_address_update_area.setText(provinceName + cityName + strictName);
        et_address_update_address.setText(address_detail.getDetailAddress());
        et_address_update_name.setText(address_detail.getReceiverName());
        et_address_update_phone.setText(address_detail.getMobileNo());
        cb_address_update_default.setChecked(address_detail.getIsDefault()==1?true:false);
        totelArea = provinceId + "*" + cityId + "*" + strictId;

        et_address_update_address.setSelection(et_address_update_address.getText().length());
        setBackTitle("修改地址");

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address_update_area:
                mChangeAddressDialog = new ChangeAddressDialog(this);
                mChangeAddressDialog.setAddress(provinceName, cityName, strictName, provinceId, cityId, strictId);
                mChangeAddressDialog.show();
                mChangeAddressDialog
                        .setAddresskListener(new OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area, String provinceId, String cityId, String areaId) {
                                Detail_Address_Activity.this.provinceName = province;
                                Detail_Address_Activity.this.cityName = city;
                                Detail_Address_Activity.this.strictName = area;
                                Detail_Address_Activity.this.provinceId = provinceId;
                                Detail_Address_Activity.this.cityId = cityId;
                                Detail_Address_Activity.this.strictId = areaId;
                                tv_address_update_area.setText(province + city  + area);
                                totelArea = provinceId + "*" + cityId + "*" + areaId;
                            }

                        });

                break;
            case R.id.btn_address_update_delete:
                break;
            case R.id.btn_address_update_confirm:
                address=et_address_update_address.getText().toString().trim();
                 name=et_address_update_name.getText().toString().trim();
                phone=et_address_update_phone.getText().toString().trim();
                ifDefault=cb_address_update_default.isChecked()?"1":"0";
                updateAddress("fb9a38d82cd3405a9b60ec54cdb5ecdf",address_detail.getDeliveryAddressId(), totelArea, address, name, phone, ifDefault);
                break;
            default:
                break;
        }

    }

    private void updateAddress(String userId,String deliveryAddressId, String area, String detailAddress, String receiverName, String mobileNo, String isDefault) {
        Log.i(TAG, " deliveryAddressId: "+deliveryAddressId+" area: "+area+" detailAddress: "+detailAddress+" receiverName: "+receiverName+" mobileNo: "+mobileNo+" isDefault: "+isDefault);
        mDialogProgress.show();
        OkHttpUtils
                .post()
                .url(UrlUtil.getIUrl(UrlUtil.InterfaceName.I_UPDATE_ADDRESS))
                .addParams("userId", userId)
                .addParams("deliveryAddressId", deliveryAddressId)
                .addParams("receiverName", receiverName)
                .addParams("area", area)
                .addParams("detailAddress", detailAddress)
                .addParams("mobileNo", mobileNo)
                .addParams("isDefault", isDefault)
                .addParams("app", "1")
                .build()
                .execute(new JsonCallBack<BeanSimple>(BeanSimple.class) {
                    @Override
                    public void onError(Request request, Exception e) {
                        mDialogProgress.dismiss();

                    }

                    @Override
                    public void onResponse(BeanSimple bean) {
                        mDialogProgress.dismiss();
                        resultUpdate();
                    }
                });
    }


    @Override
    public void setBackClick() {
        finish();

    }

    private void resultUpdate() {
        Intent intent = new Intent();
        setResult(10, intent);
        finish();
    }
}
