package com.jiyun.ipanda.presenter;

import com.jiyun.ipanda.contract.PandaEyeContract;
import com.jiyun.ipanda.model.biz.PandaEyeService;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeBeans;
import com.jiyun.ipanda.model.entity.pandaeyeentity.PandaEyeItemBean;
import com.jiyun.ipanda.model.http.RetiofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PandaEyePresenter implements PandaEyeContract.Presenter{
    private PandaEyeContract.View view;
    private  PandaEyeService pandaEyeService;

    public PandaEyePresenter() {
        pandaEyeService = RetiofitUtils.getInstance().getPandaEyeService();
    }

    @Override
    public void getPandaEyeData() {
        pandaEyeService.getPandaEyeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PandaEyeBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PandaEyeBeans pandaEyeBeans) {
                            view.showPandaEyeData(pandaEyeBeans);
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
    public void getPandaEyeItemBean(String url) {
        pandaEyeService.getPandaEyeItemBean(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PandaEyeItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PandaEyeItemBean pandaEyeItemBean) {
                            view.showPandaEyeItemData(pandaEyeItemBean);
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
    public void attachView(PandaEyeContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
