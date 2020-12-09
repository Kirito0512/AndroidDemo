package com.example.androiddemo.viewhint.HintInfo;

import android.graphics.Path;
import android.graphics.RectF;

public class TopViewHintInfo extends ViewHintInfo {

    public TopViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle, boolean showHintCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
    }

    @Override
    public float getTextStartX(int singleLineWidth) {
        return getCenterX() - (singleLineWidth >> 1);
    }

    @Override
    public float getTextStartY(float totalLineHeight) {
        return getCenterY() - getRadius() - 100 - totalLineHeight;
    }

    @Override
    public Path getTrianglePath() {
        RectF rectF = getBackgroundRectF(getSingleLineWidth(), getTotalLineHeight());
        Path path = new Path();
        path.moveTo(getCenterX() - 40, rectF.bottom);
        path.lineTo(getCenterX(), rectF.bottom + 40);
        path.lineTo(getCenterX() + 40, rectF.bottom);
        path.close();
        return path;
    }
}
