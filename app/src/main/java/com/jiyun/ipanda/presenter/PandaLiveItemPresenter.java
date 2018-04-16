package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.PandaLiveItemContract;
import com.jiyun.ipanda.model.biz.PandaLiveService;
import com.jiyun.ipanda.model.entity.pandaliveentity.MultiViewBean;
import com.jiyun.ipanda.model.entity.pandaliveentity.PandaLiveItemBean;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PandaLiveItemPresenter implements PandaLiveItemContract.Persenter{
    private PandaLiveItemContract.View view;
    private final PandaLiveService pandaLiveService;

    public PandaLiveItemPresenter( ) {
        pandaLiveService = RetiofitUtils.getInstance().getPandaLiveService();
    }

    @Override
    public void getPandaLiveItemData(String url) {
        pandaLiveService.getPandaLiveItemData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PandaLiveItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PandaLiveItemBean pandaLiveItemBean) {
                        view.showPandaLiveItemData(pandaLiveItemBean);
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
    public void getPandaLiveMultiData(String url) {
        pandaLiveService.getPandaLiveMultiData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MultiViewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MultiViewBean multiViewBean) {
                        view.showPandaLiveMultiData(multiViewBean);
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
    public void attachView(PandaLiveItemContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
