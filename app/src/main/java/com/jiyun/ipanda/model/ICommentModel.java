package com.jiyun.ipanda.model;

import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public interface ICommentModel {
    List<CommentBean> getCommentData();
    void insert(CommentBean commentBean);
}
