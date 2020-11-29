package com.example.androiddemo.viewhint;

import android.graphics.Path;

public class LowerLeftViewHintInfo extends ViewHintInfo {

    public LowerLeftViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
    }

    public LowerLeftViewHintInfo(int viewId, String hintText, int textGravity) {
        super(viewId, hintText, textGravity);
    }

    @Override
    public float getTextStartX(int singleLineWidth) {
        return 0;
    }

    @Override
    public float getTextStartY(float totalLineHeight) {
        return 0;
    }

    @Override
    public Path getTrianglePath() {
        return super.getTrianglePath();
    }
}
