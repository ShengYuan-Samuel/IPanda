package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.ChinaLiveItemContract;
import com.jiyun.ipanda.model.biz.ChinaLiveService;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVItemBean;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChinaLiveItemPresenter implements ChinaLiveItemContract.Presenter {

    private ChinaLiveItemContract.View view;
    private final ChinaLiveService chinaLiveService;

    public ChinaLiveItemPresenter() {
        chinaLiveService = RetiofitUtils.getInstance().getChinaLiveService();
    }

    @Override
    public void getChinaLiveItemData(String url) {
        chinaLiveService.getChinaLivetemData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CCTVItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CCTVItemBean cctvItemBean) {
                            view.showChinaLiveItemData(cctvItemBean);
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
    public void attachView(ChinaLiveItemContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
