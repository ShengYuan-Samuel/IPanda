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
import com.jiyun.ipanda.model.entity.homeentity.HomeCCTVBeans;

import java.util.List;
public class CCTVAdapter extends RecyclerView.Adapter<CCTVAdapter.ViewHolder> implements View.OnClickListener{
    private List<HomeCCTVBeans.ListBean> mList;
    private Context context;



    public CCTVAdapter(List<HomeCCTVBeans.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public CCTVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_pandacctv_view_view, null);

        ViewHolder holder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CCTVAdapter.ViewHolder holder, int position) {
        holder.textviewhomepageadtitlecctv.setText(mList.get(position).getTitle());
        Glide.with(context).load(mList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.imagehomepageadcctv);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagehomepageadcctv;
        private TextView textviewhomepageadtitlecctv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textviewhomepageadtitlecctv = (TextView) itemView.findViewById(R.id.textview_homepage_adtitle_cctv);
            this.imagehomepageadcctv = (ImageView) itemView.findViewById(R.id.image_homepage_ad_cctv);
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