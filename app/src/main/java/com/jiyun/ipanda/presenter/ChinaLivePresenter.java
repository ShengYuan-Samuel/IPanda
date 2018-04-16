package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.ChinaLiveContract;
import com.jiyun.ipanda.model.biz.ChinaLiveService;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVBeans;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChinaLivePresenter implements ChinaLiveContract.Presenter{
    private ChinaLiveContract.View view;
    private final ChinaLiveService chinaLiveService;

    public ChinaLivePresenter() {
        chinaLiveService = RetiofitUtils.getInstance().getChinaLiveService();

    }

    @Override
    public void getChinaLiveData() {
        chinaLiveService.getChinaLiveData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CCTVBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CCTVBeans cctvBean) {
                        view.showChinaLiveData(cctvBean);
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
    public void attachView(ChinaLiveContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
