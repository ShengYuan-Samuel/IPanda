package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVBeans;

public interface ChinaLiveContract {
    public interface View{
        void showChinaLiveData(CCTVBeans cctvBean);
        void showPopupWindow();
    }

    public interface Presenter extends BasePresenter<View> {
        void getChinaLiveData();
    }
}
