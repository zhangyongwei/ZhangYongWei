package com.zhangyongwei.zhangyongwei.secondfragment.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.base.BaseFragment;
import com.zhangyongwei.zhangyongwei.secondfragment.adapter.HotListViewAdapter;
import com.zhangyongwei.zhangyongwei.secondfragment.bean.HotBean;
import com.zhangyongwei.zhangyongwei.utils.Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class HotFragment extends BaseFragment {

    @InjectView(R.id.lv_hot_info)
    ListView lvHotInfo;

    private HotListViewAdapter adapter;

    @Override
    public View initView() {
        Log.e("TAG", "HotFragment");

        View view = View.inflate(mContext, R.layout.fragment_home_info, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    /**
     * 联网请求
     */
    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(Url.HOT_POST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("TAG", "联网成功"+response);

                        processData(response);
                    }
                });

    }

    private void processData(String json) {

        HotBean hotBean = JSON.parseObject(json, HotBean.class);
        List<HotBean.ResultBean> result = hotBean.getResult();

        if(result!=null && result.size()>0) {

            //设置适配器
            adapter = new HotListViewAdapter(mContext,result);

            lvHotInfo.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
