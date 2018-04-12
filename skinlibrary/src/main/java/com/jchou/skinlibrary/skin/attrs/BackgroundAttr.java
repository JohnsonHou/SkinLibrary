package com.jchou.skinlibrary.skin.attrs;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.SeekBar;

import com.jchou.skinlibrary.skin.util.L;


public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof SeekBar) {
            L.e("这里seekbar");
            return;
        }
        if (isColorType()) {
            if (view instanceof CardView) {//这里对CardView特殊处理下
                CardView cardView = (CardView) view;
                applyBackgroundColorForCardView(cardView);
            } else {
                applyBackgroundColor(view);
            }
            L.i("applyAttr", "apply as color");
        } else if (isDrawableType()) {
            applyBackgroundDrawable(view);
            L.i("applyAttr", "apply as drawable");
        }
    }
}
