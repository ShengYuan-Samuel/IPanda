package com.jiyun.ipanda.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.contract.InteractionContract;
import com.jiyun.ipanda.model.entity.InteractionIBeans;
import com.jiyun.ipanda.presenter.InteractionPresenter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteractionActivity extends AppCompatActivity implements InteractionContract.View{

    @BindView(R.id.interaction_return)
    ImageView interactionReturn;
    @BindView(R.id.interaction_Recycle)
    RecyclerView interactionRecycle;
    @BindView(R.id.interaction_pull)
    PullToRefreshLayout interactionPull;
    private InteractionContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.interaction_return)
    public void onViewClicked() {
    }

    private void init(){
        presenter = new InteractionPresenter(this);
        presenter.getInteractionData();
    }

    @Override
    public void showInteractionData(InteractionIBeans beans) {
        List<InteractionIBeans.InteractiveBean> interactive = beans.getInteractive();
        Log.d("InteractionActivity", "interactive.size():" + interactive.size());

    }
}
