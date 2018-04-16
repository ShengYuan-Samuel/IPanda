package com.jiyun.ipanda.model.biz;

import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomeCCTVBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomePandaEyeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomeService {
    @GET(Url.HOMESERVICE_URL)
    Observable<HomeBeans> getHomeData();
    @GET
    Observable<HomePandaEyeBean> getHomeDaEyeData(@retrofit2.http.Url String url);
    @GET
    Observable<HomeCCTVBeans> getHomeCCTVData(@retrofit2.http.Url String url);
    @GET
    Observable<HomePandaEyeBean> getHomeGuangYingData(@retrofit2.http.Url String url);


}
