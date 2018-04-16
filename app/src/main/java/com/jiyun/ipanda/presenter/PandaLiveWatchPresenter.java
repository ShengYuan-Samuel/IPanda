package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.PandaLiveContract;
import com.jiyun.ipanda.contract.PandaLiveWatchContract;
import com.jiyun.ipanda.model.CommentModel;
import com.jiyun.ipanda.model.ICommentModel;
import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public class PandaLiveWatchPresenter implements PandaLiveWatchContract.Presenter{
    private ICommentModel commentModel;
    private PandaLiveWatchContract.View view;
    public PandaLiveWatchPresenter() {
        commentModel = new CommentModel();

    }

    @Override
    public List<CommentBean> getCommentData() {
        view.showWatchData(commentModel.getCommentData());
        return commentModel.getCommentData();
    }

    @Override
    public void insert(CommentBean commentBean) {
        commentModel.insert(commentBean);


    }

    @Override
    public void attachView(PandaLiveWatchContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
