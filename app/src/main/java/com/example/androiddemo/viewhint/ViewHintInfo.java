package com.example.androiddemo.viewhint;

import android.view.View;

import androidx.annotation.IdRes;

public class ViewHintInfo {
    private @IdRes int viewId;
    private View hintView;
    private String hintText;
    private int hintMargin;
    private boolean showLightCircle;
    private boolean clickEnable;

    private float centerX, centerY, radius;

    public ViewHintInfo(int viewId, String hintText, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        this.viewId = viewId;
        this.hintText = hintText;
        this.hintMargin = hintMargin;
        this.clickEnable = clickEnable;
        this.showLightCircle = showLightCircle;
    }

    public ViewHintInfo(int viewId, String hintText) {
        this(viewId, hintText, 15, true, true);
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public View getHintView() {
        return hintView;
    }

    public void setHintView(View hintView) {
        this.hintView = hintView;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public int getHintMargin() {
        return hintMargin;
    }

    public void setHintMargin(int hintMargin) {
        this.hintMargin = hintMargin;
    }

    public boolean isShowLightCircle() {
        return showLightCircle;
    }

    public void setShowLightCircle(boolean showLightCircle) {
        this.showLightCircle = showLightCircle;
    }

    public boolean isClickEnable() {
        return clickEnable;
    }

    public void setClickEnable(boolean clickEnable) {
        this.clickEnable = clickEnable;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }
}
