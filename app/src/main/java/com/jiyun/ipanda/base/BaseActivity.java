package com.jiyun.ipanda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{
    private BaseFragment lastFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayOutId());
        ButterKnife.bind(this);
        App.context = this;
        init();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
       App.context = null;
    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        App.context = this;
    }*/
    /*
    @Override
    protected void onPause() {
        super.onPause();
        App.context = this;
    }*/



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //App.context = null;
        Log.d("HomeWebActivity", "Base的Destroy");
    }

    //这是进行id更新的
    protected abstract int getLayOutId();
    //这是初始化组件的
    protected abstract void init();
    //这是更新数据的
    protected abstract void loadData();

    //这是统一管理fragment的

    public BaseFragment setContentView(Class<? extends BaseFragment> classFragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String simpleName = classFragment.getSimpleName();
        BaseFragment fragmentByTag = (BaseFragment) manager.findFragmentByTag(simpleName);
            try {
                if (fragmentByTag == null){
                    fragmentByTag = classFragment.newInstance();
                    transaction.add(R.id.fl_content,fragmentByTag,simpleName);
                }
                if (lastFragment != null){
                    transaction.hide(lastFragment);
                }
                transaction.show(fragmentByTag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            lastFragment = fragmentByTag;

            transaction.commit();
            return fragmentByTag;
    }
   //这个方法是用来同意管理布局的
/*   protected BaseFragment setContentView(Class<? extends BaseFragment> fragmentClass) {
       FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction transaction = fragmentManager.beginTransaction();
       //获取Fragment的类名 用作Tag
       String fragmentName = fragmentClass.getSimpleName();
        *//*
        1.如何创建fragment对象
        2.创建过的就直接查询,如果没有创建就直接查询
         *//*
       //根据Tag值来查找Fragment
       BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentName);
       try {
           if (fragment == null) {
               fragment = fragmentClass.newInstance();
               //如果fragment等于null代表没有创建  采用java的代理进行添加
               transaction.add(R.id.fl_content, fragment,fragmentName);
           }
           //隐藏上一个fragment
           if (lastFragment != null) {
               transaction.hide(lastFragment);
           }
           transaction.show(fragment);
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }
       lastFragment = fragment;
       transaction.commit();
       return fragment;
   }*/
}
