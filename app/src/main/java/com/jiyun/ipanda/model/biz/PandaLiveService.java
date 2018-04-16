package com.jiyun.ipanda.model.biz;


import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveBeans;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PandaLiveService {
    @GET(Url.PANDALIVE_URL)
    Observable<PandaLiveBeans> getPandaLiveData();
    @GET
    Observable<PandaLiveItemBean> getPandaLiveItemData(@retrofit2.http.Url String url);
    @GET
    Observable<MultiViewBean> getPandaLiveMultiData(@retrofit2.http.Url String url);
}
