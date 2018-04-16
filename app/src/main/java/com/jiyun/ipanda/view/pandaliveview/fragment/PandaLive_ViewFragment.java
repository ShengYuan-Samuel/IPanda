package com.jiyun.ipanda.view.pandaliveview.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.PandaLiveItemContract;
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveItemBean;
import com.jiyun.ipanda.presenter.PandaLiveItemPresenter;
import com.jiyun.ipanda.view.pandaliveview.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class PandaLive_ViewFragment extends BaseFragment<PandaLiveItemPresenter> implements PandaLiveItemContract.View{
    @BindView(R.id.pannalive_image_live)
    ImageView pannaliveImageLive;
    @BindView(R.id.pannalive_text_title)
    TextView pannaliveTextTitle;
    @BindView(R.id.pannalive_button)
    Button pannaliveButton;
    @BindView(R.id.pannalive_text_content)
    TextView pannaliveTextContent;
    @BindView(R.id.pannalive_rl)
    RelativeLayout pannaliveRl;
    @BindView(R.id.pannalive_tab_live)
    TabLayout pannaliveTabLive;
    @BindView(R.id.pannalive_pv_live)
    ViewPager pannalivePvLive;
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private int count = 0;
    private VpAdapter adapter;

    @Override
    public void showPandaLiveItemData(PandaLiveItemBean pandaLiveItemBean) {
        //Log.d("PandaLive_ViewFragment", "pandaLiveItemBean.getBookmark().getMultiple().size():" + pandaLiveItemBean.getBookmark().getMultiple().size());
        pannaliveTextTitle.setText("[正在直播]"+pandaLiveItemBean.getLive().get(0).getTitle());
        pannaliveTextContent.setText(pandaLiveItemBean.getLive().get(0).getBrief());
        Glide.with(getContext()).load(pandaLiveItemBean.getLive().get(0).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(pannaliveImageLive);
        String title = pandaLiveItemBean.getBookmark().getMultiple().get(0).getTitle();
        String title1 = pandaLiveItemBean.getBookmark().getWatchTalk().get(0).getTitle();
        mTitle.add(title);
        mTitle.add(title1);
        MultiViewFragment multiViewFragment = new MultiViewFragment();
        WatchingFragment watchingFragment = new WatchingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",pandaLiveItemBean.getBookmark().getMultiple().get(0).getUrl());
        multiViewFragment.setArguments(bundle);
        mFragments.add(multiViewFragment);
        mFragments.add(watchingFragment);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPandaLiveMultiData(MultiViewBean multiViewBean) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_live__view;
    }

    @Override
    protected void init() {

        Log.d("PandaLiveFragment", "我在这里");
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        adapter = new VpAdapter(getActivity().getSupportFragmentManager(), mTitle, mFragments);
        pannalivePvLive.setAdapter(adapter);
        pannaliveTabLive.setupWithViewPager(pannalivePvLive);

    }

    @Override
    protected void loadData() {

        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        if (!(url.equals(""))) {
            pannaliveRl.setVisibility(View.VISIBLE);
            presenter.getPandaLiveItemData(url);
        }
    }

    @Override
    public void updateTitle() {

    }

    @OnClick(R.id.pannalive_button)
    public void onViewClicked() {
        count++;
        if (count % 2 == 0) {
            pannaliveButton.setBackgroundResource(R.drawable.live_china_detail_up);
        } else {
            pannaliveButton.setBackgroundResource(R.drawable.live_china_detail_down);

        }
        if (pannaliveTextContent.getVisibility() == View.GONE) {
            pannaliveTextContent.setVisibility(View.VISIBLE);
        } else {
            pannaliveTextContent.setVisibility(View.GONE);
        }
    }

}
