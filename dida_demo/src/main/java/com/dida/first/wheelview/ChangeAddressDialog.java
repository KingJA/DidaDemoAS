package com.dida.first.wheelview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dida.first.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author KingJA
 * @data 2015-9-22 下午2:36:00
 * @use 省市区地址选择器
 */
public class ChangeAddressDialog extends Dialog implements android.view.View.OnClickListener {

    private static final String TAG = "ChangeAddressDialog";
    private WheelView wvProvince;
    private WheelView wvCitys;
    private WheelView wvAreas;
    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;
    private Context context;
    private JSONObject mJsonObj;
    private String[] mProvinceDatas;
    private String[] mProvinceIds;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

    private Map<String, String[]> mCitisIdsMap = new HashMap<String, String[]>();
    private Map<String, String[]> mAreaIdsMap = new HashMap<String, String[]>();
    private ArrayList<String> arrProvinces = new ArrayList<String>();
    private ArrayList<String> arrCitys = new ArrayList<String>();
    private ArrayList<String> arrAreas = new ArrayList<String>();

    private ArrayList<String> arrProvinceIds = new ArrayList<String>();
    private ArrayList<String> arrCityIds = new ArrayList<String>();
    private ArrayList<String> arrAreaIds = new ArrayList<String>();

    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter areaAdapter;
    private String strProvinceId = "330000";
    private String strCityId = "330300";
    private String strAreaId = "330302";
    private OnAddressCListener onAddressCListener;
    private int maxsize = 24;
    private int minsize = 14;
    private String strProvince;
    private String strCity;
    private String strArea;


    public ChangeAddressDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_changeaddress);

        wvProvince = (WheelView) findViewById(R.id.wv_address_province);
        wvCitys = (WheelView) findViewById(R.id.wv_address_city);
        wvAreas = (WheelView) findViewById(R.id.wv_address_area);

        lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
        lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

        lyChangeAddress.setOnClickListener(this);
        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        initJsonData();
        initDatas();
        initProvinces();
        provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
        wvProvince.setVisibleItems(5);
        wvProvince.setViewAdapter(provinceAdapter);
        wvProvince.setCurrentItem(getProvinceItem(strProvince));

        initCitys(mCitisDatasMap.get(strProvince));
        initCityIds(mCitisIdsMap.get(strProvince));
        cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
        wvCitys.setVisibleItems(5);
        wvCitys.setViewAdapter(cityAdapter);
        wvCitys.setCurrentItem(getCityItem(strCity));

        initAreas(mAreaDatasMap.get(strCity));
        initAreaIds(mAreaIdsMap.get(strCity));
        areaAdapter = new AddressTextAdapter(context, arrAreas, getAresItem(strArea), maxsize, minsize);
        wvAreas.setVisibleItems(5);
        wvAreas.setViewAdapter(areaAdapter);
        wvAreas.setCurrentItem(getAresItem(strArea));

        wvProvince.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                strProvince = currentText;
                strProvinceId = arrProvinceIds.get(wheel.getCurrentItem());
                setTextviewSize(currentText, provinceAdapter);
                initCitys(mCitisDatasMap.get(currentText));
                initCityIds(mCitisIdsMap.get(currentText));
                cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
                wvCitys.setVisibleItems(5);
                wvCitys.setViewAdapter(cityAdapter);
                wvCitys.setCurrentItem(0);
                initAreas(mAreaDatasMap.get(strCity));
                initAreaIds(mAreaIdsMap.get(strCity));
                areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
                wvAreas.setVisibleItems(5);
                wvAreas.setViewAdapter(areaAdapter);
                wvAreas.setCurrentItem(0);
            }
        });

        wvProvince.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, provinceAdapter);
            }
        });

        wvCitys.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                strCity = currentText;
                strCityId = arrCityIds.get(wheel.getCurrentItem());
                setTextviewSize(currentText, cityAdapter);

                initAreas(mAreaDatasMap.get(currentText));
                initAreaIds(mAreaIdsMap.get(currentText));
                areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
                wvAreas.setVisibleItems(5);
                wvAreas.setViewAdapter(areaAdapter);
                wvAreas.setCurrentItem(0);

            }
        });

        wvCitys.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, cityAdapter);
            }
        });
        wvAreas.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
                strArea = currentText;
                strAreaId = arrAreaIds.get(wheel.getCurrentItem());
                setTextviewSize(currentText, areaAdapter);
                Log.i(TAG, "wvAreas onChanged: ");
            }
        });

        wvAreas.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, areaAdapter);
            }
        });
    }


    private class AddressTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(24);
            } else {
                textvew.setTextSize(14);
            }
        }
    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(strProvince, strCity, strArea, strProvinceId, strCityId, strAreaId);
            }
        } else if (v == btnCancel) {

        } else if (v == lyChangeAddressChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        public void onClick(String province, String city, String area, String provinceId, String cityId, String areaId);
    }

    /**
     * 从文件中读取地址数据
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("city_id.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    private void initDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            mProvinceDatas = new String[jsonArray.length()];
            mProvinceIds = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);
                String province = jsonP.getString("p");
                String provinceId = jsonP.getString("pid");
                mProvinceDatas[i] = province;
                mProvinceIds[i] = provinceId;
                JSONArray jsonCs = null;
                try {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                String[] mCitiesIds = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");
                    String cityId = jsonCity.getString("nid");
                    mCitiesDatas[j] = city;
                    mCitiesIds[j] = cityId;
                    JSONArray jsonAreas = null;
                    try {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    } catch (Exception e) {
                        continue;
                    }
                    String[] mAreasDatas = new String[jsonAreas.length()];
                    String[] mAreasIds = new String[jsonAreas.length()];
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("s");
                        String areaId = jsonAreas.getJSONObject(k).getString("sid");
                        mAreasDatas[k] = area;
                        mAreasIds[k] = areaId;
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                    mAreaIdsMap.put(city, mAreasIds);
                }
                mCitisDatasMap.put(province, mCitiesDatas);
                mCitisIdsMap.put(province, mCitiesIds);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    /**
     * 初始化省会
     */
    public void initProvinces() {
        int length = mProvinceDatas.length;
        for (int i = 0; i < length; i++) {
            arrProvinces.add(mProvinceDatas[i]);
            arrProvinceIds.add(mProvinceIds[i]);
        }
    }

    /**
     * 根据省会，生成该省会的所有城市
     *
     * @param citys
     */
    public void initCitys(String[] citys) {
        if (citys != null) {
            arrCitys.clear();
            for (int i = 0; i < citys.length; i++) {
                arrCitys.add(citys[i]);
            }
        } else {
            String[] city = mCitisDatasMap.get("浙江省");
            arrCitys.clear();
            for (int i = 0; i < city.length; i++) {
                arrCitys.add(city[i]);
            }
        }
        if (arrCitys != null && arrCitys.size() > 0
                && !arrCitys.contains(strCity)) {
            strCity = arrCitys.get(0);
        }
    }

    private void initCityIds(String[] cityIds) {
        if (cityIds != null) {
            arrCityIds.clear();
            for (int i = 0; i < cityIds.length; i++) {
                arrCityIds.add(cityIds[i]);
            }
        } else {
            String[] ids = mCitisIdsMap.get("浙江省");
            arrCityIds.clear();
            for (int i = 0; i < ids.length; i++) {
                arrCityIds.add(ids[i]);
            }
        }
        if (arrCityIds != null && arrCityIds.size() > 0
                && !arrCityIds.contains(strCityId)) {
            strCityId = arrCityIds.get(0);
        }
    }

    private void initAreas(String[] areas) {

        if (areas != null) {
            arrAreas.clear();
            for (int i = 0; i < areas.length; i++) {
                arrAreas.add(areas[i]);
            }
        } else {
            String[] area = mAreaDatasMap.get("温州市");
            arrAreas.clear();
            for (int i = 0; i < area.length; i++) {
                arrAreas.add(area[i]);
            }
        }
        if (arrAreas != null && arrAreas.size() > 0
                && !arrAreas.contains(strArea)) {
            strArea = arrAreas.get(0);
        }


    }

    private void initAreaIds(String[] areaIds) {

        if (areaIds != null) {
            arrAreaIds.clear();
            for (int i = 0; i < areaIds.length; i++) {
                arrAreaIds.add(areaIds[i]);
            }
        } else {
            String[] area = mAreaIdsMap.get("温州市");
            arrAreaIds.clear();
            for (int i = 0; i < area.length; i++) {
                arrAreaIds.add(area[i]);
            }
        }
        if (arrAreaIds != null && arrAreaIds.size() > 0
                && !arrAreaIds.contains(strAreaId)) {
            strAreaId = arrAreaIds.get(0);
        }

    }

    /**
     * 初始化地点
     *
     * @param province
     * @param city
     */
    public void setAddress(String province, String city, String area,String provinceId, String cityId, String areaId) {
        if (province != null && province.length() > 0) {
            this.strProvince = province;
        }
        if (city != null && city.length() > 0) {
            this.strCity = city;
        }
        if (area != null && city.length() > 0) {
            this.strArea = area;
        }
        if (area != null && city.length() > 0) {
            this.strProvinceId = provinceId;
        }
        if (area != null && city.length() > 0) {
            this.strCityId = cityId;
        }
        if (area != null && city.length() > 0) {
            this.strAreaId = areaId;
        }
    }

    /**
     * 返回省会索引，没有就返回默认“浙江”
     *
     * @param province
     * @return
     */
    public int getProvinceItem(String province) {
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < arrProvinces.size(); i++) {
            if (province.equals(arrProvinces.get(i))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            strProvince = "浙江省";
            return 10;
        }
        return provinceIndex;
    }

    /**
     * 得到城市索引，没有返回默认“温州市”
     *
     * @param city
     * @return
     */
    public int getCityItem(String city) {
        int cityIndex = 0;
        boolean nocity = true;
        for (int i = 0; i < arrCitys.size(); i++) {
//			System.out.println(arrCitys.get(i));
            if (city.equals(arrCitys.get(i))) {
                nocity = false;
                return cityIndex;
            } else {
                cityIndex++;
            }
        }
        if (nocity) {
            strCity = "温州市";
            return 2;
        }
        return cityIndex;
    }

    /**
     * 得到地区索引，没有返回默认“鹿城区”
     *
     * @param area
     * @return
     */
    public int getAresItem(String area) {
        int areaIndex = 0;
        boolean nocity = true;
        for (int i = 0; i < arrAreas.size(); i++) {

            if (area.equals(arrAreas.get(i))) {
                nocity = false;

                return areaIndex;
            } else {
                areaIndex++;
            }
        }
        if (nocity) {
            strCity = "鹿城区";
            return 0;
        }
        return areaIndex;
    }

}