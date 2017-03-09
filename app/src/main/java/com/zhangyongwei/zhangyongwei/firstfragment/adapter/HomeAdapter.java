package com.zhangyongwei.zhangyongwei.firstfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.firstfragment.bean.HomeBean;
import com.zhangyongwei.zhangyongwei.utils.Constants;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<HomeBean.DataBean.NewsBean> datas;

    private final LayoutInflater inflater;

    public HomeAdapter(Context mContext, List<HomeBean.DataBean.NewsBean> news) {
        this.mContext = mContext;
        this.datas = news;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new MyViewHolder(mContext, inflater.inflate(R.layout.item_home_view, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HomeBean.DataBean.NewsBean newsBean = datas.get(position);
        holder.setData(newsBean);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_time)
        TextView tvTime;

        MyViewHolder(Context mContext, View view) {
            super(view);

            ButterKnife.inject(this, view);
        }

        public void setData(HomeBean.DataBean.NewsBean newsBean) {

            tvTitle.setText(newsBean.getTitle());
            tvTime.setText(newsBean.getPubdate());

            //加载图片
            Glide.with(mContext).load(Constants.BASE_URL+newsBean.getListimage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.news_pic_default)
                    .error(R.drawable.news_pic_default)
                    .into(ivIcon);

        }
    }
}
