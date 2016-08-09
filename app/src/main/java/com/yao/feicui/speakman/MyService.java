package com.yao.feicui.speakman;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;



public class MyService extends Service {

    MyReceiver mScreenReceiver;
//    private SensorManager sensorManager;
//    private Sensor sensor;
//    private static final int SPEED_SHRESHOLD = 500;
//    //手机上一个位置时重力感应坐标
//    private float lastX;
//    private float lastY;
//    private float lastZ;
//    private static final int UPTATE_INTERVAL_TIME = 70;
//    //上次检测时间
//    private long lastUpdateTime;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerListener();
//        ShakeListener listener = new ShakeListener(this);
//        listener.setOnShakeListener(new ShakeListener.OnShakeListener() {
//            @Override
//            public void onShake() {
//                Intent i = new Intent(MyService.this, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//            }
//        });
//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        if (sensorManager != null) {
//            //获得重力传感器
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        }
//
//        //注册
//        if (sensor != null) {
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
//        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void registerListener() {
        mScreenReceiver = MyReceiver.shareInstance();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
    }

    @Override
    public void onDestroy() {
        Intent service = new Intent(this, MyService.class);
        startService(service);
        super.onDestroy();
//        unregisterReceiver(mScreenReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        //现在检测时间
//        long currentUpdateTime = System.currentTimeMillis();
//        //两次检测的时间间隔
//        long timeInterval = currentUpdateTime - lastUpdateTime;
//        //判断是否达到了检测时间间隔
//        if (timeInterval < UPTATE_INTERVAL_TIME)
//            return;
//        //现在的时间变成last时间
//        lastUpdateTime = currentUpdateTime;
//
//        //获得x,y,z坐标
//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];
//
//        //获得x,y,z的变化值
//        float deltaX = x - lastX;
//        float deltaY = y - lastY;
//        float deltaZ = z - lastZ;
//
//        //将现在的坐标变成last坐标
//        lastX = x;
//        lastY = y;
//        lastZ = z;
//
//        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / timeInterval * 10000;
//        //达到速度阀值，发出提示
//        if (speed >= SPEED_SHRESHOLD) {
//            Intent i = new Intent(MyService.this, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//        }
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }
}
