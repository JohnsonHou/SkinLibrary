package com.jchou.skinlibrary.skin.attrs;

import android.view.View;
import android.widget.TextView;

import com.jchou.skinlibrary.skin.util.L;


public class TextColorAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (isColorType() && view instanceof TextView) {
            applyTextColor(view);
            L.i("applyAttr", "TextColorAttr");
        }
    }
}
