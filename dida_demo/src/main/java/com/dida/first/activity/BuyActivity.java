package com.dida.first.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dida.first.R;
import com.dida.first.selectmore.adapter.ImagePublishAdapter;
import com.dida.first.selectmore.model.ImageItem;
import com.dida.first.selectmore.util.IntentConstants;
import com.dida.first.selectmore.view.ImageBucketChooseActivity;
import com.dida.first.selectmore.view.ImageZoomActivity;
import com.dida.first.utils.CustomConstants;
import com.dida.first.wheelview.ChangeDeadlineDialog;
import com.dida.first.wheelview.ChangeDeadlineDialog.OnBirthListener;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends Activity implements OnClickListener {
    private boolean isOpen;
    private GridView gv_content;
    private ValueAnimator valueAnimator;
    private static final String itmes[] = {"女装", "男装", "衣服", "笔记本", "手机", "自行车",
            "洗衣机", "电脑", "床", "模拟器", "眼镜", "遥控飞机", "大空飞船", "光碟"};
    private LinearLayout ll_popup;
    private GridView mGridView;
    private ImagePublishAdapter mAdapter;
    private TextView sendTv;
    public static List<ImageItem> mDataList = new ArrayList<ImageItem>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish);
        minitView();
        initEvent();
        minitData();
        initData();
        initView();
    }

    private void minitData() {
        initClose();

    }

    private void initClose() {
        layoutParams = fl_content.getLayoutParams();
        layoutParams.height = 0;
        fl_content.setLayoutParams(layoutParams);
    }

    private void initEvent() {
        iv_down_up.setOnClickListener(this);
        ll_deadline.setOnClickListener(this);
        myGridViewAdapter = new MyGridViewAdapter();
        gv_content.setAdapter(myGridViewAdapter);
        int height = fl_content.getHeight();
        Log.i("height", height + "");
        gv_content.setOnItemClickListener(onItemClickListener);


    }

    private void minitView() {
        tv_deadline = (TextView) findViewById(R.id.tv_deadline);
        ll_deadline = (LinearLayout) findViewById(R.id.ll_deadline);
        view_down_up = findViewById(R.id.view_down_up);
        iv_down_up = (ImageView) findViewById(R.id.iv_down_up);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        tv_select_name = (TextView) findViewById(R.id.tv_select_name);
        gv_content = (GridView) findViewById(R.id.gv_content);

    }

    private int selectPosition;
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectPosition = position;
            myGridViewAdapter.notifyDataSetChanged();
            tv_select_name.setText(itmes[position]);
            expend();
            Log.i("onItemClick", "selectPosition:" + itmes[position]);
        }

    };

    private MyGridViewAdapter myGridViewAdapter;
    private TextView tv_select_name;
    private FrameLayout fl_content;
    private ImageView iv_down_up;
    private LayoutParams layoutParams;
    private View view_down_up;
    private LinearLayout ll_deadline;
    private TextView tv_deadline;

    class MyGridViewAdapter extends BaseAdapter {

        private TextView tv;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return itmes.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tv = new TextView(BuyActivity.this);
            tv.setBackgroundColor(getResources().getColor(
                    R.color.tab_color_selected));
            tv.setText(itmes[position]);
            tv.setTextColor(Color.parseColor("#ffffff"));
            int dp8 = dip2px(8);
            tv.setPadding(0, dp8, 0, dp8);
            tv.setGravity(Gravity.CENTER);
            convertView = tv;
            if (position == selectPosition) {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.tab_color_selected));
            } else {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.black_bg));
            }
            return convertView;
        }

    }

    public int dip2px(int dip) {
        //dp和px的转换关系比例值
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_down_up:
                expend();
                break;
            case R.id.ll_deadline:
                deadLine();
                break;

        }

    }

    private void deadLine() {

        // TODO Auto-generated method stub
        ChangeDeadlineDialog mChangeDeadlineDialog = new ChangeDeadlineDialog(
                BuyActivity.this);
//		mChangeDeadlineDialog.setDate(2015, 03, 29);
        mChangeDeadlineDialog.show();
        mChangeDeadlineDialog.setBirthdayListener(new OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this,
//						year + "-" + month + "-" + day,
//						Toast.LENGTH_LONG).show();
                String deadLine = year + "-" + month + "-" + day;
                tv_deadline.setText(deadLine);
            }
        });


    }

    private void expend() {
        int height = 0;
        int targetHeight = getMeasureHeight();
        if (isOpen) {
            //展开---->关闭
            isOpen = false;
            valueAnimator = ValueAnimator.ofInt(targetHeight, height);
            view_down_up.setVisibility(View.VISIBLE);
        } else {
            //关闭---->展开
            isOpen = true;
            //1，初始高度，2，目标高度
            valueAnimator = ValueAnimator.ofInt(height, targetHeight);
            view_down_up.setVisibility(View.GONE);
        }

        //时时刻刻去计算当前控件需要扩展到的高度，高度值获取以后，设置给需要去扩展的控件，即可达到向下弹出时刻改变位置的效果
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                //设置给控件的长度值
                layoutParams.height = (Integer) arg0.getAnimatedValue();
                //作用在子控件(ll_footer)上
                fl_content.setLayoutParams(layoutParams);
            }
        });

        valueAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (isOpen) {
                    //打开
                    iv_down_up.setBackgroundResource(R.drawable.shangla);
                } else {
                    //关闭
                    iv_down_up.setBackgroundResource(R.drawable.xiala);
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });
        valueAnimator.setDuration(300);
        valueAnimator.start();
    }

    private int getMeasureHeight() {

        int measuredWidth = fl_content.getMeasuredWidth();

        int makeWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY);
        int makeHeightMeasureSpec = MeasureSpec.makeMeasureSpec(1280, MeasureSpec.AT_MOST);
        fl_content.measure(makeWidthMeasureSpec, makeHeightMeasureSpec);
        Log.i("getMeasureHeight", fl_content.getMeasuredHeight() + "");
        return fl_content.getMeasuredHeight();
    }

    protected void onPause() {
        super.onPause();
        saveTempToPref();
    }

    @Override
    protected void onDestroy() {
        // clearPicture();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        clearPicture();
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTempToPref();
    }

    private void saveTempToPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = JSON.toJSONString(mDataList);
        sp.edit().putString(CustomConstants.PREF_TEMP_IMAGES, prefStr).commit();

    }

    private void getTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = sp.getString(CustomConstants.PREF_TEMP_IMAGES, null);
        if (!TextUtils.isEmpty(prefStr)) {
            List<ImageItem> tempImages = JSON.parseArray(prefStr,
                    ImageItem.class);
            mDataList = tempImages;
        }
    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        getTempFromPref();
        List<ImageItem> incomingDataList = (List<ImageItem>) getIntent()
                .getSerializableExtra(IntentConstants.EXTRA_IMAGE_LIST);
        if (incomingDataList != null) {
            mDataList.addAll(incomingDataList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyDataChanged(); // 当在ImageZoomActivity中删除图片时，返回这里需要刷新
    }

    public void initView() {
        TextView titleTv = (TextView) findViewById(R.id.title);
        titleTv.setText("");
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImagePublishAdapter(this, mDataList);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /**
                 * 当点击是添加按钮，弹出popuwindow
                 */
                if (position == getDataSize()) {
                    new PopupWindows(BuyActivity.this, mGridView);
                } else {
                    /**
                     * 点击图片，进入预览页面
                     */
                    Intent intent = new Intent(BuyActivity.this,
                            ImageZoomActivity.class);
                    intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
                            (Serializable) mDataList);
                    intent.putExtra(IntentConstants.EXTRA_CURRENT_IMG_POSITION,
                            position);
                    startActivity(intent);
                }
            }
        });
        sendTv = (TextView) findViewById(R.id.action);
        sendTv.setText("发送");
        sendTv.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                clearPicture();
                /**
                 * 发布求购
                 */
                // TODO 这边以mDataList为来源做上传的动作
            }

        });
    }

    private void clearPicture() {
        removeTempFromPref();
        if (mDataList != null) {
            mDataList.clear();
        }
    }

    private int getDataSize() {
        return mDataList == null ? 0 : mDataList.size();
    }

    private int getAvailableSize() {
        int availSize = CustomConstants.MAX_IMAGE_SIZE - mDataList.size();
        if (availSize >= 0) {
            return availSize;
        }
        return 0;
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindow, null);

            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));

            setWidth(LayoutParams.MATCH_PARENT);
            setHeight(LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            bt1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    takePhoto();
                    dismiss();
                }
            });
            bt2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(BuyActivity.this,
                            ImageBucketChooseActivity.class);
                    intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                            getAvailableSize());
                    startActivity(intent);
                    finish();
                    dismiss();
                }
            });
            bt3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    public void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File vFile = new File(Environment.getExternalStorageDirectory()
                + "/myimage/", String.valueOf(System.currentTimeMillis())
                + ".jpg");
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        path = vFile.getPath();
        Uri cameraUri = Uri.fromFile(vFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (mDataList.size() < CustomConstants.MAX_IMAGE_SIZE
                        && resultCode == -1 && !TextUtils.isEmpty(path)) {
                    ImageItem item = new ImageItem();
                    item.sourcePath = path;
                    mDataList.add(item);
                }
                break;
        }
    }

    private void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

}