package com.jchou.skinlibrary.skin.entity;

import android.view.View;


import com.jchou.skinlibrary.skin.attrs.SkinAttr;
import com.jchou.skinlibrary.skin.util.ListUtils;

import java.util.ArrayList;
import java.util.List;


public class SkinItem {

    private View view;

    private List<SkinAttr> skinAttrs;

    public SkinItem() {
        skinAttrs = new ArrayList<SkinAttr>();
    }

    public void apply() {
        if (ListUtils.isEmpty(skinAttrs)) {
            return;
        }
        for (SkinAttr attr : skinAttrs) {
            attr.apply(view);
        }
    }

    public void clean() {
        if (ListUtils.isEmpty(skinAttrs)) {
            return;
        }
        for (SkinAttr attr : skinAttrs) {
            attr = null;
        }
    }


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getSkinAttrs() {
        return skinAttrs;
    }

    public void setSkinAttrs(List<SkinAttr> skinAttrs) {
        this.skinAttrs = skinAttrs;
    }


//    public void apply() {
//        if (view != null) {
//            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(ThemeUtil.NAME, Context.MODE_PRIVATE);
//            boolean isTheme = sharedPreferences.getBoolean(ThemeUtil.IS_THEME, true);
//            int value = sharedPreferences.getInt(ThemeUtil.THEME_COLOR, Color.parseColor("#FF2F3A4C"));
//            for (SkinAttr skinAttr :
//                    skinAttrs) {
//                if (isTheme) {
//                    if (skinAttr.getAttrName().equals("textColor")) {
//                        ((TextView) view).setTextColor(value);
//                    } else if (skinAttr.getAttrName().equals("background")) {
//                        if (view instanceof SeekBar) {
////                            不设置seekbar的背景
//                            continue;
//                        }
//                        view.setBackgroundColor(value);
//                    } else if (skinAttr.getAttrName().equals("thumb") && view instanceof SeekBar) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            CommonLogger.e("这里设置主题");
//                            ((SeekBar) view).setThumb(view.getContext().getResources().getDrawable(R.drawable.thumb_normal, view.getContext().getTheme()));
//                        } else {
//                            CommonLogger.e("这里设置主题1");
//                            ((SeekBar) view).setThumb(view.getContext().getResources().getDrawable(R.drawable.thumb_normal));
//                        }
//                    }
//                } else {
//                    skinAttr.apply(view);
//                }
//            }
//        }
//    }


    @Override
    public String toString() {
        return "SkinItem [view=" + view.getClass().getSimpleName() + ", skinAttrs=" + skinAttrs + "]";
    }


    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof SkinItem && ((SkinItem) obj).getView().equals(getView()));
    }
}