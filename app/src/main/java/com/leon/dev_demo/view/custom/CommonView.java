package com.leon.dev_demo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定义View第一课
 *
 * - 坐标系的定义
 * - canvas的使用
 * - 常见几何图形的使用
 * - path的使用
 */
public class CommonView extends View {
    private Paint paint;
    public CommonView(Context context) {
        super(context);
    }

    public CommonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    {
        init();
    }
    int widthArc = 0;
    int heightArc = 0;
    Path dash = new Path();
    PathDashPathEffect effect;
    RectF rectf;
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        widthArc = 400;
        heightArc = 400;
        degrees = 240 / 20;
    }
    int degrees = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        canvas.drawArc(rectf,150,240,false,paint);
        paint.setPathEffect(effect);
        paint.setColor(Color.RED);
        canvas.drawArc(rectf,150,240,false,paint);
        paint.setPathEffect(null);
        paint.setColor(Color.BLUE);

        canvas.save();
        canvas.rotate(12*10,width >> 1,400);
        canvas.drawLine(width >> 1,400,(width >> 1),400-180,paint);
        canvas.restore();
    }

    private int width = 0;
    private int height = 0;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        widthArc = width-100;
        rectf = new RectF((width >> 1) -200,200,(width >> 1) +200,600);
        Path path = new Path();
        path.addArc(rectf,150,240);
        PathMeasure pathMeasure = new PathMeasure(path,false);
        dash = new Path();
//        dash.addRect(0,0,10,20, Path.Direction.CW);
        dash.addCircle(10,10,5, Path.Direction.CW);
        effect = new PathDashPathEffect(dash,(pathMeasure.getLength()-10)/20,0, PathDashPathEffect.Style.ROTATE);

    }
}
