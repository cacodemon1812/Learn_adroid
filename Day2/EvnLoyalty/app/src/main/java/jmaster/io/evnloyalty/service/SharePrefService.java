package jmaster.io.evnloyalty.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefService {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_AUTHEN = "isAuthenticated";

    final SharedPreferences sharedpreferences;

    public SharePrefService(Context context) {
        this.sharedpreferences = context.getSharedPreferences("MySession", Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        sharedpreferences.edit().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        sharedpreferences.edit().putInt(key, value).commit();
    }

    public void putLong(String key, long value) {
        sharedpreferences.edit().putLong(key, value).commit();
    }

    public void remove(String key) {
        sharedpreferences.edit().remove(key).commit();
    }

    public String getString(String key) {
        return sharedpreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedpreferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return sharedpreferences.getLong(key, 0L);
    }
}
