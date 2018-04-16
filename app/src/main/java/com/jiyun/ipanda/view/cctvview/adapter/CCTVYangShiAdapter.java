package com.jiyun.ipanda.view.cctvview.adapter;

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
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;

import java.util.List;


public class CCTVYangShiAdapter extends RecyclerView.Adapter<CCTVYangShiAdapter.ViewHolder> implements View.OnClickListener{
    private List<YangShiBean.ListBean> mList;
    private Context context;

    public CCTVYangShiAdapter(List<YangShiBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctv_item_view, null);
        ViewHolder holder = new ViewHolder(mView);
        mView.setOnClickListener(this);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cctvtext.setText(mList.get(position).getVideoLength());
        holder.cctvtitle.setText(mList.get(position).getTitle());
        holder.cctvcontent.setText(mList.get(position).getBrief());
        Glide.with(context).load(mList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.cctvimage);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cctvimage;
        private TextView cctvtext;
        private TextView cctvtitle;
        private TextView cctvcontent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.cctvcontent = (TextView) itemView.findViewById(R.id.cctv_content);
            this.cctvtitle = (TextView) itemView.findViewById(R.id.cctv_title);
            this.cctvtext = (TextView) itemView.findViewById(R.id.cctv_text);
            this.cctvimage = (ImageView) itemView.findViewById(R.id.cctv_image);
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
