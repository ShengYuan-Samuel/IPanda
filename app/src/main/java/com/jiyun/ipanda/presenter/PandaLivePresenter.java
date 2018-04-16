package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.HomeContract;
import com.jiyun.ipanda.contract.PandaLiveContract;
import com.jiyun.ipanda.model.biz.PandaLiveService;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveBeans;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PandaLivePresenter implements PandaLiveContract.Persenter{
    private PandaLiveContract.View view;
    private final PandaLiveService pandaLiveService;

    public PandaLivePresenter() {
        pandaLiveService = RetiofitUtils.getInstance().getPandaLiveService();
    }

    @Override
    public void getPandaLiveData() {
        pandaLiveService.getPandaLiveData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PandaLiveBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PandaLiveBeans pandaLiveBeans) {
                        view.showPandaLiveData(pandaLiveBeans);
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
    public void attachView(PandaLiveContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view =null;

    }
}
