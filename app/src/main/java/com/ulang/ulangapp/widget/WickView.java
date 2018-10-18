package com.ulang.ulangapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WickView extends View {
    Paint paint;

    private int mWidth, mHeight;

    public WickView(Context context) {
        super(context);
       initPaint();
    }

    public WickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    // 1.创建Picture



    // 2.录制内容方法
    private void recording() {
             Picture mPicture = new Picture();
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
//
//        Path path = new Path();                     // 创建Path
//
//        path.lineTo(200, 200);                      // lineTo
//        path.lineTo(200,0);
//
//        canvas.drawPath(path, paint);
//
//
//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path2 = new Path();                     // path中添加一个圆形(圆心在坐标原点)
        path2.addCircle(0,0,100, Path.Direction.CW);

        Path dst = new Path();                      // dst中添加一个矩形
        dst.addRect(-200,400,200,200, Path.Direction.CW);

        path2.offset(300,100,dst);                     // 平移

        canvas.drawPath(path2,paint);               // 绘制path

        paint.setColor(Color.BLUE);                // 更改画笔颜色

        canvas.drawPath(dst,paint);                // 绘制dst
    }


    public WickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int w_size = MeasureSpec.getSize(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int h_size = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        mWidth = w;
        mHeight = h;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
