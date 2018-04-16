package com.jiyun.ipanda.view.pandaeyeview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PandaEyeWebActivity extends BaseActivity {


    @BindView(R.id.pandaeyeweb_finsh)
    ImageView pandaeyewebFinsh;
    @BindView(R.id.collect_bo_image)
    ImageView collectBoImage;
    @BindView(R.id.pandaeye_shape_image)
    ImageView pandaeyeShapeImage;
    @BindView(R.id.pandaeye_webView)
    WebView pandaeyeWebView;
    private int count = 0;

    @Override
    protected int getLayOutId() {
        return R.layout.activity_panda_eye_web;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("PandaEyeWebActivity", url);
        pandaeyeWebView.loadUrl(url);
        pandaeyeWebView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }

                });

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.pandaeyeweb_finsh, R.id.collect_bo_image, R.id.pandaeye_shape_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pandaeyeweb_finsh:
                finish();
                break;
            case R.id.collect_bo_image:
                count++;
                if (count % 2 == 0) {
                    collectBoImage.setImageResource(R.mipmap.collect_no);
                    Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show();

                } else {
                    collectBoImage.setImageResource(R.mipmap.collect_yes);
                    Toast.makeText(this, "已添加，请到[我的收藏]中查看", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.pandaeye_shape_image:
                break;
        }
    }


}
