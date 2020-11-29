package com.example.androiddemo.viewhint;

public class LowerRightViewHintInfo extends ViewHintInfo {

    public LowerRightViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
    }

    public LowerRightViewHintInfo(int viewId, String hintText, int textGravity) {
        super(viewId, hintText, textGravity);
    }

    @Override
    public float getTextStartX(int singleLineWidth) {
        return super.getTextStartX(singleLineWidth);
    }

    @Override
    public float getTextStartY(float totalLineHeight) {
        return super.getTextStartY(totalLineHeight);
    }
}
