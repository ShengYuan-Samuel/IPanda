package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVBeans;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVItemBean;

public interface ChinaLiveItemContract {
    public interface View{
        void showChinaLiveItemData(CCTVItemBean cctvBean);
       // void showPopupWindow();
    }

    public interface Presenter extends BasePresenter<View> {
        void getChinaLiveItemData(String url);
    }
}
