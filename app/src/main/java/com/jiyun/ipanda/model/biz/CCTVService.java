package com.jiyun.ipanda.model.biz;

import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctventity.PinDaoBeans;
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CCTVService {
    @GET(Url.CCTV_URL)
    Observable<CCTVBean> getCCTVData();

    @GET
    Observable<PinDaoBeans> getPinDaoData(@retrofit2.http.Url String url);
    @GET
    Observable<YangShiBean> getYangShiData(@retrofit2.http.Url String url);

}
