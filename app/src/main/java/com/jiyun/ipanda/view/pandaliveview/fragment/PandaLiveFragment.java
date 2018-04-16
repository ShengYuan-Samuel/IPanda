package com.jiyun.ipanda.view.pandaliveview.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.PandaLiveContract;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveBeans;
import com.jiyun.ipanda.presenter.PandaLivePresenter;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.pandaliveview.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PandaLiveFragment extends BaseFragment<PandaLivePresenter> implements PandaLiveContract.View{
    @BindView(R.id.pannalive_tab)
    TabLayout pannaliveTab;
    @BindView(R.id.pannalive_vp)
    ViewPager pannaliveVp;
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private VpAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_live;
    }

    @Override
    protected void init() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        LinearLayout linearLayout = (LinearLayout) pannaliveTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.tab_shape));
        adapter = new VpAdapter(getActivity().getSupportFragmentManager(), mTitle, mFragments);
        pannaliveVp.setAdapter(adapter);
        pannaliveTab.setupWithViewPager(pannaliveVp);
    }

    @Override
    protected void loadData() {
        presenter.getPandaLiveData();


    }

    @Override
    public void updateTitle() {
        ((MainActivity) App.context).getTvTitle().setText("熊猫直播");
        ((MainActivity) App.context).getImageHudong().setVisibility(View.INVISIBLE);
        ((MainActivity) App.context).getImageSign().setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPandaLiveData(PandaLiveBeans pandaLiveBeans) {
        List<PandaLiveBeans.TablistBean> tablist = pandaLiveBeans.getTablist();
        for (PandaLiveBeans.TablistBean tablistBean : tablist) {
            PandaLive_ViewFragment pandaLive_viewFragment = new PandaLive_ViewFragment();
            Bundle bundle = new Bundle();
            String url = tablistBean.getUrl();
            bundle.putString("url", url);
            pandaLive_viewFragment.setArguments(bundle);
            mTitle.add(tablistBean.getTitle());
            mFragments.add(pandaLive_viewFragment);
        }
        adapter.notifyDataSetChanged();
       // Log.d("PandaLiveFragment", "pandaLiveBeans.getTablist().size():" + pandaLiveBeans.getTablist().size());
    }
}
