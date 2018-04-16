package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveItemBean;

public interface PandaLiveItemContract {
    public interface View{
       void showPandaLiveItemData(PandaLiveItemBean pandaLiveItemBean);
       void showPandaLiveMultiData(MultiViewBean multiViewBean);

    }
    public interface Persenter extends BasePresenter<View> {
        void getPandaLiveItemData(String url);
        void getPandaLiveMultiData(String url);

    }
}
