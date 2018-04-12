package com.jchou.skinlibrary.skin.attrs;

import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.jchou.skinlibrary.skin.SkinManager;


public class TabLayoutAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof TabLayout) {
            Log.i("TabLayoutAttr", "apply");
            TabLayout tl = (TabLayout) view;
            if (isColorType()) {
                Log.i("TabLayoutAttr", "apply color");
                int color = SkinManager.getInstance().getColor(getAttrValueRefId());
                tl.setSelectedTabIndicatorColor(color);
            } else if (isDrawableType()) {
                Log.i("TabLayoutAttr", "apply drawable");
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }
}
