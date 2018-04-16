package com.jiyun.ipanda.view.pandaliveview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public class PandaLiveWatchAdapter extends RecyclerView.Adapter<PandaLiveWatchAdapter.ViewHolder> implements View.OnClickListener{
     private List<CommentBean> mList;

         public PandaLiveWatchAdapter(List<CommentBean> mList) {
             this.mList = mList;
         }

         @Override
         public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pandalive_watch_view, parent, false);

             ViewHolder holder = new ViewHolder(view);
             view.setOnClickListener(this);
             return holder;
         }

         @Override
         public void onBindViewHolder(ViewHolder holder, int position) {
             holder.pandalivewatchauther.setText(mList.get(position).getAuthor());
             holder.pandalivewatchcontent.setText(mList.get(position).getContent());
             holder.pandalivewatchtime.setText(mList.get(position).getTime());
             holder.itemView.setTag(position);
         }

         @Override
         public int getItemCount() {
             return mList.isEmpty() ? 0 : mList.size();
         }
         public class ViewHolder extends RecyclerView.ViewHolder {
             private TextView pandalivewatchauther;
             private TextView pandalivewatchcontent;
             private TextView pandalivewatchtime;
             public ViewHolder(View itemView) {
                 super(itemView);
                 pandalivewatchtime = (TextView) itemView.findViewById(R.id.pandalive_watch_time);
                 pandalivewatchcontent = (TextView) itemView.findViewById(R.id.pandalive_watch_content);
                 pandalivewatchauther = (TextView) itemView.findViewById(R.id.pandalive_watch_auther);
             }
         }
         private onItemClick mOnItemClick;
         public interface onItemClick{
             void onItemClickLister(View view,int position);
         }
         @Override
         public void onClick(View v) {
             if(mOnItemClick !=null){
                 this.mOnItemClick.onItemClickLister(v,(int)v.getTag());
             }
         }
         public void setOnItemClick(onItemClick onItem){
             this.mOnItemClick = onItem;
         }
}
