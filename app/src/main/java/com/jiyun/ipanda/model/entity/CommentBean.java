package com.jiyun.ipanda.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CommentBean {

    @Id(autoincrement = true)
    private Long id;
    private String author;
    private String time;
    private String content;
    @Generated(hash = 1112028043)
    public CommentBean(Long id, String author, String time, String content) {
        this.id = id;
        this.author = author;
        this.time = time;
        this.content = content;
    }

    public CommentBean(String author, String time, String content) {
        this.author = author;
        this.time = time;
        this.content = content;
    }

    @Generated(hash = 373728077)
    public CommentBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
