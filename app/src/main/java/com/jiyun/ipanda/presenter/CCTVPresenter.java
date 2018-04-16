package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.CCTVContract;
import com.jiyun.ipanda.model.biz.CCTVService;
import com.jiyun.ipanda.model.entity.cctventity.CCTVBean;
import com.jiyun.ipanda.model.entity.cctventity.PinDaoBeans;
import com.jiyun.ipanda.model.entity.cctventity.YangShiBean;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CCTVPresenter implements CCTVContract.Presenter{

    private CCTVContract.View view;
    private final CCTVService cctvService;

    public CCTVPresenter() {
        cctvService = RetiofitUtils.getInstance().getCCTVService();

    }

    @Override
    public void getCCTVData() {
        cctvService.getCCTVData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CCTVBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CCTVBean cctvBean) {
                        view.showCCTVData(cctvBean);
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
    public void getPinDaoData(String url) {
       cctvService.getPinDaoData(url)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<PinDaoBeans>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(PinDaoBeans pinDaoBeans) {
                    view.showPinDaoData(pinDaoBeans);
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
    public void getYangShiData(String url) {
    cctvService.getYangShiData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<YangShiBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(YangShiBean yangShiBean) {
                    view.showYangShiData(yangShiBean);
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
    public void attachView(CCTVContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
