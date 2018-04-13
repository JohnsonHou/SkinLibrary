package com.jchou.skinloader.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jchou.skinlibrary.skin.SkinManager;
import com.jchou.skinlibrary.skin.listener.ILoaderListener;
import com.jchou.skinlibrary.skin.util.SkinUtil;
import com.jchou.skinloader.R;
import com.jchou.skinloader.ui.base.SkinBaseActivity;

import java.io.File;
import java.util.List;

/**
 * Created by Johnson on 2018/2/14.
 */

public class MainActivity extends SkinBaseActivity {

    @Override
    protected boolean isNeedStatusBarAutoChange() {
        return true;
    }
    @Override
    protected boolean isResponseOnSkinChanging() {
        return true;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态添加换肤需求
        dynamicAddSkinEnableView(findViewById(R.id.tv), "background", R.color.bg_btn_color);
        //为了体现换肤效果，一开始使用默认皮肤
        SkinManager.getInstance().restoreDefaultTheme();

    }


    public void clickBtn(View view){
        //换肤
        changeSkin();
    }

    public void clickText(View view){
        //使用默认皮肤
        SkinManager.getInstance().restoreDefaultTheme();
    }

    private void changeSkin() {
        //目录地址：/storage/emulated/0/Android/data/com.jchou.mytodo/cache/skin/skin_black.skin
        String skinFullName = SkinUtil.getSkinDirPath(getApplicationContext()) + File.separator + "skin_black.skin";
        //从assets/skin_black.skin--->缓存
        SkinUtil.moveRawToDir(getApplicationContext(), "skin/skin_black.skin", skinFullName);
        File skin = new File(skinFullName);
        if (!skin.exists()) {
            Toast.makeText(getApplicationContext(),"请检查" + skinFullName + "是否存在",Toast.LENGTH_SHORT).show();
            return;
        }
        SkinManager.getInstance().load(skin.getAbsolutePath(),
                new ILoaderListener() {
                    @Override
                    public void onStart() {
                        Log.e("jc", "loadSkinStart");
                    }

                    @Override
                    public void onSuccess() {
                        Log.e("jc", "loadSkinSuccess");
                        Toast.makeText(getApplicationContext(),"切换成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Log.e("jc", "loadSkinFail");
                        Toast.makeText(getApplicationContext(),"切换失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
