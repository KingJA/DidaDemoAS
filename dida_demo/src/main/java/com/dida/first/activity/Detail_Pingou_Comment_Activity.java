package com.dida.first.activity;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.adapter.MyBaseListViewAdapter;
import com.dida.first.entity.BeanComment;
import com.dida.first.interfaces.OnMyClickListener;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.view.AbsListView.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015-11-20.
 */
public class Detail_Pingou_Comment_Activity extends BackTitleActivity implements AdapterView.OnItemClickListener ,OnMyClickListener<Integer>{

    private TextView tv_pingou_comment_send;
    private LinearLayout ll_pingou_root;
    private EditText et_pingou_comment_content;
    private PullToRefreshListView plv_pingou_comment;
    private boolean mKeyboardOpened;
    private List<BeanComment> commentList = new ArrayList<BeanComment>();
    private int keyboardHeight;
    private String mReplyTo="楼主";
    public static final String TAG = "Detail_Pingou_Comment_Activity";

    @Override
    public View setView() {
        view = View.inflate(this, R.layout.act_detail_pingou_comment, null);
        return view;
    }

    @Override
    public void initView() {
        keyboardHeight = UIUtils.getScreenHeight()/3;
        tv_pingou_comment_send = (TextView) view.findViewById(R.id.tv_pingou_comment_send);
        plv_pingou_comment = (PullToRefreshListView) view.findViewById(R.id.plv_pingou_comment);
        et_pingou_comment_content = (EditText) view.findViewById(R.id.et_pingou_comment_content);
        ll_pingou_root = (LinearLayout) view.findViewById(R.id.ll_pingou_root);
    }

    @Override
    public void initDoNet() {
        for (int i = 0; i < 20; i++) {
            commentList.add(new BeanComment());
        }

    }

    @Override
    public void initEvent() {
        ll_pingou_root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                ll_pingou_root.getWindowVisibleDisplayFrame(r);

                int screenHeight = ll_pingou_root.getRootView().getHeight();
                int heightDifference = screenHeight - (r.bottom - r.top);
//                Log.e(TAG ,"heightDifference="+ heightDifference);
            }
        });
        ll_pingou_root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {


            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyboardHeight)) {
//                    Log.i(TAG, "onLayoutChange: "+(oldBottom - bottom));
                    mKeyboardOpened = true;
                    Log.i("onLayoutChange", "弹出");
                    et_pingou_comment_content.setHint("回复 " + mReplyTo);
                    et_pingou_comment_content.requestFocus();

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyboardHeight)) {
                    Log.i("onLayoutChange", "关闭");
                    mKeyboardOpened = false;
                    et_pingou_comment_content.setHint("回复楼主");
                }
            }
        });
        MyAdapter myAdapter = new MyAdapter(commentList, Detail_Pingou_Comment_Activity.this);
        plv_pingou_comment.setAdapter(myAdapter);
        myAdapter.setOnMyClickListener(this);
        tv_pingou_comment_send.setOnClickListener(this);


    }

    @Override
    public void initData() {
        setBackTitle("所有评论");
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()){
            case R.id.tv_pingou_comment_send:
                ToastUtil.showMyToast("发送给"+mReplyTo);
                break;
        }
    }

    @Override
    public void setBackClick() {
        finish();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setKeyboard(position);
        Log.i("内层", position + "");
    }

    @Override
    public void onMyClick(Integer... args) {
        setKeyboard(args[0]);
        ToastUtil.showMyToast("点击了评论" + args[0]);
    }

    private void setKeyboard(int position){
        if (mKeyboardOpened){
            hideKeyboard();

        }else{
            mReplyTo=position+"";
            showKeyboard(position);
        }
        mKeyboardOpened=!mKeyboardOpened;
    }
    private void showKeyboard(int position) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
        Log.i("showKeyboard",  "showKeyboard");
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    class MyAdapter extends BaseAdapter {
        private OnMyClickListener<Integer> onMyClickListener;

        private List<BeanComment> list;
        private Context context;

        public MyAdapter(List<BeanComment> list, Context context) {
            this.list = list;
            this.context = context;
        }

        public void setOnMyClickListener(OnMyClickListener onMyClickListener) {
            this.onMyClickListener = onMyClickListener;
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolderO viewHolderO;
            if (convertView == null) {
                viewHolderO = new ViewHolderO();
                convertView = View.inflate(context, R.layout.item_comment_pingou, null);
                viewHolderO.mlv_pingou_detail_comment_second = (MyListView) convertView.findViewById(R.id.mlv_pingou_detail_comment_second);
                viewHolderO.tv_pingou_detail_comment_content = (TextView) convertView.findViewById(R.id.tv_pingou_detail_comment_content);

                convertView.setTag(viewHolderO);
            } else {
                viewHolderO = (ViewHolderO) convertView.getTag();
            }
            List<BeanComment> inList = new ArrayList<BeanComment>();
            for (int i = 0; i < new Random().nextInt(3) + 3; i++) {
                inList.add(new BeanComment());
            }
            viewHolderO.tv_pingou_detail_comment_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyClickListener.onMyClick(position);
                }
            });
            viewHolderO.mlv_pingou_detail_comment_second.setAdapter(new MyInAdapter(inList));
            viewHolderO.mlv_pingou_detail_comment_second.setOnItemClickListener(Detail_Pingou_Comment_Activity.this);

            return convertView;
        }

        class MyInAdapter extends MyBaseListViewAdapter<BeanComment> {

            public MyInAdapter(List<BeanComment> list) {
                super(list);
            }

            @Override
            public View getItemView(int position, View convertView, ViewGroup parent) {
                    View view = View.inflate(context, R.layout.include_commenet_pingou_in, null);
                return view;
            }
        }

        class ViewHolderO {
            MyListView mlv_pingou_detail_comment_second;
            TextView tv_pingou_detail_comment_content;

        }
    }
}
