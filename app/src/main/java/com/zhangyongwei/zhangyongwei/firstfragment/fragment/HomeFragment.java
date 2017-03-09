package com.zhangyongwei.zhangyongwei.firstfragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.base.BaseFragment;
import com.zhangyongwei.zhangyongwei.firstfragment.adapter.HomeAdapter;
import com.zhangyongwei.zhangyongwei.firstfragment.bean.HomeBean;
import com.zhangyongwei.zhangyongwei.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;
    private HomeAdapter adapter;

    @Override
    public View initView() {

        Log.e("TAG", "主页被初始化了");

        View view = View.inflate(mContext, R.layout.fragment_home, null);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求数据
        getDataFromNet();
    }

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(Constants.NEWSCENTER_PAGER_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网成功" + response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        List<HomeBean.DataBean.NewsBean> news = homeBean.getData().getNews();

        //设置适配器
        adapter = new HomeAdapter(mContext, news);

        rvHome.setAdapter(adapter);


        //设置布局管理器
        rvHome.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
