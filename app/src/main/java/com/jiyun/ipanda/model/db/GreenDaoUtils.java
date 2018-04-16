package com.jiyun.ipanda.model.db;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.gen.CommentBeanDao;
import com.jiyun.ipanda.gen.DaoMaster;
import com.jiyun.ipanda.gen.DaoSession;
import com.jiyun.ipanda.model.entity.CommentBean;

import java.util.List;

public class GreenDaoUtils {

    private static GreenDaoUtils greenDaoUtils;
    private final CommentBeanDao commentBeanDao;

    public GreenDaoUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.context, "comment");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        commentBeanDao = daoSession.getCommentBeanDao();
    }
    public static GreenDaoUtils getInstance(){
        if (greenDaoUtils == null){
            synchronized (GreenDaoUtils.class){
                if (greenDaoUtils == null)
                    greenDaoUtils = new GreenDaoUtils();
            }
        }
        return greenDaoUtils;
    }

    public List<CommentBean> queryAll(){
        return commentBeanDao.loadAll();
    }

    public void insert(CommentBean commentBean){
        commentBeanDao.insert(commentBean);

    }
}
