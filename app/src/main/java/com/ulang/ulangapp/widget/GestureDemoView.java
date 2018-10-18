package com.ulang.ulangapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by raise.yang on 2016/06/29.
 */
public class GestureDemoView extends LinearLayout {
    //1,定义GestureDetector类
    private GestureDetector m_gestureDetector;
    private RelativeLayout mFather;
    View mChild;
    int width;
    int height;
    int fatherLeft=0, fatherRight=0, fatherTop=0, fatherDown;
    int childLeft, childRight, childTop, childDown;

    public GestureDemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置为可点击
        setClickable(true);
        //2,初始化手势类，同时设置手势监听
        m_gestureDetector = new GestureDetector(context, onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //3,将touch事件交给gesture处理
        m_gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        Log.d("GestureDemoView", "onMeasure() ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mFather.layout(fatherLeft, fatherTop, fatherRight, fatherDown);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mFather = (RelativeLayout) getChildAt(0);
        mChild = mFather.getChildAt(0);
        fatherRight = mFather.getRight();
        fatherDown = mFather.getTop()+mFather.getHeight();
    }

    //初始化手势监听对象，使用GestureDetector.OnGestureListener的实现抽象类，因为实际开发中好多方法用不上
    private final GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("GestureDemoView", "onSingleTapUp() ");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("GestureDemoView", "onLongPress() ");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("GestureDemoView", "onScroll()");
            float y1 = e1.getRawY();
            float y2 = e2.getRawY();
            int yDis = (int) (y2 - y1);
            if (yDis <= mFather.getHeight()) {
                fatherTop = mFather.getTop() - yDis;
                fatherDown = fatherTop + mFather.getHeight();
            }
            Log.d("GestureDemoView", "onScroll() fatherTop = " + fatherTop);
            Log.d("GestureDemoView", "onScroll() fatherDown = " + fatherDown);
            requestLayout();
            return super.onScroll(e1, e2, distanceX, distanceY);

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("GestureDemoView", "onFling() velocityX = " + velocityX);


            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("GestureDemoView", "onShowPress() ");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("GestureDemoView", "onDown() ");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("GestureDemoView", "onDoubleTap() ");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d("GestureDemoView", "onDoubleTapEvent() ");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GestureDemoView", "onSingleTapConfirmed() ");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.d("GestureDemoView", "onContextClick() ");
            return super.onContextClick(e);
        }
    };

}
