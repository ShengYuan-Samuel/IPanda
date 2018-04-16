package com.jiyun.ipanda.view.homeview.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.HomeContract;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomeCCTVBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomePandaEyeBean;
import com.jiyun.ipanda.presenter.HomePresenter;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.homeview.adapter.CCTVAdapter;
import com.jiyun.ipanda.view.homeview.adapter.HomeAdapter;
import com.jiyun.ipanda.view.homeview.adapter.PannaeyeAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.BindView;
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, HomeAdapter.HomePandaCallback {
    @BindView(R.id.home_Recycle)
    RecyclerView homeRecycle;
    @BindView(R.id.home_pull)
    PullToRefreshLayout homePull;

    private RecyclerView homePaneyeRec;
    private RecyclerView homeCCTVRec;
    private RecyclerView homeGuangYingRec;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        homeRecycle.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void loadData() {
        presenter.getHomeData();
    }
    @Override
    public void updateTitle() {
        ((MainActivity) App.context).getTvTitle().setText("");
        ((MainActivity) App.context).getImageHudong().setVisibility(View.VISIBLE);
        ((MainActivity) App.context).getImageSign().setVisibility(View.VISIBLE);
    }
    @Override
    public void showHomeData(HomeBeans homeBeans) {
            HomeAdapter homeAdapter = new HomeAdapter(homeBeans, getContext());
            homeAdapter.setHomePandaCallback(this);
            homeRecycle.setAdapter(homeAdapter);
            for (HomeBeans.DataBean.BigImgBean bigImgBean : homeBeans.getData().getBigImg()) {
                Log.d("HomeFragment", bigImgBean.getId());
        }

    }

    @Override
    public void showHomePandaEyeData(final HomePandaEyeBean homePandaEyeBean) {
        Log.d("HomeFragment", "homePandaEyeBean.getList().size():" + homePandaEyeBean.getList().size());
        PannaeyeAdapter pannaeyeAdapter = new PannaeyeAdapter(homePandaEyeBean.getList(), getContext());
        homePaneyeRec.setAdapter(pannaeyeAdapter);
        pannaeyeAdapter.setOnClickListener(new PannaeyeAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), homePandaEyeBean.getList().get(position).getDaytime(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void showHomeCCTVData(HomeCCTVBeans homeCCTVBeans) {
        final List<HomeCCTVBeans.ListBean> list = homeCCTVBeans.getList();
        CCTVAdapter adapter = new CCTVAdapter(list, getContext());
        homeCCTVRec.setAdapter(adapter);
        adapter.setOnClickListener(new CCTVAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void showHomeGuangYingData(HomePandaEyeBean homePandaEyeBean) {
        final List<HomePandaEyeBean.ListBean> list = homePandaEyeBean.getList();
        PannaeyeAdapter adapter = new PannaeyeAdapter(list, getContext());
        homeGuangYingRec.setAdapter(adapter);
        adapter.setOnClickListener(new PannaeyeAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), list.get(position).getDaytime(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void loadPandaeyeByUrl(String url, RecyclerView pandaEyeRecyclerView) {
        this.homePaneyeRec = pandaEyeRecyclerView;
        presenter.getHomeDaEyeData(url);
    }

    @Override
    public void loadPandaHomeCCTVByUrl(String url, RecyclerView pandaEyeRecyclerView) {
        this.homeCCTVRec = pandaEyeRecyclerView;
        presenter.getHomeCCTVData(url);

    }

    @Override
    public void loadGuangYingByUrl(String url, RecyclerView pandaEyeRecyclerView) {
        this.homeGuangYingRec = pandaEyeRecyclerView;
        presenter.getHomeGuangYingData(url);

    }
}
