package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.base.BaseView;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomeCCTVBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomePandaEyeBean;

public interface HomeContract {

    public interface View{
        void showHomeData(HomeBeans homeBeans);
        void showHomePandaEyeData(HomePandaEyeBean homePandaEyeBean);
        void showHomeCCTVData(HomeCCTVBeans homeCCTVBeans);
        void showHomeGuangYingData(HomePandaEyeBean homePandaEyeBean);

    }

    public interface Presenter extends BasePresenter<View>{
        void getHomeData();
        void getHomeDaEyeData(String url);
        void getHomeCCTVData(String url);
        void getHomeGuangYingData(String url);

    }
}
