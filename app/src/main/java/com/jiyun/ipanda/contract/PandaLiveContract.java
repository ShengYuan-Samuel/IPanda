package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveBeans;

public interface PandaLiveContract {

    public interface View{
        void showPandaLiveData(PandaLiveBeans pandaLiveBeans);

    }
    public interface Persenter extends BasePresenter<View> {
        void getPandaLiveData();


    }
}
