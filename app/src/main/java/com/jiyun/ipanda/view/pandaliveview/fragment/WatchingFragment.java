package com.jiyun.ipanda.view.pandaliveview.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.contract.PandaLiveWatchContract;
import com.jiyun.ipanda.model.entity.CommentBean;
import com.jiyun.ipanda.presenter.PandaLiveWatchPresenter;
import com.jiyun.ipanda.view.pandaliveview.adapter.PandaLiveWatchAdapter;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class WatchingFragment extends BaseFragment<PandaLiveWatchPresenter> implements PandaLiveWatchContract.View, View.OnClickListener {


    @BindView(R.id.pannalive_et)
    EditText pannaliveEt;
    @BindView(R.id.pannalive_insert)
    Button pannaliveInsert;
    @BindView(R.id.pandalive_wachRec)
    RecyclerView pandaliveWachRec;
    @BindView(R.id.pandalive_watchpull)
    PullToRefreshLayout pandaliveWatchpull;
    private List<CommentBean> mList;
    private PandaLiveWatchAdapter adapter;
    private TextView pandalive_popup_cancel;
    private TextView pandalive_popup_confirm;
    private String time;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_watching;
    }
    @Override
    protected void init() {
        mList = new ArrayList<>();
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
        layout.setReverseLayout(true);//列表翻转
        pandaliveWachRec.setLayoutManager(layout);
        adapter = new PandaLiveWatchAdapter(mList);
        pandaliveWachRec.setAdapter(adapter);

    }

    @Override
    protected void loadData() {
        presenter.getCommentData();

    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void showWatchData(List<CommentBean> commentBeans) {
        mList.addAll(commentBeans);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.pandalive_popup_view, null);
        pandalive_popup_cancel = view.findViewById(R.id.pandalive_popup_cancel);
        pandalive_popup_confirm = view.findViewById(R.id.pandalive_popup_confirm);
        pandalive_popup_cancel.setOnClickListener(this);
        pandalive_popup_confirm.setOnClickListener(this);
    }
    @OnClick(R.id.pannalive_insert)
    public void onViewClicked() {
        String content = pannaliveEt.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(getContext(), "请输入你的评论内容", Toast.LENGTH_SHORT).show();
            return;
        }
        CommentBean commentBean = new CommentBean("央视网网友",getTime(),content);
        presenter.insert(commentBean);
        List<CommentBean> commentData = presenter.getCommentData();
        mList.clear();
        mList.addAll(commentData);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pandalive_popup_cancel:
                break;
            case R.id.pandalive_popup_confirm:
                break;
        }

    }
    private String getTime(){
        Date curDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String str = formatter.format(curDate);
        return str;
    }
}
