package com.jiyun.ipanda.contract;

import com.jiyun.ipanda.base.BasePresenter;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctventity.PinDaoBeans;
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;

import retrofit2.http.GET;

public interface CCTVContract {

    public interface View{
        void showCCTVData(CCTVBean cctvBean);
        void showPinDaoData(PinDaoBeans pinDaoBeans);
        void showYangShiData(YangShiBean yangShiBean);
    }

    public interface Presenter extends BasePresenter<View>{
        void getCCTVData();
        void getPinDaoData(String url);
        void getYangShiData(String url);
    }
}
