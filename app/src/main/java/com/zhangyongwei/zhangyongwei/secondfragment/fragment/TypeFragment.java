package com.zhangyongwei.zhangyongwei.secondfragment.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.base.BaseFragment;
import com.zhangyongwei.zhangyongwei.secondfragment.adapter.TypeFragmentViewPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class TypeFragment extends BaseFragment {

    @InjectView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @InjectView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<BaseFragment> fragments;

    private TypeFragmentViewPagerAdapter adapter;

    @Override
    public View initView() {
        Log.e("TAG", "分类视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        initFragment();

        //设置适配器
        adapter = new TypeFragmentViewPagerAdapter(getFragmentManager(), fragments);

        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    /**
     * 初始化fragment
     */
    private void initFragment() {

        fragments = new ArrayList<>();

        fragments.add(new HotFragment());
        fragments.add(new NewsFragment());
        fragments.add(new OldFragment());
        fragments.add(new GoodFragment());
        fragments.add(new FuckFragment());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                Toast.makeText(mContext, "图像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_community_message:
                Toast.makeText(mContext, "信息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
