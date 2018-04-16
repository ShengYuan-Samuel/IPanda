package com.jiyun.ipanda;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application{

    public static AppCompatActivity context;

    @Override
    public void onCreate() {
        super.onCreate();
       UMConfigure.setLogEnabled(true);
       UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        PlatformConfig.setWeixin("wx8900016a806472a4","983caedf2e322262f84780d29e2281c4");
        PlatformConfig.setQQZone("1106715541", "qOPr22h1Wpi80wVQ");
    }
}
