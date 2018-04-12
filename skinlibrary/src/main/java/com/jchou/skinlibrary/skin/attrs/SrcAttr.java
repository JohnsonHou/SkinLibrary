package com.jchou.skinlibrary.skin.attrs;

import android.view.View;
import android.widget.ImageView;

public class SrcAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (isDrawableType() && view instanceof ImageView) {
            applyImageDrawable(view);
        }
    }
}