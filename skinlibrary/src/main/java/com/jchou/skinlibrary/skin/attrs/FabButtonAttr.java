package com.jchou.skinlibrary.skin.attrs;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jchou.skinlibrary.skin.SkinManager;


public class FabButtonAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof FloatingActionButton) {
            FloatingActionButton fb = (FloatingActionButton) view;
            if (isColorType()) {
                int color = SkinManager.getInstance().getColor(getAttrValueRefId());
                fb.setBackgroundTintList(ColorStateList.valueOf(color));
            } else if (isDrawableType()) {
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }
}
