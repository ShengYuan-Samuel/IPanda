package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeBeans;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeItemBean;

public interface PandaEyeContract {

    public interface View{
        void showPandaEyeData(PandaEyeBeans pandaEyeBeans);
        void showPandaEyeItemData(PandaEyeItemBean pandaEyeItemBean);
        void addHeadView();
    }
    public interface Presenter extends BasePresenter<View>{
        void getPandaEyeData();
        void getPandaEyeItemBean(String url);
    }
}
