package com.jiyun.ipanda.model.biz;

import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVBeans;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ChinaLiveService {
    @GET(Url.CHINALIVE_URL)
    Observable<CCTVBeans> getChinaLiveData();

    @GET
    Observable<CCTVItemBean> getChinaLivetemData(@retrofit2.http.Url String url);
}
