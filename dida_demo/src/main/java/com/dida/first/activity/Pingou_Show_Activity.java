package com.dida.first.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.dialog.DialogList;
import com.dida.first.utils.BigDecimalUtil;
import com.dida.first.utils.ToastUtil;
import com.dida.first.view.MySwitchButton;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015-11-17.
 */
public class Pingou_Show_Activity extends BackTitleActivity implements DialogList.OnListItemClickListener,MySwitchButton.OnSwitchListener {
    //快递名字
    private String mExpressName;
    //商品数量
    private int mCount;
    //商品价格
    private double mPrice = 256.00;
    //商品总价格
    private double mTotelPrice;
    //快递价格
    private int mExpressPrice;
    private LinearLayout ll_pingou_show_address;
    private RelativeLayout rl_pingou_show_express;
    private TextView tv_pingou_show_expressName;
    private TextView tv_pingou_show_totlePrice;
    private TextView tv_pingou_show_editCount;
    private TextView tv_pingou_show_count;
    private EditText et_pingou_show_editPrice;
    private ImageView iv_pingou_show_add;
    private ImageView iv_pingou_show_reduce;
    private MySwitchButton msb_pingou_show_nobody;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View setView() {
        view = View.inflate(Pingou_Show_Activity.this, R.layout.activity_add_show, null);
        return view;
    }

    @Override
    public void initView() {
        ll_pingou_show_address = (LinearLayout) view.findViewById(R.id.ll_pingou_show_address);
        msb_pingou_show_nobody = (MySwitchButton) view.findViewById(R.id.msb_pingou_show_nobody);
        rl_pingou_show_express = (RelativeLayout) view.findViewById(R.id.rl_pingou_show_express);
        tv_pingou_show_totlePrice = (TextView) view.findViewById(R.id.tv_pingou_show_totlePrice);
        tv_pingou_show_editCount = (TextView) view.findViewById(R.id.tv_pingou_show_editCount);
        tv_pingou_show_count = (TextView) view.findViewById(R.id.tv_pingou_show_count);
        tv_pingou_show_expressName = (TextView) view.findViewById(R.id.tv_pingou_show_expressName);

        et_pingou_show_editPrice = (EditText) view.findViewById(R.id.et_pingou_show_editPrice);
        iv_pingou_show_add = (ImageView) view.findViewById(R.id.iv_pingou_show_add);
        iv_pingou_show_reduce = (ImageView) view.findViewById(R.id.iv_pingou_show_reduce);

    }

    @Override
    public void initDoNet() {

    }

    @Override
    public void initEvent() {
        ll_pingou_show_address.setOnClickListener(this);
        rl_pingou_show_express.setOnClickListener(this);
        iv_pingou_show_add.setOnClickListener(this);
        iv_pingou_show_reduce.setOnClickListener(this);
        msb_pingou_show_nobody.setOnSwitchListener(this);

    }

    @Override
    public void initData() {
        setBackTitle("拼购预付");
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.rl_pingou_show_express:
                DialogList dialogList = new DialogList(this);
                dialogList.setOnListItemClickListener(this);
                break;
            case R.id.iv_pingou_show_add:
                addCount();
                break;
            case R.id.iv_pingou_show_reduce:
                reduceCount();
                break;
            case R.id.ll_pingou_show_address:
                changeAddress();
                break;
        }

    }

    private void changeAddress() {

        ToastUtil.showMyToast("更改地址");
    }

    private void reduceCount() {
        if (mCount > 1) {
            mCount--;
            calculate();
        }
    }

    private void addCount() {
        mCount++;
        calculate();
    }

    private void calculate() {
        tv_pingou_show_count.setText(mCount + "");
        tv_pingou_show_editCount.setText(mCount + "");
        String price = df.format(BigDecimalUtil.mul(mCount, mPrice));
        tv_pingou_show_totlePrice.setText(price);
    }

    @Override
    public void setBackClick() {
        finish();
    }

    @Override
    public void onListItemClick(String express) {
        mExpressName = express;
        tv_pingou_show_expressName.setText(express);

    }

    @Override
    public void onSwitch(boolean isOpen) {
        if (isOpen){
            ToastUtil.showMyToast("开");
        }else{
            ToastUtil.showMyToast("关");
        }


    }
}
