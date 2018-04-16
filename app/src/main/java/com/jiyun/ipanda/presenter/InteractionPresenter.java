package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.InteractionContract;
import com.jiyun.ipanda.model.biz.AppService;
import com.jiyun.ipanda.model.entity.InteractionIBeans;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InteractionPresenter implements InteractionContract.Presenter{
    private InteractionContract.View view;
    private final AppService appService;

    public InteractionPresenter(InteractionContract.View view) {
        this.view = view;
        appService = RetiofitUtils.getInstance().getAppService();
    }

    @Override
    public void getInteractionData() {
        appService.getInteractionData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InteractionIBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InteractionIBeans beans) {
                        view.showInteractionData(beans);
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
    public void attachView(InteractionContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
