/**
 * 
 */
package com.yao.feicui.speakman;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author 韩大发
 * @time 2012-5-6 下午07:33:57
 * @email smile-forget-song@foxmail.com
 * @summary Brain v1.0
 */
public class PreferenceHelper {
	private static final String TAG = "PreferenceHelper";
	private static PreferenceHelper instance = null;


	SharedPreferences settings;

	private PreferenceHelper(Context context) {
		settings = context.getApplicationContext().getSharedPreferences(
				"settings", 0);
	}

	public static PreferenceHelper getInstance(Context context) {
		if (instance == null) {
			instance = new PreferenceHelper(context);
		}
		return instance;

	}

	public void setIntValue(String flag, int value) {
		Log.i(TAG, "setIntValue");
		settings.edit().putInt(flag, value).commit();
	}

	public int getIntValue(String flag) {
		Log.i(TAG, "getIntValue");
		return settings.getInt(flag, 0);
	}

	public void setStringValue(String flag, String value) {
		settings.edit().putString(flag, value).commit();
	}

	public String getStringValue(String flag) {
		return settings.getString(flag, "").trim();
	}

	public void setBooleanValue(String flag, Boolean value) {
		settings.edit().putBoolean(flag, value).commit();
	}

	public Boolean getBooleanValue(String flag) {
		return settings.getBoolean(flag, false);
	}

	public void clear() {
		settings.edit().clear().commit();
	}


}
