package com.zhangyongwei.zhangyongwei.secondfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangyongwei.zhangyongwei.R;
import com.zhangyongwei.zhangyongwei.secondfragment.bean.HotBean;
import com.zhangyongwei.zhangyongwei.utils.Url;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class HotListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HotBean.ResultBean> datas;

    public HotListViewAdapter(Context mContext, List<HotBean.ResultBean> result) {

        this.mContext = mContext;
        this.datas = result;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_hot_listview, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        HotBean.ResultBean resultBean = datas.get(position);

        //图像
        Glide.with(mContext).load(Url.BASE_URL_IMAGE+resultBean.getAvatar()).into(viewHolder.ivNewPostAvatar);

        viewHolder.tvHotUsername.setText(resultBean.getUsername());

        //图像
        Glide.with(mContext).load(Url.BASE_URL_IMAGE+resultBean.getFigure()).into(viewHolder.ivHotFigure);
        viewHolder.tvHotComments.setText(resultBean.getComments());
        viewHolder.tvHotLikes.setText(resultBean.getLikes());
        viewHolder.tvHotSaying.setText(resultBean.getSaying());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @InjectView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @InjectView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @InjectView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @InjectView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @InjectView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @InjectView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
