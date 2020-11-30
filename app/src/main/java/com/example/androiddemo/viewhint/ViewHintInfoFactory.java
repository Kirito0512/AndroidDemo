package com.example.androiddemo.viewhint;

public class ViewHintInfoFactory {
    public static ViewHintInfo create(@ViewHintInfo.HintTextGravity int textGravity, int viewId, String hintText) {
        if (hintText == null || hintText.length() == 0) {
            return new ViewHintInfo(viewId);
        }
        switch (textGravity) {
            case ViewHintInfo.TOP:
                return new TopViewHintInfo(viewId, hintText, textGravity);
            case ViewHintInfo.LOWER_LEFT:
                return new LowerLeftViewHintInfo(viewId, hintText, textGravity);
            case ViewHintInfo.LOWER_RIGHT:
                return new LowerRightViewHintInfo(viewId, hintText, textGravity);
            case ViewHintInfo.UPPER_LEFT:
                return new UpperLeftViewHintInfo(viewId, hintText, textGravity);
            case ViewHintInfo.UPPER_RIGHT:
                return new UpperRightViewHintInfo(viewId, hintText, textGravity);
            case ViewHintInfo.BOTTOM:
            default:
                return new BottomViewHintInfo(viewId, hintText, textGravity);
        }
    }

    public static ViewHintInfo create(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        if (hintText == null || hintText.length() == 0) {
            return new ViewHintInfo(viewId);
        }
        switch (textGravity) {
            case ViewHintInfo.TOP:
                return new TopViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
            case ViewHintInfo.LOWER_LEFT:
                return new LowerLeftViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
            case ViewHintInfo.LOWER_RIGHT:
                return new LowerRightViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
            case ViewHintInfo.UPPER_LEFT:
                return new UpperLeftViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
            case ViewHintInfo.BOTTOM:
            default:
                return new BottomViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
        }
    }
}
