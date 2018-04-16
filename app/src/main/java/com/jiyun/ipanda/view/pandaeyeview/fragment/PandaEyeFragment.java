package com.jiyun.ipanda.view.pandaeyeview.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.PandaEyeContract;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeBeans;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeItemBean;
import com.jiyun.ipanda.presenter.PandaEyePresenter;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.homeview.adapter.PannaeyeAdapter;
import com.jiyun.ipanda.view.pandaeyeview.activity.PandaEyeWebActivity;
import com.jiyun.ipanda.view.pandaeyeview.adapter.HeaderViewAdapter;
import com.jiyun.ipanda.view.pandaeyeview.adapter.PandaEyeAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class PandaEyeFragment extends BaseFragment<PandaEyePresenter> implements PandaEyeContract.View {


    @BindView(R.id.pandaeye_Recycle)
    RecyclerView pandaeyeRecycle;
    @BindView(R.id.pandaeye_pull)
    PullToRefreshLayout pandaeyePull;
    private View headView;
    private ImageView pannaeye_head_image;
    private TextView pannaeye_head_text;
    private List<PandaEyeItemBean.ListBean> mList;
    private PandaEyeAdapter adapter;
    private HeaderViewAdapter headerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_eye;
    }

    @Override
    protected void init() {
        addHeadView();
        mList = new ArrayList<>();
        pandaeyeRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PandaEyeAdapter(mList, getContext());
        headerViewAdapter = new HeaderViewAdapter(adapter);
        headerViewAdapter.addHeaderView(headView);
        pandaeyeRecycle.setAdapter(headerViewAdapter);
        adapter.setOnClickListener(new PandaEyeAdapter.OnClickListeren() {
            @Override
            public void onCilckListeren(View view, int position) {
                Toast.makeText(getContext(), "你点击到我的蛋了"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void loadData() {
        presenter.getPandaEyeData();

    }

    @Override
    public void updateTitle() {
        ((MainActivity) App.context).getTvTitle().setText("熊猫观察");
        ((MainActivity) App.context).getImageHudong().setVisibility(View.INVISIBLE);
        ((MainActivity) App.context).getImageSign().setVisibility(View.INVISIBLE);

    }

    @Override
    public void showPandaEyeData(PandaEyeBeans pandaEyeBeans) {
        List<PandaEyeBeans.DataBean.BigImgBean> bigImg = pandaEyeBeans.getData().getBigImg();
        final PandaEyeBeans.DataBean.BigImgBean bigImgBean = bigImg.get(0);
        String listurl = pandaEyeBeans.getData().getListurl();
        String image = bigImgBean.getImage();
        Glide.with(getContext()).load(image).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(pannaeye_head_image);
        pannaeye_head_text.setText(pandaEyeBeans.getData().getBigImg().get(0).getTitle());
        pannaeye_head_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PandaEyeWebActivity.class);
                intent.putExtra("url",bigImgBean.getUrl());
                startActivity(intent);
            }
        });
        presenter.getPandaEyeItemBean(listurl);


    }

    @Override
    public void showPandaEyeItemData(PandaEyeItemBean pandaEyeItemBean) {
        Log.d("PandaEyeFragment", "pandaEyeItemBean.getList().size():" + pandaEyeItemBean.getList().size());
        mList.addAll(pandaEyeItemBean.getList());
        adapter.notifyDataSetChanged();
        headerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addHeadView() {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.pannaeye_head_item, null);
        pannaeye_head_image = headView.findViewById(R.id.pannaeye_head_image);
        pannaeye_head_text = headView.findViewById(R.id.pannaeye_head_text);
    }

}
