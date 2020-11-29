package com.example.androiddemo.viewhint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androiddemo.R;

public class KtvHintView extends View {
    private SparseArray<ViewHintInfo> sparseArray;
    private Paint mPaint;
    private static final String TAG = "KtvHintView_test";
    private final int DRAW_POINT_COUNT = 40;
    public KtvHintView(@NonNull Context context) {
        this(context, null);
    }

    public KtvHintView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KtvHintView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public void setHintInfo(SparseArray<ViewHintInfo> sparseArray) {
        this.sparseArray = sparseArray;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: sparseArray size = " + sparseArray.size());
        if (sparseArray == null || sparseArray.size() == 0)
            return;
        int count = canvas.saveLayer(0, 0, getWidth(), getHeight(), null);
        canvas.drawColor(getResources().getColor(R.color.transparent_50));
        for (int i = 0; i < sparseArray.size(); i++) {
            ViewHintInfo hintInfo = sparseArray.valueAt(i);
            drawHitView(hintInfo, canvas);
        }
        canvas.restoreToCount(count);
        mPaint.setXfermode(null);
        for (int i = 0; i < sparseArray.size(); i++) {
            ViewHintInfo hintInfo = sparseArray.valueAt(i);
            if (hintInfo.isShowLightCircle()) {
                drawPointCircle(hintInfo, canvas);
            }
        }
    }

    private void drawHitView(ViewHintInfo hintInfo, Canvas canvas) {
        // 绘制镂空区域
        View view = hintInfo.getHintView();
        int[] locationArray = new int[2];
        view.getLocationInWindow(locationArray);
        RectF rectF = new RectF();
        rectF.left = locationArray[0];
        rectF.top = locationArray[1];
        rectF.right = locationArray[0] + view.getWidth();
        rectF.bottom = locationArray[1] + view.getHeight();
        float radius = Math.max(rectF.right - rectF.left, rectF.bottom - rectF.top) / 2 + hintInfo.getHintMargin();
        float centerCircleX = (rectF.left + rectF.right) / 2;
        float centerCircleY = (rectF.top + rectF.bottom) / 2;
        canvas.drawCircle(centerCircleX, centerCircleY, radius, mPaint);
//        if (hintInfo.isShowLightCircle()) {
//            drawPointCircle(hintInfo, canvas);
//        }
    }

    private void drawPointCircle(ViewHintInfo hintInfo, Canvas canvas) {
        // 绘制镂空区域
        View view = hintInfo.getHintView();
        int[] locationArray = new int[2];
        view.getLocationInWindow(locationArray);
        RectF rectF = new RectF();
        rectF.left = locationArray[0];
        rectF.top = locationArray[1];
        rectF.right = locationArray[0] + view.getWidth();
        rectF.bottom = locationArray[1] + view.getHeight();
        float radius = Math.max(rectF.right - rectF.left, rectF.bottom - rectF.top) / 2 + hintInfo.getHintMargin();
        float centerCircleX = (rectF.left + rectF.right) / 2;
        float centerCircleY = (rectF.top + rectF.bottom) / 2;

        int perDegree = 360 / DRAW_POINT_COUNT;
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        for (int i = 0; i < DRAW_POINT_COUNT; i++) {
            canvas.drawPoint(centerCircleX, centerCircleY - radius - 2, mPaint);
            canvas.rotate(perDegree, centerCircleX, centerCircleY);
        }
    }
}
