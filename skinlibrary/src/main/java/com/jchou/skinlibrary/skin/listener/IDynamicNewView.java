package com.jchou.skinlibrary.skin.listener;

import android.view.View;

import com.jchou.skinlibrary.skin.attrs.DynamicAttr;

import java.util.List;


public interface IDynamicNewView {
    void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
