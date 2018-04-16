package com.jiyun.ipanda.model;

import com.jiyun.ipanda.model.db.GreenDaoUtils;
import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public class CommentModel implements ICommentModel{
    @Override
    public List<CommentBean> getCommentData() {
        return GreenDaoUtils.getInstance().queryAll();
    }

    @Override
    public void insert(CommentBean commentBean) {
        GreenDaoUtils.getInstance().insert(commentBean);

    }
}
