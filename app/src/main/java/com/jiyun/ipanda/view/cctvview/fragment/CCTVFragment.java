package com.jiyun.ipanda.view.cctvview.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.CCTVContract;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctventity.PinDaoBeans;
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;
import com.jiyun.ipanda.presenter.CCTVPresenter;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.pandaliveview.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CCTVFragment extends BaseFragment<CCTVPresenter> implements CCTVContract.View {
    @BindView(R.id.cctv_tab)
    TabLayout cctvTab;
    @BindView(R.id.cctv_vp)
    ViewPager cctvVp;
    private List<Fragment> mFragments;
    private List<String> mTitle;
    private VpAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cctv;
    }

    @Override
    protected void init() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        adapter = new VpAdapter(getActivity().getSupportFragmentManager(), mTitle, mFragments);
        cctvVp.setAdapter(adapter);
        cctvTab.setupWithViewPager(cctvVp);
    }

    @Override
    protected void loadData() {
        presenter.getCCTVData();

    }

    @Override
    public void updateTitle() {
        ((MainActivity) App.context).getTvTitle().setText("CCTV");
        ((MainActivity) App.context).getImageHudong().setVisibility(View.INVISIBLE);
        ((MainActivity) App.context).getImageSign().setVisibility(View.INVISIBLE);

    }

    @Override
    public void showCCTVData(CCTVBean cctvBean) {
        Log.d("CCTVFragment", "cctvBean.getTablist().size():" + cctvBean.getTablist().size());
        for (CCTVBean.TablistBean tablistBean : cctvBean.getTablist()) {
            mTitle.add(tablistBean.getTitle());
        }
        PinDaoFragment pinDaoFragment = new PinDaoFragment();
        Bundle bundle = new Bundle();
        String url = cctvBean.getTablist().get(0).getUrl();
        bundle.putString("url", url);
       pinDaoFragment.setArguments(bundle);
        YangShiFragment yangShiFragment = new YangShiFragment();
        Bundle bundle1 = new Bundle();
        String url1 = cctvBean.getTablist().get(1).getUrl();
       bundle1.putString("url", url1);
        yangShiFragment.setArguments(bundle1);
        mFragments.add(pinDaoFragment);
        mFragments.add(yangShiFragment);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPinDaoData(PinDaoBeans pinDaoBeans) {

    }

    @Override
    public void showYangShiData(YangShiBean yangShiBean) {

    }


}
