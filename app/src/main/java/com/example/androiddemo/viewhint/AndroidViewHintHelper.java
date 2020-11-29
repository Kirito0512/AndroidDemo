package com.example.androiddemo.viewhint;

import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class AndroidViewHintHelper {
    private final KtvHintView hintView;
    private SparseArray<ViewHintInfo> sparseArray = new SparseArray<>();
    private FragmentActivity activity;
    private View externalView;
    public AndroidViewHintHelper(Fragment fragment) {
        this(fragment.getActivity());
    }

    public AndroidViewHintHelper(FragmentActivity activity) {
        this.activity = activity;
        this.hintView = new KtvHintView(activity);
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText) {
        ViewHintInfo hintInfo = new ViewHintInfo(viewId, hintText);
        View view = activity.findViewById(viewId);
        hintInfo.setHintView(view);
        sparseArray.put(view.hashCode(), hintInfo);
        return this;
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        ViewHintInfo hintInfo = new ViewHintInfo(viewId, hintText, hintMargin, clickEnable, showLightCircle);
        View view = activity.findViewById(viewId);
        sparseArray.put(view.hashCode(), hintInfo);
        return this;
    }

    public AndroidViewHintHelper addExternalCenterView(View externalView) {
        this.externalView = externalView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        this.externalView.setLayoutParams(layoutParams);
        return this;
    }

    public void show() {
        if (activity == null)
            return;
        FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
        hintView.setHintInfo(sparseArray);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hintView.setLayoutParams(layoutParams);
        decorView.addView(hintView);
        if (externalView != null) {
            decorView.addView(externalView);
        }
    }

    public void dismiss() {
        if (activity == null)
            return;
        FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
        decorView.removeView(hintView);
        decorView.removeView(externalView);
    }
}
