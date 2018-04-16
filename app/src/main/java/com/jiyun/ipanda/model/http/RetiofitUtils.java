package com.jiyun.ipanda.model.http;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jiyun.ipanda.App;
import com.jiyun.ipanda.model.biz.AppService;
import com.jiyun.ipanda.model.biz.CCTVService;
import com.jiyun.ipanda.model.biz.ChinaLiveService;
import com.jiyun.ipanda.model.biz.HomeService;
import com.jiyun.ipanda.model.biz.PandaEyeService;
import com.jiyun.ipanda.model.biz.PandaLiveService;
import com.jiyun.ipanda.model.config.Url;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetiofitUtils {

    private static RetiofitUtils retiofitUtils;
    private final Retrofit retrofit;

    public RetiofitUtils() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (TextUtils.isEmpty(response.cacheControl().toString())){
                    return response.newBuilder().addHeader("Cache-Control", "max-age=60*60*24").build();
                }
                return response;
            }
        };
        Cache cache = new Cache(App.context.getCacheDir(), 1024 * 1024 * 20);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addNetworkInterceptor(interceptor).build();
                    retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(Url.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static RetiofitUtils getInstance(){
        if (retiofitUtils == null){
            synchronized (RetiofitUtils.class){
                if (retiofitUtils == null)
                    retiofitUtils = new RetiofitUtils();
            }
        }
        return retiofitUtils;
    }
     public HomeService getHomeService(){
        return retrofit.create(HomeService.class);
     }
     public PandaEyeService getPandaEyeService(){
        return retrofit.create(PandaEyeService.class);
     }

     public PandaLiveService getPandaLiveService(){
        return retrofit.create(PandaLiveService.class);
     }

     public CCTVService getCCTVService(){
        return retrofit.create(CCTVService.class);
     }

     public ChinaLiveService getChinaLiveService(){
        return retrofit.create(ChinaLiveService.class);
     }

     public AppService getAppService(){
        return retrofit.create(AppService.class);
     }
}
