package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public interface PandaLiveWatchContract {

    public interface View{
        void showWatchData(List<CommentBean> commentBeans);
        void showPopupWindow();
    }
    public interface Presenter extends BasePresenter<View>{
        List<CommentBean> getCommentData();
        void insert(CommentBean commentBean);
    }
}
