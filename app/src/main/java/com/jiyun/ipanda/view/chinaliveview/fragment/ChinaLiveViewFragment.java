package com.jiyun.ipanda.view.chinaliveview.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.ChinaLiveItemContract;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVItemBean;
import com.jiyun.ipanda.presenter.ChinaLiveItemPresenter;
import com.jiyun.ipanda.view.chinaliveview.adapter.ChinaAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ChinaLiveViewFragment extends BaseFragment<ChinaLiveItemPresenter> implements ChinaLiveItemContract.View {

    @BindView(R.id.pandalive_view_Recycler)
    RecyclerView pandaliveViewRecycler;
    @BindView(R.id.pandalive_view_pull)
    PullToRefreshLayout pandaliveViewPull;
    private List<CCTVItemBean.LiveBean> mList;
    private ChinaAdapter adapter;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china_live_view;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        pandaliveViewRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChinaAdapter(mList, getContext());
        pandaliveViewRecycler.setAdapter(adapter);
        Bundle arguments = getArguments();
        url = arguments.getString("url");

    }

    @Override
    protected void loadData() {
        presenter.getChinaLiveItemData(url);

    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void showChinaLiveItemData(CCTVItemBean cctvBean) {
        mList.addAll(cctvBean.getLive());
        adapter.notifyDataSetChanged();
    }
}
