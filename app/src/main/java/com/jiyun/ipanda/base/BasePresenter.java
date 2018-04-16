package com.jiyun.ipanda.base;

public interface BasePresenter<T> {
    void attachView(T t);
    void detachView();


}
