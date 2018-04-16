package com.jiyun.ipanda.view.cctvview.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.CCTVContract;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctventity.PinDaoBeans;
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;
import com.jiyun.ipanda.presenter.CCTVPresenter;
import com.jiyun.ipanda.view.cctvview.adapter.PinDaoAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PinDaoFragment extends BaseFragment<CCTVPresenter> implements CCTVContract.View {

    @BindView(R.id.cctv_pindao_recycler)
    RecyclerView cctvPindaoRecycler;
    @BindView(R.id.cctv_pindao_pull)
    PullToRefreshLayout cctvPindaoPull;
    private List<PinDaoBeans.ListBean> mList;
    private String url;
    private PinDaoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pin_dao;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        Bundle arguments = getArguments();
        url = arguments.getString("url");
        Log.d("PinDaoFragment", url);
        cctvPindaoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PinDaoAdapter(mList, getContext());
        cctvPindaoRecycler.setAdapter(adapter);
        adapter.setOnClickListener(new PinDaoAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), mList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getPinDaoData(url);

    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void showCCTVData(CCTVBean cctvBean) {

    }

    @Override
    public void showPinDaoData(PinDaoBeans pinDaoBeans) {
        Log.d("PinDaoFragment", "pinDaoBeans.getList().size():" + pinDaoBeans.getList().size());
        mList.addAll(pinDaoBeans.getList());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showYangShiData(YangShiBean yangShiBean) {

    }


}
