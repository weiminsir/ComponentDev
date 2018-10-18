package com.ulang.ulangapp.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VDHLayout extends LinearLayout {

    private ViewDragHelper mDragger;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();


    public VDHLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {

                Log.d("weiminsir", "method=" + getCurrentMethodName());
                //mEdgeTrackerView禁止直接移动
                return child == mDragView || child == mAutoBackView;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {

                Log.d("weiminsir", "method=" + getCurrentMethodName());
                if (releasedChild == mAutoBackView) {
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                }
                invalidate();
            }

            //在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                Log.d("weiminsir", "method=" + getCurrentMethodName());
                Log.d("weiminsir", "在边界拖动时回调=" + getCurrentMethodName());
                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }


            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {

                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                Log.d("weiminsir", "method=" + getCurrentMethodName());
                Log.d("weiminsir", "top=" + top);


                return top;
            }
        });

        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }


    public static String getCurrentMethodName() {
        int level = 1;
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String methodName = stacks[level].getMethodName();
        return methodName;
    }


    @Override
    public void computeScroll() {

        Log.d("weiminsir", "method=" + getCurrentMethodName());

        if (mDragger.continueSettling(true)) {
            Log.d("weiminsir", "computeScroll true");
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("weiminsir", "method=" + getCurrentMethodName());
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        Log.d("weiminsir", "method=" + getCurrentMethodName());
        super.onFinishInflate();

        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}
