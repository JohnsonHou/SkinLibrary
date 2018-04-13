package com.jchou.skinloader.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jchou.skinlibrary.skin.SkinInflaterFactory;
import com.jchou.skinlibrary.skin.SkinManager;
import com.jchou.skinlibrary.skin.attrs.DynamicAttr;
import com.jchou.skinlibrary.skin.listener.IDynamicNewView;
import com.jchou.skinlibrary.skin.listener.ISkinUpdate;
import com.jchou.skinloader.widget.status.StatusBarUtil;

import java.util.List;

public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate,IDynamicNewView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().attach(this);
        changeStatusColor();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    //是否需要自动切换StatusBar颜色,默认需要
    protected boolean isNeedStatusBarAutoChange(){
        return true;
    }
    
    // 当前Activity是否需要响应皮肤更改需求
    protected boolean isResponseOnSkinChanging(){
        return true;
    }
    private SkinInflaterFactory mSkinInflaterFactory;
    
      //更新主题
    @Override
    public void onThemeUpdate(){
        Log.i("SkinBaseActivity", "onThemeUpdate");
        if (!isResponseOnSkinChanging()) {
            return;
        }
        mSkinInflaterFactory.applySkin();
        changeStatusColor();
        
    }

    //改变状态栏着色，这里使用的是一个第三方
    public void changeStatusColor() {
        
        if (!isNeedStatusBarAutoChange())return;
        int color = SkinManager.getInstance().getColorStatusBar();
        Log.i("statusBarColor",color+"");
        if (color != -1) {
            StatusBarUtil.setColor(this, color);
        }else {
            StatusBarUtil.setColor(this, Color.WHITE);
        }
    }


    /** 
     * 目前支持的属性：
     * background
     * textColor
     * tabIndicatorColor
     * contentScrimColor
     * backgroundTint
     * navigationViewMenu
     * src
     * thumb
     */
    
    //动态的添加有皮肤更改需求的View。
    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }
    
    //动态的添加有皮肤更改需求的View。如：dynamicAddSkinEnableView(mToolbar, "background", R.color.colorPrimary);
    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }
}