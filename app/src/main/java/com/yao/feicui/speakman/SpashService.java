package com.yao.feicui.speakman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SpashService extends Service {

    public SpashService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            Thread.sleep(1 * 1000);

            Intent in = new Intent(this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(in);

        } catch (InterruptedException e) {
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
