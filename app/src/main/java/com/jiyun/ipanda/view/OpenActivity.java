package com.jiyun.ipanda.view;

import android.content.Intent;
import android.os.Handler;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseActivity;

public class OpenActivity extends BaseActivity {
    @Override
    protected int getLayOutId() {
        return R.layout.activity_open;
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OpenActivity.this, MainActivity.class));
            }
        }, 2000);
    }

    @Override
    protected void loadData() {

    }
}