package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.InteractionIBeans;

public interface InteractionContract {

    public interface View{
        void showInteractionData(InteractionIBeans beans);
    }

    public interface Presenter extends BasePresenter<View>{
        void getInteractionData();
    }
}
