package com.zhangyongwei.zhangyongwei.secondfragment.fragment;

import android.view.View;
import android.widget.ImageView;

import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.base.BaseFragment;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class GoodFragment extends BaseFragment {

    private ImageView imageView;
    @Override
    public View initView() {


        imageView = new ImageView(mContext);

        return imageView;
    }

    @Override
    public void initData() {
        super.initData();

        imageView.setImageResource(R.drawable.cc);
    }
}
