package com.jchou.skinlibrary.skin.attrs;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;

import com.jchou.skinlibrary.skin.SkinManager;


public class NavigationViewAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof NavigationView) {
            Log.i("TabLayoutAttr", "apply");
            NavigationView nv = (NavigationView) view;
            if (isColorType()) {
                Log.i("TabLayoutAttr", "apply color");
                int color = SkinManager.getInstance().getColor(getAttrValueRefId());
                nv.setItemTextColor(createSelector(color));
                nv.setItemIconTintList(createSelector(color));
            } else if (isDrawableType()) {
                Log.i("TabLayoutAttr", "apply drawable");
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }

    private ColorStateList createSelector(int color) {
        int statePressed = android.R.attr.state_checked;
        int stateChecked = android.R.attr.state_checked;
        int[][] state = {{statePressed}, {-statePressed}, {stateChecked}, {-stateChecked}};
        int color1 = color;
        int color2 = Color.parseColor("#6E6E6E");
        int color3 = color;
        int color4 = Color.parseColor("#6E6E6E");
        int[] colors = {color1, color2, color3, color4};
        ColorStateList colorStateList = new ColorStateList(state, colors);
        return colorStateList;
    }
}
