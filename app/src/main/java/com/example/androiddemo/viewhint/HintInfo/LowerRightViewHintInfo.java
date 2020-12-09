package com.example.androiddemo.viewhint.HintInfo;

import android.graphics.Path;
import android.graphics.RectF;

public class LowerRightViewHintInfo extends ViewHintInfo {

    public LowerRightViewHintInfo(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle, boolean showHintCircle) {
        super(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
    }

    @Override
    public float getTextStartX(int singleLineWidth) {
        return getCenterX() - 50;
    }

    @Override
    public float getTextStartY(float totalLineHeight) {
        return getCenterY() + getRadius() + 100;
    }

    @Override
    public Path getTrianglePath() {
        RectF rectF = getBackgroundRectF(getSingleLineWidth(), getTotalLineHeight());
        Path path = new Path();
        // 防止出现圆角矩形和三角形之间出现不连接的部分
        float top = (float) Math.ceil(rectF.top);
        path.moveTo(getCenterX() - 40, top);
        path.lineTo(getCenterX(), top - 40);
        path.lineTo(getCenterX() + 40, top);
        path.close();
        return path;
    }
}
