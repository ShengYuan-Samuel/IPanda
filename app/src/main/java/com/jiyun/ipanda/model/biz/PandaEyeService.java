package com.jiyun.ipanda.model.biz;



import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeBeans;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PandaEyeService {
    @GET(Url.PANDAEYE_URL)
    Observable<PandaEyeBeans> getPandaEyeData();

    //这是引用的适配器
    @GET
    Observable<PandaEyeItemBean> getPandaEyeItemBean(@retrofit2.http.Url String url);


}
