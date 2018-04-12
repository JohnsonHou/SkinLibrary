package com.jchou.skinlibrary.skin.attrs;

import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;

import com.jchou.skinlibrary.skin.SkinManager;


public class CollapsingToolbarLayoutAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof CollapsingToolbarLayout) {
            Log.i("CollapsingToolbarAttr", "apply");
            CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) view;
            if (isColorType()) {
                Log.i("CollapsingToolbarAttr", "apply color");
                int color = SkinManager.getInstance().getColor(getAttrValueRefId());
                ctl.setContentScrimColor(color);
                ctl.setBackgroundColor(color);
            } else if (isDrawableType()) {
                Log.i("CollapsingToolbarAttr", "apply drawable");
            }
        }
    }
}
