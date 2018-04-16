package com.jiyun.ipanda.view.pandaliveview.adapter;

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
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;

import java.util.List;
public class PannaLiveAdapter extends RecyclerView.Adapter<PannaLiveAdapter.ViewHolder> implements View.OnClickListener {
    private List<MultiViewBean.ListBean> mList;
    private Context context;
    public PannaLiveAdapter(List<MultiViewBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pannalive_one_item_view, parent, false);

        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.pannaliveoneitemimage);
        holder.pannaliveoneitemtitle.setText(mList.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pannaliveoneitemimage;
        private TextView pannaliveoneitemtitle;
        public ViewHolder(View itemView) {
            super(itemView);
            pannaliveoneitemtitle = (TextView) itemView.findViewById(R.id.pannalive_oneitem_title);
            pannaliveoneitemimage = (ImageView) itemView.findViewById(R.id.pannalive_oneitem_image);
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
