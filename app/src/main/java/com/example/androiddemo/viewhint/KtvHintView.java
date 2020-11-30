package com.example.androiddemo.viewhint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androiddemo.R;

public class KtvHintView extends View {
    private SparseArray<ViewHintInfo> sparseArray;
    private Paint mPaint;
    private static final String TAG = "KtvHintView_test";
    private static final int DRAW_POINT_COUNT = 60;
    private static final int TEXT_MAX_WIDTH = 500;
    public static final int TEXT_BACKGROUND_PADDING = 30;
    private Xfermode xfermode;
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
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
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
        mPaint.setXfermode(xfermode);
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
            if (hintInfo.getHintText() != null && hintInfo.getHintText().length() > 0) {
                drawHintText(hintInfo, canvas);
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
        hintInfo.setCenterX(centerCircleX);
        hintInfo.setCenterY(centerCircleY);
        hintInfo.setRadius(radius);
        canvas.drawCircle(centerCircleX, centerCircleY, radius, mPaint);
    }

    // 绘制点状的圆环
    private void drawPointCircle(ViewHintInfo hintInfo, Canvas canvas) {
        // 绘制镂空区域
        float radius = hintInfo.getRadius();
        float centerCircleX = hintInfo.getCenterX();
        float centerCircleY = hintInfo.getCenterY();

        int perDegree = 360 / DRAW_POINT_COUNT;
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        for (int i = 0; i < DRAW_POINT_COUNT; i++) {
            canvas.drawPoint(centerCircleX, centerCircleY - radius, mPaint);
            canvas.rotate(perDegree, centerCircleX, centerCircleY);
        }
    }

    private void drawHintText(ViewHintInfo hintInfo, Canvas canvas) {

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(5);
        textPaint.setTextSize(48.0F);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setAntiAlias(true);
        StaticLayout layout = new StaticLayout(hintInfo.getHintText(), textPaint, TEXT_MAX_WIDTH, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        // 这里的参数300，表示字符串的长度，当满300时，就会换行，也可以使用“\r\n”来实现换行
        canvas.save();

        int layoutCount = layout.getLineCount();
        float singleLineHeight = getFontHeight(textPaint);
        float totalLineHeight = singleLineHeight * layoutCount;

        Rect bounds = new Rect();
        textPaint.getTextBounds(hintInfo.getHintText(), 0, hintInfo.getHintText().length(), bounds);
        int singleLineWidth = Math.min(bounds.width(), TEXT_MAX_WIDTH);
        hintInfo.setSingleLineWidth(singleLineWidth);
        hintInfo.setTotalLineHeight(totalLineHeight);
        // 绘制圆角背景颜色
        drawBackGround(hintInfo, canvas, singleLineWidth, totalLineHeight);
        // 绘制文字
        canvas.translate(hintInfo.getTextStartX(singleLineWidth), hintInfo.getTextStartY(totalLineHeight));
        layout.draw(canvas);
        canvas.restore();//别忘了restore
    }

    private void drawBackGround(ViewHintInfo hintInfo, Canvas canvas, int singleLineWidth, float totalLineHeight) {
        mPaint.setColor(Color.parseColor("#FFFF3348"));
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = hintInfo.getBackgroundRectF(singleLineWidth, totalLineHeight);
        canvas.drawRoundRect(rectF, 30, 30, mPaint);
        // 绘制三角形
        canvas.drawPath(hintInfo.getTrianglePath(), mPaint);
    }

    /**
     *得到文字的高度
     */
    private float getFontHeight(TextPaint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();// 得到系统默认字体属性
        return fm.bottom - fm.top;
    }
}
