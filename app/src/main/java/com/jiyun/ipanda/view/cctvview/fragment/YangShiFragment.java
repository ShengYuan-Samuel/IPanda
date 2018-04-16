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
import com.jiyun.ipanda.view.cctvview.adapter.CCTVYangShiAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class YangShiFragment extends BaseFragment<CCTVPresenter> implements CCTVContract.View {

    @BindView(R.id.cctv_yangshi_recycler)
    RecyclerView cctvYangshiRecycler;
    @BindView(R.id.cctv_yangshi_pull)
    PullToRefreshLayout cctvYangshiPull;
    Unbinder unbinder;
    private String url;
    private List<YangShiBean.ListBean> mList;
    private CCTVYangShiAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yang_shi;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        cctvYangshiRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CCTVYangShiAdapter(mList, getContext());
        cctvYangshiRecycler.setAdapter(adapter);
        adapter.setOnClickListener(new CCTVYangShiAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), mList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        Bundle arguments = getArguments();
        url = arguments.getString("url");
        Log.d("PinDaoFragment", url);
    }

    @Override
    protected void loadData() {
        presenter.getYangShiData(url);

    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void showCCTVData(CCTVBean cctvBean) {

    }

    @Override
    public void showPinDaoData(PinDaoBeans pinDaoBeans) {

    }

    @Override
    public void showYangShiData(YangShiBean yangShiBean) {
        Log.d("YangShiFragment", "yangShiBean.getList().size():" + yangShiBean.getList().size());
        mList.addAll(yangShiBean.getList());
        adapter.notifyDataSetChanged();

    }


}
