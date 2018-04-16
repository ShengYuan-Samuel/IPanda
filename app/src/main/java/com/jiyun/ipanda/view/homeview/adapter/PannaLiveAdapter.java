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

public class PannaLiveAdapter extends RecyclerView.Adapter<PannaLiveAdapter.ViewHolder> implements View.OnClickListener{
    public List<HomeBeans.DataBean.PandaliveBean.ListBean> mList;
    public List<HomeBeans.DataBean.WallliveBean.ListBeanX> mList1;
    public List<HomeBeans.DataBean.ChinaliveBean.ListBeanXX> mList2;
    private Context context;


    public PannaLiveAdapter(List<HomeBeans.DataBean.PandaliveBean.ListBean> mList,
             List<HomeBeans.DataBean.WallliveBean.ListBeanX> mList1,
            List<HomeBeans.DataBean.ChinaliveBean.ListBeanXX> mList2, Context context) {
        this.mList = mList;
        this.mList1 = mList1;
        this.mList2 = mList2;
        this.context = context;
    }


    @NonNull
    @Override
    public PannaLiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_pandalive_view_view, null);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mList != null){
        holder.textview_homepage_adtitle.setText(mList.get(position).getTitle());
        Glide.with(context).load(mList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.image_homepage_ad);
        }else if(mList1 != null){
            holder.textview_homepage_adtitle.setText(mList1.get(position).getTitle());
            Glide.with(context).load(mList1.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.image_homepage_ad);
        }else{
            holder.textview_homepage_adtitle.setText(mList2.get(position).getTitle());
            Glide.with(context).load(mList2.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.image_homepage_ad);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (mList != null){
        return mList.size();

        }else if (mList1!= null){
            return mList1.size();

        }else {
            return mList2.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_homepage_ad;
        private TextView textview_homepage_adtitle;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textview_homepage_adtitle = (TextView) itemView.findViewById(R.id.textview_homelive_title);
            this.image_homepage_ad = (ImageView) itemView.findViewById(R.id.image_homelive_view);
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
