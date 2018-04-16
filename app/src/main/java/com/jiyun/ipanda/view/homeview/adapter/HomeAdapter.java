package com.jiyun.ipanda.view.homeview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.homeview.activity.HomeWebActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private HomeBeans homeBeans;
    private Context context;
    private HomePandaCallback homePandaCallback;
    private final HomeBeans.DataBean data;
    private LayoutInflater inflater;
    private final int BANNER_TYPE_VIEW = 0;//这是轮播图布局
    private final int RECOMMEND_TYPE_VIEW = 1; //这是频道图
    private final int PANDAEYE_TYPE_VIEW = 2; //这是熊猫观察
    private final int PANDALIVE_TYPE_VIEW = 3; //这是熊猫直播
    private final int CHANGCHENG_VIEW = 4;//这是长城直播
    private final int CHINA_VIEW = 5;//这是中国直播
    private final int TEBIECEHUA_VIEW = 6;//这是特别策划的页面
    private final int CCTV_VIE = 7;//这是cctv页面
    private final int GUANGYING_VIEW = 8;//这是最后一个

    public HomeAdapter(HomeBeans homeBeans, Context context) {
        this.homeBeans = homeBeans;
        this.context = context;
        data = homeBeans.getData();
        inflater = LayoutInflater.from(context);
    }
    public void setHomePandaCallback(HomePandaCallback homePandaCallback) {
        this.homePandaCallback = homePandaCallback;
    }

    public interface HomePandaCallback {
        void loadPandaeyeByUrl(String url, RecyclerView pandaEyeRecyclerView);
        void loadPandaHomeCCTVByUrl(String url, RecyclerView pandaEyeRecyclerView);
        void loadGuangYingByUrl(String url, RecyclerView pandaEyeRecyclerView);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        if (viewType == BANNER_TYPE_VIEW) {
            view = inflater.inflate(R.layout.home_slideshow_view, null);
            holder = new BannerHolder(view);
        }else if (viewType == RECOMMEND_TYPE_VIEW) {
            view = inflater.inflate(R.layout.home_recommend_view, parent, false);
            holder = new RecommendHolder(view);
        }else if (viewType == PANDAEYE_TYPE_VIEW) {
            view = inflater.inflate(R.layout.home_pandaeye_view, parent, false);
            holder = new PannaeyeHolder(view);
        }else if (viewType == PANDALIVE_TYPE_VIEW) {
            view = inflater.inflate(R.layout.home_pandalive_view, parent, false);
            holder = new PannaLiveHolder(view);
        } else if (viewType == CHANGCHENG_VIEW) {
            view = inflater.inflate(R.layout.home_pandalive_view, parent, false);
            holder = new ChangChengHolder(view);
        } else if (viewType == CHINA_VIEW) {
            view = inflater.inflate(R.layout.home_pandalive_view, parent, false);
            holder = new ChinaLiveHolder(view);
        }else if (viewType == TEBIECEHUA_VIEW) {
            view = inflater.inflate(R.layout.home_special_view, null);
            holder = new TeBieHolder(view);
        } else if (viewType == CCTV_VIE) {
            view = inflater.inflate(R.layout.home_pandalive_view, parent, false);
            holder = new CCTVHolder(view);
        }else if (viewType == GUANGYING_VIEW){
            view = inflater.inflate(R.layout.home_pandalive_view,parent,false);
            holder = new GuangYingHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            setBanner(bannerHolder);
        }else if (holder instanceof RecommendHolder) {
            RecommendHolder recommendHolder = (RecommendHolder) holder;
            setRecommend(recommendHolder);
        }else if (holder instanceof PannaeyeHolder) {
            PannaeyeHolder pannaeyeHolder = (PannaeyeHolder) holder;
            setPannaEye(pannaeyeHolder);
        }else if (holder instanceof PannaLiveHolder) {
            PannaLiveHolder pannaLiveHolder = (PannaLiveHolder) holder;
            setPannaLive(pannaLiveHolder);
        } else if (holder instanceof ChangChengHolder) {
            ChangChengHolder chinaLiveHolder = (ChangChengHolder) holder;
            setChangCheng(chinaLiveHolder);
        } else if (holder instanceof ChinaLiveHolder) {
            ChinaLiveHolder chinaLiveHolder = (ChinaLiveHolder) holder;
            setChinaLive(chinaLiveHolder);
        }else if (holder instanceof TeBieHolder) {
            TeBieHolder teBieHolder = (TeBieHolder) holder;
            setTeBie(teBieHolder);
        }else if (holder instanceof CCTVHolder) {
            CCTVHolder cctvHolder = (CCTVHolder) holder;
            setCCTV(cctvHolder);
        }else if (holder instanceof GuangYingHolder){
            GuangYingHolder guangYingHolder = (GuangYingHolder) holder;
            setGuangYing(guangYingHolder);
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_TYPE_VIEW;
        } else if (position == 1) {
            return RECOMMEND_TYPE_VIEW;
        } else if (position == 2) {
            return PANDAEYE_TYPE_VIEW;
        } else if (position == 3) {
            return PANDALIVE_TYPE_VIEW;
        } else if (position == 4) {
            return CHANGCHENG_VIEW;
        } else if (position == 5) {
            return CHINA_VIEW;
        } else if (position == 6) {
            return TEBIECEHUA_VIEW;
        } else if (position == 7) {
            return CCTV_VIE;
        } else if (position == 8) {
            return GUANGYING_VIEW;
        }
        return super.getItemViewType(position);


    }

    //这是轮播的布局参数
    public class BannerHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.home_slideshow);
        }
    }

    //这是初始化数据的
    public void setBanner(BannerHolder holder) {
        final List<HomeBeans.DataBean.BigImgBean> bigImg = data.getBigImg();
        //创建一个标题
        ArrayList<String> mTitle = new ArrayList<>();
        //创建一个照片的集合
        ArrayList<String> mPhoto = new ArrayList<>();
        for (HomeBeans.DataBean.BigImgBean bigImgBean : bigImg) {
            mTitle.add(bigImgBean.getTitle());
            mPhoto.add(bigImgBean.getImage());
        }
        //设置一个样式
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //解析图片的
        holder.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
            }
        });
        //设置一个图片集合
        holder.banner.setImages(mPhoto);
        //设置标题集合
        holder.banner.setBannerTitles(mTitle);
        //设置动画
        holder.banner.setBannerAnimation(Transformer.Default);
        //设置滚动时间
        holder.banner.setDelayTime(3000);
        //设置自动轮播
        holder.banner.isAutoPlay(true);
        //设置一个标题位置
        holder.banner.setIndicatorGravity(BannerConfig.RIGHT)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(context, HomeWebActivity.class);
                        intent.putExtra("url",bigImg.get(position).getUrl());
                        context.startActivity(intent);
                    }
                })
                .start();

    }
    //这是精彩推荐的布局参数
    public class RecommendHolder extends RecyclerView.ViewHolder {
        private ImageView mHomeJingcaiImage;
        private TextView mHomeJingcaiText;
        private RecyclerView mHomeJingcaiRecycler;

        public RecommendHolder(View itemView) {
            super(itemView);
            mHomeJingcaiImage = itemView.findViewById(R.id.home_recommend_image);
            mHomeJingcaiText = itemView.findViewById(R.id.home_recommend_text);
            mHomeJingcaiRecycler = itemView.findViewById(R.id.home_recommend_recycler);

        }
    }
    //这是初始化精彩推荐的方法
    public void setRecommend(RecommendHolder holder) {
        HomeBeans.DataBean.AreaBean area = data.getArea();
        holder.mHomeJingcaiText.setText(area.getTitle());
        Glide.with(context).load(area.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.mHomeJingcaiImage);
        final List<HomeBeans.DataBean.AreaBean.ListscrollBean> listscroll = area.getListscroll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.mHomeJingcaiRecycler.setLayoutManager(linearLayoutManager);
        RecommendAdapter recommendAdapter = new RecommendAdapter(listscroll, context);
        holder.mHomeJingcaiRecycler.setAdapter(recommendAdapter);
        recommendAdapter.setOnClickListener(new RecommendAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(context, "你点击了" + listscroll.get(position).getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //这是熊猫观察的布局
    public class PannaeyeHolder extends RecyclerView.ViewHolder {
        private TextView home_pannaeye_title;
        private ImageView home_pannaeye_image;
        private TextView home_pannaeye_text1;
        private TextView home_pannaeye_text2;
        private TextView home_pannaeye_text3;
        private TextView home_pannaeye_text4;
        private RecyclerView home_pannaeye_reycycler;

        public PannaeyeHolder(View itemView) {
            super(itemView);
            home_pannaeye_title = itemView.findViewById(R.id.home_pannaeye_title);
            home_pannaeye_text1 = itemView.findViewById(R.id.home_pannaeye_text1);
            home_pannaeye_text2 = itemView.findViewById(R.id.home_pannaeye_text2);
            home_pannaeye_text3 = itemView.findViewById(R.id.home_pannaeye_text3);
            home_pannaeye_text4 = itemView.findViewById(R.id.home_pannaeye_text4);
            home_pannaeye_image = itemView.findViewById(R.id.home_pannaeye_image);
            home_pannaeye_reycycler = itemView.findViewById(R.id.home_pannaeye_reycycler);

        }
    }

    //这是初始化熊猫观察的数据
    public void setPannaEye(final PannaeyeHolder holder) {
        HomeBeans.DataBean.PandaeyeBean pandaeye = data.getPandaeye();
        holder.home_pannaeye_title.setText(pandaeye.getTitle());
        Glide.with(context).load(pandaeye.getPandaeyelogo()).into(holder.home_pannaeye_image);
        holder.home_pannaeye_text1.setText(pandaeye.getItems().get(0).getBrief());
        holder.home_pannaeye_text2.setText(pandaeye.getItems().get(0).getTitle());
        holder.home_pannaeye_text3.setText(pandaeye.getItems().get(1).getBrief());
        holder.home_pannaeye_text4.setText(pandaeye.getItems().get(1).getTitle());
        holder.home_pannaeye_reycycler.setLayoutManager(new LinearLayoutManager(context));
        homePandaCallback.loadPandaeyeByUrl(pandaeye.getPandaeyelist(), holder.home_pannaeye_reycycler);
    }
    //这是主页熊猫直播的
    private class PannaLiveHolder extends RecyclerView.ViewHolder {
        private TextView home_pannalive_text;
        private RecyclerView home_pannalive_recycler;
        public PannaLiveHolder(View itemView) {
            super(itemView);
            home_pannalive_text = itemView.findViewById(R.id.home_pannalive_text);
            home_pannalive_recycler = itemView.findViewById(R.id.home_pannalive_recycler);
        }
    }
    //这是初始化熊猫直播数据的布局
    public void setPannaLive(PannaLiveHolder holder) {
        HomeBeans.DataBean.PandaliveBean pandalive = data.getPandalive();
        holder.home_pannalive_text.setText(pandalive.getTitle());
        final List<HomeBeans.DataBean.PandaliveBean.ListBean> list = pandalive.getList();
        holder.home_pannalive_recycler.setLayoutManager(new GridLayoutManager(context, 3));
        PannaLiveAdapter pannaLiveAdapter = new PannaLiveAdapter(list,null,null, context);
        holder.home_pannalive_recycler.setAdapter(pannaLiveAdapter);
        pannaLiveAdapter.setOnClickListener(new PannaLiveAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //这是长城
    private class ChangChengHolder extends RecyclerView.ViewHolder {
        private TextView home_pannalive_text;
        private RecyclerView home_pannalive_recycler;
        public ChangChengHolder(View itemView) {
            super(itemView);
            home_pannalive_text = itemView.findViewById(R.id.home_pannalive_text);
            home_pannalive_recycler = itemView.findViewById(R.id.home_pannalive_recycler);
        }
    }
    //这是初始化长城直播数据的布局
    public void setChangCheng(ChangChengHolder holder) {
        HomeBeans.DataBean.WallliveBean walllive = data.getWalllive();
        holder.home_pannalive_text.setText(walllive.getTitle());
        final List<HomeBeans.DataBean.WallliveBean.ListBeanX> list = walllive.getList();
        holder.home_pannalive_recycler.setLayoutManager(new GridLayoutManager(context, 3));
        PannaLiveAdapter pannaLiveAdapter = new PannaLiveAdapter(null,list,null, context);
        holder.home_pannalive_recycler.setAdapter(pannaLiveAdapter);
        pannaLiveAdapter.setOnClickListener(new PannaLiveAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //这是中国
    private class ChinaLiveHolder extends RecyclerView.ViewHolder {
        private TextView home_pannalive_text;
        private RecyclerView home_pannalive_recycler;
        public ChinaLiveHolder(View itemView) {
            super(itemView);
            home_pannalive_text = itemView.findViewById(R.id.home_pannalive_text);
            home_pannalive_recycler = itemView.findViewById(R.id.home_pannalive_recycler);
        }
    }

    //这是初始化中国直播数据的布局
    public void setChinaLive(ChinaLiveHolder holder) {
        HomeBeans.DataBean.ChinaliveBean chinalive = data.getChinalive();
        holder.home_pannalive_text.setText(chinalive.getTitle());
        final List<HomeBeans.DataBean.ChinaliveBean.ListBeanXX> list = chinalive.getList();
        holder.home_pannalive_recycler.setLayoutManager(new GridLayoutManager(context, 3));
        PannaLiveAdapter pannaLiveAdapter = new PannaLiveAdapter(null,null,list, context);
        holder.home_pannalive_recycler.setAdapter(pannaLiveAdapter);
        pannaLiveAdapter.setOnClickListener(new PannaLiveAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //这是特别策划的布局
    public class TeBieHolder extends RecyclerView.ViewHolder {
        private TextView home_tebie_title;
        private ImageView home_tebie_image;
        private TextView home_tebie_content;

        public TeBieHolder(View itemView) {
            super(itemView);
            home_tebie_title = itemView.findViewById(R.id.home_tebie_title);
            home_tebie_image = itemView.findViewById(R.id.home_tebie_image);
            home_tebie_content = itemView.findViewById(R.id.home_tebie_content);
        }
    }
    //这是初始化特别策划的布局
    public void setTeBie(TeBieHolder holder) {
        final HomeBeans.DataBean.InteractiveBean interactive = data.getInteractive();
        String title = interactive.getTitle();
        final List<HomeBeans.DataBean.InteractiveBean.InteractiveoneBean> interactiveone = interactive.getInteractiveone();
        String image = interactiveone.get(0).getImage();
        String title1 = interactiveone.get(0).getTitle();
        holder.home_tebie_title.setText(title);
        holder.home_tebie_content.setText(title1);
        Glide.with(context).load(image).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.home_tebie_image);
        holder.home_tebie_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeWebActivity.class);
                intent.putExtra("url",interactiveone.get(0).getUrl());
                context.startActivity(intent);
            }
        });
    }
    //这是初始化cctv布局方法
    public class CCTVHolder extends RecyclerView.ViewHolder {
        private TextView home_pannalive_text;
        private RecyclerView home_pannalive_recycler;

        public CCTVHolder(View itemView) {
            super(itemView);
            home_pannalive_text = itemView.findViewById(R.id.home_pannalive_text);
            home_pannalive_recycler = itemView.findViewById(R.id.home_pannalive_recycler);
        }
    }

    //这是初始化cctv数据的
    public void setCCTV(final CCTVHolder holder) {
        HomeBeans.DataBean.CctvBean cctv = data.getCctv();
        String url = cctv.getListurl();
        String title = cctv.getTitle();
        holder.home_pannalive_text.setText(title);
        holder.home_pannalive_recycler.setLayoutManager(new GridLayoutManager(context, 2));
        homePandaCallback.loadPandaHomeCCTVByUrl(url, holder.home_pannalive_recycler);

    }
    //这是初始化光影中国布局方法
    public class GuangYingHolder extends RecyclerView.ViewHolder {
        private TextView home_pannalive_text;
        private RecyclerView home_pannalive_recycler;

        public GuangYingHolder(View itemView) {
            super(itemView);
            home_pannalive_text = itemView.findViewById(R.id.home_pannalive_text);
            home_pannalive_recycler = itemView.findViewById(R.id.home_pannalive_recycler);
        }
    }

    public void setGuangYing(final GuangYingHolder holder) {

        List<HomeBeans.DataBean.ListBeanXXX> list = data.getList();
        String title = list.get(0).getTitle();
        holder.home_pannalive_text.setText(title);
        String url = list.get(0).getListUrl();
        holder.home_pannalive_recycler.setLayoutManager(new LinearLayoutManager(context));
        homePandaCallback.loadGuangYingByUrl(url,holder.home_pannalive_recycler);
    }
}
