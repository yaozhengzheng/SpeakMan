package com.yao.feicui.speakman;

import android.content.Context;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者: 韩大发.
 * 邮箱: handafa@126.com
 * 时间: 15/11/17 22:11
 */
public class SDCardUtil {
    private Context context;

    private boolean hasCard = false; //判断是否存在SD卡

    private String sdPath = null;
    private String filePath = null;

    /**
     * 构造函数
     */
    public SDCardUtil(Context context) {
        this.context = context;

        hasCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        try {
            sdPath = Environment.getExternalStorageDirectory().getCanonicalPath();
            filePath = this.context.getFilesDir().getPath();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("sdPath = " + sdPath);
        System.out.println("filePath = " + filePath);
    }


    public String getSdPath() {
        return sdPath;
    }

    public void setSdPath(String sdPath) {
        this.sdPath = sdPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isOk() {
        return hasCard;
    }

    /**
     * 将文件写入SD卡内
     */
    public int readStreamToSDCard(InputStream is, String fileName) {
        int state = -1;
        try {
            FileOutputStream fos = new FileOutputStream(sdPath + "/" + fileName);
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = is.read(buffer)) != -1) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();

            state = 0;
            return state;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
