package com.jiyun.ipanda.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = getPresenter();
        if (presenter != null){
            presenter.attachView(this);
        }
        init();
        loadData();
    }
    //这是统一加载布局的
    protected abstract int getLayoutId();
    //这是统一加载初始化组件的方法
    protected abstract void init();
    //这是统一加载数据的
    protected abstract void loadData();
    /**
     * 更新标题
     */
    public abstract void updateTitle();
    private T getPresenter(){
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass.equals(BaseFragment.class))
            return null;
        Type[] arguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        if (arguments.length == 0){
            return null;
        }
//        Class<T> aClass = (Class<T>) arguments[0].getClass();
        Class<T> aClass = (Class<T>) arguments[0];
        try {
            T t = aClass.newInstance();
            return t;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
       // App.context  = ((BaseActivity)getActivity());
        if (presenter != null){
            presenter.attachView(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
      if (presenter != null){
            presenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
