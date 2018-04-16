package com.jiyun.ipanda.view.pandaeyeview.adapter;

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
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeItemBean;

import java.util.List;

public class PandaEyeAdapter extends RecyclerView.Adapter<PandaEyeAdapter.ViewHolder> implements View.OnClickListener{
    private List<PandaEyeItemBean.ListBean> mList;
    private Context context;

    public PandaEyeAdapter(List<PandaEyeItemBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pannaeye_view, null);
        ViewHolder holder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textviewhomepagemolihuatitle.setText(mList.get(position).getTitle());
        holder.textviewhomepagemolihuatime.setText(mList.get(position).getFocus_date() + "");
        holder.textviewhomepagepandaeyelistvideolength.setText(mList.get(position).getVideolength());
        Glide.with(context).load(mList.get(position).getPicurl()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.imagehomepagemolihua);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewhomepagemolihuatime;
        TextView textviewhomepagemolihuatitle;
        TextView textviewhomepagepandaeyelistvideolength;
        ImageView imagehomepagemolihua;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewhomepagemolihuatime = itemView.findViewById(R.id.textview_homepage_molihuatime);
            textviewhomepagemolihuatitle = (TextView) itemView.findViewById(R.id.textview_homepage_molihuatitle);
            textviewhomepagepandaeyelistvideolength = (TextView) itemView.findViewById(R.id.textview_homepage_pandaeyelist_videolength);
            imagehomepagemolihua = (ImageView) itemView.findViewById(R.id.image_homepage_molihua);
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
