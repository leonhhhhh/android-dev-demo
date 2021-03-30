package com.leon.dev_demo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.fonts.Font;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class TTextView extends View {

    public TTextView(Context context) {
        super(context);
    }

    public TTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("宽高:","宽度:"+w+"高度:"+h);
        width = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    Paint paint;
    Paint textPaint;
    Rect textRect;
    Paint.FontMetrics fontMetricsBig;
    Paint.FontMetrics fontMetricsSmall;
    String text = "MjMgMgMgMgMgMgMgMgMgMgMg";
    private static final String TAG = "自定义 文字绘制";

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                50, getResources().getDisplayMetrics());
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textRect = new Rect();
        textPaint.getTextBounds(text,0,text.length(),textRect);
        fontMetricsSmall = new Paint.FontMetrics();
        fontMetricsBig = new Paint.FontMetrics();
        textPaint.getFontMetrics(fontMetricsBig);
        Log.e(TAG,"文字量度:Top:"+fontMetricsBig.top);
        Log.e(TAG,"文字量度:Ascent:"+fontMetricsBig.ascent);
        Log.e(TAG,"文字量度:Descent:"+fontMetricsBig.descent);
        Log.e(TAG,"文字量度:Bottom:"+fontMetricsBig.bottom);
        Log.e(TAG,"文字量度:Leading:"+fontMetricsBig.leading);
        Log.e(TAG,"文字矩阵:"+textRect.toString());
    }

    int width = 0;
    int height = 0;
    int circleX = 0;
    int circleY = 200;
    int radius = 49;
    int textLeft = 50;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawRect(1,1, radius,height,paint);

        textPaint.setTextSize(150);
        canvas.drawText(text,textLeft,circleY,textPaint);
        canvas.drawLine(textLeft,circleY+fontMetricsBig.ascent, width,circleY+fontMetricsBig.ascent,paint);
        canvas.drawLine(textLeft,circleY-107, width,circleY-107,paint);
        canvas.drawLine(textLeft,circleY, width,circleY,paint);
        canvas.drawLine(textLeft,circleY+fontMetricsBig.descent, width,circleY+fontMetricsBig.descent,paint);
        textPaint.setTextSize(20);
        textPaint.getFontMetrics(fontMetricsSmall);
        canvas.drawText(text,textLeft,circleY+(fontMetricsBig.descent-fontMetricsSmall.ascent),textPaint);
        canvas.drawLine(textLeft,circleY+(fontMetricsBig.descent-fontMetricsSmall.ascent), width,circleY+(fontMetricsBig.descent-fontMetricsSmall.ascent)
                ,paint);
        canvas.drawLine(textLeft,circleY+fontMetricsBig.descent, width,circleY+fontMetricsBig.descent
                ,paint);

    }
}
