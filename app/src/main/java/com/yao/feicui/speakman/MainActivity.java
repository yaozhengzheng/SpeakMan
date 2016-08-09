package com.yao.feicui.speakman;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.update.PgyUpdateManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener {


    private Timer mTimer;
    int i = -1;
    ImageView v;

    private List<String> paths = new ArrayList<String>();

    PowerManager.WakeLock wl;


    private static final String IMG_TAG  = "IMG_TAG";
    private static final String FILE_PATH_NAME = "launchad5";


    private boolean continus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v = (ImageView) findViewById(R.id.id_parent);
        v.setOnClickListener(this);
        try {
            readIMGSFromSD();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            wakeUpAndUnlock();
        } catch (Exception e) {
            e.printStackTrace();
        }

        i = PreferenceHelper.getInstance(this).getIntValue(IMG_TAG);
        Log.i("img is:",i+"");

        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                i++;
                mHandler.sendEmptyMessage(0);
            }
        },0,1000*3);

//        Intent service = new Intent(this, MyService.class);
//        startService(service);
        PgyUpdateManager.register(this);


        this.listnerHome();
    }







    private void listnerHome(){
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Process logcatProcess = null;
                BufferedReader bufferedReader = null;
                try
                {
                                /** 获取系统logcat日志信息 */
                    logcatProcess = Runtime.getRuntime().exec(new String[] {"logcat", "ActivityManager:I *:S"});
                    bufferedReader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        if (line.indexOf("cat=[android.intent.category.HOME]") > 0)
                        {
                            /** 这里可以处理你对点击Home的操作哦 我这里是完全退出应用*/
                            System.exit(0);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 从SD卡读取图片
     */
    private void readIMGSFromSD() {
        String path = Environment.getExternalStorageDirectory() + "/" + FILE_PATH_NAME;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        for (File file : f.listFiles()) {
            if (file.getName().toLowerCase().endsWith("jpg") || file.getName().toLowerCase().endsWith("jpeg") || file
                    .getName().toLowerCase().endsWith("png")) {
                Log.i("图片路径", path + "/" + file.getName());
                try {

                    paths.add(path + "/" + file.getName());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (paths.size() > 0) {
            if (i >= paths.size()){
                i = 0;
            }
            Bitmap bm = BitmapFactory.decodeFile(paths.get(i));
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inSampleSize = 5;
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            v.setImageBitmap(bm);
        } else {
            mHandler.sendEmptyMessage(i);
//            v.setImageResource(R.mipmap.a0);
        }


    }


    private void wakeUpAndUnlock() {
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager
                .SCREEN_DIM_WAKE_LOCK, "bright");
    }


    @Override
    protected void onResume() {
        super.onResume();
        continus = true;
        try {
            wl.acquire();
        } catch (Exception e) {
            e.printStackTrace();
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        continus = false;
        try {
            wl.release();
        } catch (Exception e) {
            e.printStackTrace();
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (paths.size() == 0) {

                if (i > 8){
                    i=0;
                }
                PreferenceHelper.getInstance(MainActivity.this).setIntValue(IMG_TAG,i);

                if (i == 0) {
                    v.setImageResource(R.mipmap.a1);
                } else if (i == 1) {
                    v.setImageResource(R.mipmap.a2);
                } else if (i == 2) {
                    v.setImageResource(R.mipmap.a3);
                } else if (i == 3) {
                    v.setImageResource(R.mipmap.a4);
                } else if (i == 4) {
                    v.setImageResource(R.mipmap.a5);
                } else if (i == 5) {
                    v.setImageResource(R.mipmap.a6);
                } else if (i == 6) {
                    v.setImageResource(R.mipmap.a7);
                } else if (i == 7) {
                    v.setImageResource(R.mipmap.a8);
                } else if (i == 8) {
                    v.setImageResource(R.mipmap.a9);
                } else {
                    MainActivity.this.finish();
                }

            } else {
                try {
                    if (i >= paths.size()){
                        i = 0;
                    }
                    String s = paths.get(i);
                    PreferenceHelper.getInstance(MainActivity.this).setIntValue(IMG_TAG,i);
                    Bitmap bm = BitmapFactory.decodeFile(s);
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 5;
                    opt.inPreferredConfig = Bitmap.Config.RGB_565;
                    opt.inPurgeable = true;
                    opt.inInputShareable = true;
                    v.setImageBitmap(bm);
                } catch (Exception e) {
                    MainActivity.this.finish();
                }
            }


        }
    };


    @Override
    protected void onDestroy() {

        if (mTimer != null) {
            mTimer.cancel();
        }

        Intent service = new Intent(this, QuitService.class);
        startService(service);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        PreferenceHelper.getInstance(MainActivity.this).setIntValue(IMG_TAG,i);
        Intent intent = new Intent(this, SpashService.class);
        startService(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){

        }else if (keyCode == KeyEvent.KEYCODE_HOME){
            Log.i("|||||","======");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
