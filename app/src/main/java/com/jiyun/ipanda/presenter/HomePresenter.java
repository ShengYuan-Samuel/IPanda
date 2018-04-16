package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.HomeContract;
import com.jiyun.ipanda.model.biz.HomeService;
import com.jiyun.ipanda.model.entity.homeentity.HomeBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomeCCTVBeans;
import com.jiyun.ipanda.model.entity.homeentity.HomePandaEyeBean;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View view;
    private HomeService homeService;

    public HomePresenter() {
        homeService = RetiofitUtils.getInstance().getHomeService();
    }
    @Override
    public void getHomeData() {
        homeService.getHomeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HomeBeans homeBeans) {
                        view.showHomeData(homeBeans);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    @Override
    public void getHomeDaEyeData(String url) {
        homeService.getHomeDaEyeData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePandaEyeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HomePandaEyeBean homePandaEyeBean) {
                        if (homePandaEyeBean != null)
                        view.showHomePandaEyeData(homePandaEyeBean);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getHomeCCTVData(String url) {
        homeService.getHomeCCTVData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeCCTVBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeCCTVBeans homeCCTVBeans) {
                        view.showHomeCCTVData(homeCCTVBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHomeGuangYingData(String url) {
            homeService.getHomeGuangYingData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<HomePandaEyeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(HomePandaEyeBean homePandaEyeBean) {
                    view.showHomeGuangYingData(homePandaEyeBean);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
