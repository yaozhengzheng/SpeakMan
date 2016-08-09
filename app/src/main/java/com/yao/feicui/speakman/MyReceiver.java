package com.yao.feicui.speakman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    static int num = 0;

    static MyReceiver receiver;

    public static MyReceiver shareInstance() {
        if (receiver == null) {
            receiver = new MyReceiver();
        }
        return receiver;
    }

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        if (action.equals(Intent.ACTION_SCREEN_ON) || action.equals(Intent.ACTION_USER_PRESENT)) {
//            // 开屏
//
//            Log.i("++++++++>", "开屏");
//            if (num == 0) {
//                Intent i = new Intent(context, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//
//                Thread t = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        num = 0;
//                    }
//                });
//
//                t.start();
//            }
//            num++;
//        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
//
//            Log.i("++++++++>", "息屏");
//            // 息屏
////            if (!PreferenceHelper.getInstance(context).getBooleanValue("FIRST_SCREEN_OFF")) {
////
////                Intent i = new Intent(context, MainActivity.class);
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(i);
////                PreferenceHelper.getInstance(context).setBooleanValue("FIRST_SCREEN_OFF", true);
////            }
//
////            try {
////                Thread.sleep(4 * 1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////
////            Intent i = new Intent(context, MainActivity.class);
////            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(i);
//
//        }
//
//        Intent service = new Intent(context, MyService.class);
//        context.startService(service);
    }

}
