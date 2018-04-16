package com.jiyun.ipanda.view.pandaliveview.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.PandaLiveItemContract;
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveItemBean;
import com.jiyun.ipanda.presenter.PandaLiveItemPresenter;
import com.jiyun.ipanda.view.pandaliveview.adapter.PannaLiveAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MultiViewFragment extends BaseFragment<PandaLiveItemPresenter> implements PandaLiveItemContract.View {
    @BindView(R.id.multi_recycler)
    RecyclerView multiRecycler;
    private String url;
    private List<MultiViewBean.ListBean> mList;
    private PannaLiveAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multi_view;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        multiRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new PannaLiveAdapter(mList, getContext());
        multiRecycler.setAdapter(adapter);
        adapter.setOnClickListener(new PannaLiveAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), mList.get(position).getDaytime(), Toast.LENGTH_SHORT).show();
            }
        });
        Bundle arguments = getArguments();
        url = arguments.getString("url");
    }

    @Override
    protected void loadData() {
        presenter.getPandaLiveMultiData(url);

    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void showPandaLiveItemData(PandaLiveItemBean pandaLiveItemBean) {

    }

    @Override
    public void showPandaLiveMultiData(MultiViewBean multiViewBean) {
        List<MultiViewBean.ListBean> list = multiViewBean.getList();
        mList.addAll(list);
        adapter.notifyDataSetChanged();

    }

}
