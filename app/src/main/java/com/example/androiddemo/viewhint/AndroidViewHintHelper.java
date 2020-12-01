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
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.androiddemo.R;

import org.w3c.dom.Text;

public class AndroidViewHintHelper {
    private final KtvHintView hintView;
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
        ViewHintInfo hintInfo = ViewHintInfoFactory.create(textGravity, viewId, hintText);
        View view = activity.findViewById(viewId);
        hintInfo.setHintView(view);
        sparseArray.put(view.hashCode(), hintInfo);
        return this;
    }

    public AndroidViewHintHelper addHintView(@IdRes int viewId, String hintText, @ViewHintInfo.HintTextGravity int textGravity, int hintMargin, boolean clickEnable, boolean showLightCircle) {
        ViewHintInfo hintInfo = ViewHintInfoFactory.create(viewId, hintText, textGravity, hintMargin, clickEnable, showLightCircle);
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
        hintView.setHintInfo(sparseArray);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hintView.setLayoutParams(layoutParams);
        decorView.addView(hintView);
        if (externalView != null) {
            decorView.addView(externalView);
        }
        if (skipTv != null) {
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
