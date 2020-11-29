package com.example.androiddemo.viewhint;

public class BottomViewHintInfo extends ViewHintInfo {

    public BottomViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
    }

    public BottomViewHintInfo(int viewId, String hintText, int textGravity) {
        super(viewId, hintText, textGravity);
    }

    @Override
    public float getTextStartX() {
        return super.getTextStartX();
    }

    @Override
    public float getTextStartY() {
        return super.getTextStartY();
    }
}
