package com.yao.feicui.speakman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {
    public LaunchReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {

        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);


//        Handler mHandler = new Handler();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent service = new Intent(context, MyService.class);
//                context.startService(service);
//            }
//        }, 1000 * 10 * 2);
    }
}
