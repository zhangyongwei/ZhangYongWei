package com.zhangyongwei.zhangyongwei.secondfragment.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zhangyongwei.zhangyongwei.base.BaseFragment;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class TypeFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setText("分类被初始化了");

        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        textView.setText("分类数据被初始化了");
    }
}
