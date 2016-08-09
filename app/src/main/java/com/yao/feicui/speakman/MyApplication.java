package com.yao.feicui.speakman;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;


/**
 * 作者: 韩大发.
 * 邮箱: handafa@126.com
 * 时间: 15/11/25 14:25
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register(this);
    }
}
