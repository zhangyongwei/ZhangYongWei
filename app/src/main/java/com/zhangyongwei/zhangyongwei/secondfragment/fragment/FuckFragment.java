package com.zhangyongwei.zhangyongwei.secondfragment.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zhangyongwei.zhangyongwei.base.BaseFragment;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class FuckFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setText("FuckFragment");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
