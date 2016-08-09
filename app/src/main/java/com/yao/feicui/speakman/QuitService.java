package com.yao.feicui.speakman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class QuitService extends Service {
    public QuitService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        try {
//            Thread.sleep(4 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Intent i = new Intent(this, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
        return super.onStartCommand(intent, flags, startId);
    }
}
