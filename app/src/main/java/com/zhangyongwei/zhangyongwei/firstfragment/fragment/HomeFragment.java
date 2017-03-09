package com.zhangyongwei.zhangyongwei.firstfragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    //适配器
    private HomeAdapter adapter;

    private boolean isLoadMore=false;

    private String moreUrl;
    private HomeBean homeBean;
    private List<HomeBean.DataBean.NewsBean> news;
    private List<HomeBean.DataBean.NewsBean> moreNews;


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

        //初始化监听
        initListener();

    }

    private void initListener() {

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                isLoadMore = false;

                getDataFromNet();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);

                isLoadMore = true;

                getMoreDataFromNet(moreUrl);
            }
        });

    }
    /**
     * 加载更多数据
     * @param moreUrl
     */
    private void getMoreDataFromNet(String moreUrl) {

        OkHttpUtils.get()
                .url(moreUrl)
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

                        refresh.finishRefreshLoadMore();
                    }
                });

    }

    /**
     * 联网请求
     */
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

                        refresh.finishRefresh();
                    }
                });
    }

    private void processData(String json) {
        homeBean = JSON.parseObject(json, HomeBean.class);
        String more = homeBean.getData().getMore();

        Log.e("TAG", "11111111111111111"+more);

        if(TextUtils.isEmpty(more)) {

            moreUrl="";
        }else{

            moreUrl = Constants.BASE_URL+more;
        }

        if(!isLoadMore) {

            news = homeBean.getData().getNews();
            //设置适配器
            adapter = new HomeAdapter(mContext, news);

            rvHome.setAdapter(adapter);

            //设置布局管理器
            GridLayoutManager manager = new GridLayoutManager(mContext,1);

            rvHome.setLayoutManager(manager);

        }else{

            moreNews = homeBean.getData().getNews();

            news.addAll(moreNews);

            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
