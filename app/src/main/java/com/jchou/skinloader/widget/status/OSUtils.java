package com.jchou.skinloader.widget.status;

import android.text.TextUtils;

import java.lang.reflect.Method;


public class OSUtils {

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_DISPLAY = "ro.build.display.id";

    public static String MIUIVersion() {
        return isMIUI() ? getSystemProperty(KEY_MIUI_VERSION_NAME, null) : "";
    }

    public static boolean isMIUI() {
        String property = getSystemProperty(KEY_MIUI_VERSION_NAME, null);
        return !TextUtils.isEmpty(property);
    }

    public static boolean isFlymeOS() {
        return getMeizuFlymeOSFlag().toLowerCase().contains("flyme");
    }

    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty(KEY_DISPLAY, "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
