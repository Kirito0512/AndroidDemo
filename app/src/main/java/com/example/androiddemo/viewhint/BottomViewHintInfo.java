package com.example.androiddemo.viewhint;

import android.graphics.Path;
import android.graphics.RectF;

public class BottomViewHintInfo extends ViewHintInfo {

    public BottomViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
    }

    public BottomViewHintInfo(int viewId, String hintText, int textGravity) {
        super(viewId, hintText, textGravity);
    }

    @Override
    public float getTextStartX(int singleLineWidth) {
        return getCenterX() - (singleLineWidth >> 1);
    }

    @Override
    public float getTextStartY(float totalLineHeight) {
        return getCenterY() + getRadius() + 100;
    }

    @Override
    public Path getTrianglePath() {
        RectF rectF = getBackgroundRectF(getSingleLineWidth(), getTotalLineHeight());
        Path path = new Path();
        path.moveTo(getCenterX() - 40, rectF.top);
        path.lineTo(getCenterX(), rectF.top - 40);
        path.lineTo(getCenterX() + 40, rectF.top);
        path.close();
        return path;
    }
}
