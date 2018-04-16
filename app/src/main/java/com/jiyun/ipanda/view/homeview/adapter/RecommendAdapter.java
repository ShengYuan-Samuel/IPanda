package com.jiyun.ipanda.view.homeview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;

import java.util.List;

/**
 * 这是精彩推荐的适配器
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> implements View.OnClickListener {
    private List<HomeBeans.DataBean.AreaBean.ListscrollBean> mList;
    private Context context;

    public RecommendAdapter(List<HomeBeans.DataBean.AreaBean.ListscrollBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recommend_view_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textview_homepage_adtitle.setText(mList.get(position).getTitle());
        Glide.with(context).load(mList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.image_homepage_ad);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_homepage_ad;
        private TextView textview_homepage_adtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            image_homepage_ad = itemView.findViewById(R.id.image_homepage_ad);
            textview_homepage_adtitle = itemView.findViewById(R.id.textview_homepage_adtitle);
        }
    }

    private OnClickListeren onClickListener;

    public interface OnClickListeren {
        void onCilckListeren(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onCilckListeren(v, (int) v.getTag());
        }
    }

    public void setOnClickListener(OnClickListeren onClick) {
        this.onClickListener = onClick;
    }
}