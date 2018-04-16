package com.jiyun.ipanda.model.biz;

import com.jiyun.ipanda.model.config.Url;
import com.jiyun.ipanda.model.entity.InteractionIBeans;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AppService {
    @GET(Url.INTERACTION_URL)
    Observable<InteractionIBeans> getInteractionData();
}
