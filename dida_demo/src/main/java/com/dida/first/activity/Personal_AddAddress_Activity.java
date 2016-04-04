package com.dida.first.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.callback.JsonCallBack;
import com.dida.first.entity.BeanAddressList;
import com.dida.first.utils.CheckUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UrlUtil;
import com.dida.first.wheelview.ChangeAddressDialog;
import com.dida.first.wheelview.ChangeAddressDialog.OnAddressCListener;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Request;

/**
 * @author KingJA
 * @data 2015-9-22 下午1:51:09
 * @use
 * 
 */
public class Personal_AddAddress_Activity extends BackTitleActivity {

	private TextView tv_add_address_province;
	private EditText et_add_address_address;
	private EditText et_add_address_phone;
	private EditText et_add_address_name;
	private CheckBox cb_add_address_default;
	private String name;
	private String phone;
	private String province;
	private String address;
	private int ifDefault;

	@Override
	public View setView() {
		view = View.inflate(this, R.layout.activity_mine_personal_add_address,
				null);
		return view;
	}

	@Override
	public void initView() {
		et_add_address_name = (EditText) view
				.findViewById(R.id.et_add_address_name);
		et_add_address_phone = (EditText) view
				.findViewById(R.id.et_add_address_phone);
		et_add_address_address = (EditText) view
				.findViewById(R.id.et_add_address_address);
		tv_add_address_province = (TextView) view
				.findViewById(R.id.tv_add_address_province);
		cb_add_address_default = (CheckBox) view
				.findViewById(R.id.cb_add_address_default);

	}

	@Override
	public void initDoNet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initEvent() {
		tv_add_address_province.setOnClickListener(this);

	}

	@Override
	public void initData() {
		setBackTitle("新增收货地址");
		setOnTextClickListener("完成", new OnTextClickListener() {

			@Override
			public void onTextClick() {
				name = et_add_address_name.getText().toString().trim();
				phone = et_add_address_phone.getText().toString().trim();
				address = et_add_address_address.getText().toString().trim();
				ifDefault = cb_add_address_default.isChecked()?1:0;
				if (checkAll(name,phone,province,address)){
					loadAddress();
				}
			}
		});

	}

	private void loadAddress() {

	}

	private boolean checkAll(String name, String phone, String province, String address) {
		if(!CheckUtil.isEmpty(name,"请填写收货人")&& CheckUtil.checkPhoneFormat(phone)&&!CheckUtil.isEmpty(province,"请选择省市区")&&!CheckUtil.isEmpty(address,"请填写详细地址")){
			return true;
		}
		return false;
	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
			case R.id.tv_add_address_province:
			ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
					this);
				mChangeAddressDialog.setAddress("浙江", "温州", "鹿城区", "330000", "330300", "330302");
			mChangeAddressDialog.show();
			mChangeAddressDialog.setAddresskListener(new OnAddressCListener() {


				@Override
				public void onClick(String province, String city, String area, String provinceId, String cityId, String areaId) {
					tv_add_address_province.setText(province+ city + area);
					province=provinceId+"*"+cityId+"*"+areaId;
				}
			});
			break;

		default:
			break;
		}
	}

	@Override
	public void setBackClick() {
		finish();

	}

	private void loadNet(String userId, int page) {
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
					}
				});
	}
}
