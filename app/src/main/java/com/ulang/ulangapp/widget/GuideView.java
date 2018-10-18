package com.ulang.ulangapp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class GuideView extends View {
    private Point point;
    private int radius;
    private int drawRadius;
    private Paint paint;
    private ValueAnimator animator;

    public GuideView(Context context) {
        super(context, null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //http://stackoverflow.com/questions/11337679/porterduffxfermode-clear-a-section-of-a-bitmap
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
        Xfermode xFermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        paint.setXfermode(xFermode);

        animator = ValueAnimator.ofInt();
        animator.setDuration(300);
        animator.setInterpolator(new OvershootInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawRadius = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        animator.removeAllUpdateListeners();
        animator.cancel();
        super.onDetachedFromWindow();
    }

    public void showUnder(Point point, int radius) {
        this.point = point;
        this.radius = radius;
        animator.cancel();
        animator.setIntValues(0, radius);
        animator.start();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (point != null && radius != 0) {
            canvas.drawCircle(point.x, point.y, drawRadius, paint);
        }
    }
}
