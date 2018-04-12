package com.jchou.skinlibrary.skin.attrs;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jchou.skinlibrary.skin.SkinManager;


public abstract class SkinAttr {
    protected static final String RES_TYPE_NAME_COLOR = "color";
    protected static final String RES_TYPE_NAME_DRAWABLE = "drawable";
    public static final String RESOURCE_TYPE_NAME_MIPMAP = "mipmap";

    /**
     * 属性名, 例如: background、textSize、textColor
     */
    private String attrName;

    /**
     * 属性值的引用id
     */
    private int attrValueRefId;

    /**
     * 资源的名字, 例如 [app_exit_btn_background]
     */
    private String attrValueRefName;

    /**
     * type of the value , such as color or drawable
     */
    private String attrValueTypeName;

    /**
     * Use to apply view with new TypedValue
     *
     * @param view
     */
    public abstract void apply(View view);



    protected boolean isColorType() {
        return getAttrValueTypeName().equals(RES_TYPE_NAME_COLOR);
    }

    protected boolean isDrawableType() {
        return getAttrValueTypeName().equals(RES_TYPE_NAME_DRAWABLE);
    }

    public SkinAttr applyBackgroundColor(View view) {
        view.setBackgroundColor(SkinManager.getInstance().getColor(getAttrValueRefId()));
        return this;
    }

    //给CardView设置背景色应该使用cardBackgroundColor，直接使用background就会没有圆角效果
    public SkinAttr applyBackgroundColorForCardView(CardView view) {
        view.setCardBackgroundColor(SkinManager.getInstance().getColor(getAttrValueRefId()));
        return this;
    }


    public SkinAttr applyBackgroundDrawable(View view) {
        view.setBackground(SkinManager.getInstance().getDrawable(getAttrValueRefId()));
        return this;
    }


    public SkinAttr applyImageDrawable(View view) {
        ((ImageView) view).setImageDrawable(SkinManager.getInstance().getDrawable(getAttrValueRefId()));
        return this;
    }


    public SkinAttr applyTextColor(View view) {
        ((TextView) view).setTextColor(SkinManager.getInstance().getColor(getAttrValueRefId()));
        return this;
    }

    @Override
    public String toString() {
        return "SkinAttr \n[\nattrName=" + attrName + ", \n"
                + "attrValueRefId=" + attrValueRefId + ", \n"
                + "attrValueRefName=" + attrValueRefName + ", \n"
                + "attrValueTypeName=" + attrValueTypeName
                + "\n]";
    }




    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getAttrValueRefId() {
        return attrValueRefId;
    }

    public void setAttrValueRefId(int attrValueRefId) {
        this.attrValueRefId = attrValueRefId;
    }

    public String getAttrValueRefName() {
        return attrValueRefName;
    }

    public void setAttrValueRefName(String attrValueRefName) {
        this.attrValueRefName = attrValueRefName;
    }

    public String getAttrValueTypeName() {
        return attrValueTypeName;
    }

    public void setAttrValueTypeName(String attrValueTypeName) {
        this.attrValueTypeName = attrValueTypeName;
    }
}
