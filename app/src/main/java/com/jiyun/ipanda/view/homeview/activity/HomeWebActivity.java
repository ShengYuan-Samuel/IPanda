package com.jiyun.ipanda.view.homeview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.ipanda.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeWebActivity extends AppCompatActivity {


    @BindView(R.id.home_fenlei_fanhui)
    ImageView homeFenleiFanhui;
    @BindView(R.id.home_pe_share_img)
    ImageView homePeShareImg;
    @BindView(R.id.home_webView)
    WebView homeWebView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        ButterKnife.bind(this);
        init();
    }



    protected void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        homeWebView.loadUrl(url);
        homeWebView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }

                });

    }




    @OnClick({R.id.home_fenlei_fanhui, R.id.home_pe_share_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_fenlei_fanhui:
                finish();
                break;
            case R.id.home_pe_share_img:
                UMImage image = new UMImage(HomeWebActivity.this, R.drawable.umeng_socialize_wechat);
                UMWeb umWeb = new UMWeb(url);
                umWeb.setTitle("我是熊猫");
                umWeb.setThumb(image);
                umWeb.setDescription("这是一个很好的应用");
                new ShareAction(HomeWebActivity.this).withText("hello").withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
        }
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(HomeWebActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HomeWebActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HomeWebActivity.this,"取消了",Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HomeWebActivity", "我爱你");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HomeWebActivity", "我想你");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HomeWebActivity", "homeWeb的Destroy");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
