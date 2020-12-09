package com.example.androiddemo.viewhint;

import com.example.androiddemo.viewhint.HintInfo.BottomViewHintInfo;
import com.example.androiddemo.viewhint.HintInfo.LowerLeftViewHintInfo;
import com.example.androiddemo.viewhint.HintInfo.LowerRightViewHintInfo;
import com.example.androiddemo.viewhint.HintInfo.TopViewHintInfo;
import com.example.androiddemo.viewhint.HintInfo.UpperLeftViewHintInfo;
import com.example.androiddemo.viewhint.HintInfo.ViewHintInfo;

public class ViewHintInfoFactory {
    public static ViewHintInfo create(int viewId, String hintText, int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle, boolean showHintCircle) {
        if (hintText == null || hintText.length() == 0) {
            return new ViewHintInfo(viewId);
        }
        switch (textGravity) {
            case ViewHintInfo.TOP:
                return new TopViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
            case ViewHintInfo.LOWER_LEFT:
                return new LowerLeftViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
            case ViewHintInfo.LOWER_RIGHT:
                return new LowerRightViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
            case ViewHintInfo.UPPER_LEFT:
                return new UpperLeftViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
            case ViewHintInfo.BOTTOM:
            default:
                return new BottomViewHintInfo(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle, showHintCircle);
        }
    }
}
