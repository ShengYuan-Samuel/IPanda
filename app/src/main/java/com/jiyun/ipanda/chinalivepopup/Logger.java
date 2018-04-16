package com.jiyun.ipanda.chinalivepopup;


import android.util.Log;

public class Logger {
    public static final String TAG = "gjj";
    public static void i(String content){
        Log.i(TAG,content);
    }
    public static void d(String content){
        Log.d(TAG,content);
    }
    public static void e(String content){
        Log.e(TAG,content);
    }
}
