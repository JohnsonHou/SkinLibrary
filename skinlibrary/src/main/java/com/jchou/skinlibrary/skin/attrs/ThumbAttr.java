package com.jchou.skinlibrary.skin.attrs;

import android.view.View;
import android.widget.SeekBar;

import com.jchou.skinlibrary.skin.SkinManager;


public class ThumbAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (isDrawableType() && view instanceof SeekBar) {
            ((SeekBar) view).setThumb(SkinManager.getInstance().getDrawable(getAttrValueRefId()));
        }
    }
}