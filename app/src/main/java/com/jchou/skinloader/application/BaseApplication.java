package com.jchou.skinloader.application;

import android.app.Application;

import com.jchou.skinlibrary.skin.SkinManager;

/**
 * Created by Johnson on 2018/4/13.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSkinLoader();
    }

    private void initSkinLoader() {
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
