package com.example.androiddemo.viewhint;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.androiddemo.R;

public class AndroidViewHintHelper {
    private KtvHintView hintView;
    private SparseArray<ViewHintInfo> sparseArray = new SparseArray<>();
    private FragmentActivity activity;
    private View externalView;
    private TextView skipTv;

    public AndroidViewHintHelper(Fragment fragment) {
        this(fragment.getActivity());
    }

    public AndroidViewHintHelper(FragmentActivity activity) {
        this.activity = activity;
        this.hintView = new KtvHintView(activity);
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText, @ViewHintInfo.HintTextGravity int textGravity) {
        return addHintView(viewId, hintText, textGravity, 0, true, false);
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText, @ViewHintInfo.HintTextGravity int textGravity, int margin) {
        return addHintView(viewId, hintText, textGravity, margin, true, false);
    }

    public AndroidViewHintHelper addHintView(ViewHintInfo viewHintInfo) {
        if (viewHintInfo == null || viewHintInfo.getHintView() == null) {
            return this;
        }
        sparseArray.put(viewHintInfo.getHintView().hashCode(), viewHintInfo);
        return this;
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText, @ViewHintInfo.HintTextGravity int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        ViewHintInfo hintInfo = ViewHintInfoFactory.create(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
        View view = activity.findViewById(viewId);
        hintInfo.setHintView(view);
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

    @SuppressLint("UseCompatLoadingForDrawables")
    public AndroidViewHintHelper addSkipView() {
        skipTv = new TextView(activity);
        skipTv.setText("跳过");
        skipTv.setTextColor(Color.parseColor("#FF121212"));
        skipTv.setTextSize(12);
        skipTv.setGravity(Gravity.CENTER);
        skipTv.setBackground(skipTv.getResources().getDrawable(R.drawable.skip_textview_background, null));
        skipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintView != null && hintView.getParent() != null) {
                    FrameLayout frameLayout = (FrameLayout) hintView.getParent();
                    frameLayout.removeView(hintView);
                    frameLayout.removeView(skipTv);
                }
            }
        });
        return this;
    }

    public void show() {
        if (activity == null)
            return;
        FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
        View topView = decorView.getChildAt(decorView.getChildCount() - 1);
        if (topView.getTag() != null && topView.getTag().equals("HintView")) {
            hintView = null;
            skipTv = null;
            return;
        }
        hintView.setHintInfo(sparseArray);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hintView.setLayoutParams(layoutParams);
        hintView.setTag("HintView");
        decorView.addView(hintView);
        if (externalView != null) {
            decorView.addView(externalView);
        }
        if (skipTv != null) {
            skipTv.setTag("HintView");
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(144, 72);
            lp.gravity = Gravity.BOTTOM | Gravity.START;
            lp.leftMargin = 45;
            lp.bottomMargin = 45;
            decorView.addView(skipTv, lp);
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
