package com.ulang.ulangapp.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by raise.yang on 2016/06/29.
 */
public class MyViewDragView extends LinearLayout {
    //1,定义GestureDetector类
    private final ViewDragHelper mDragHelper;
    private RelativeLayout mLayout;
    View mChild;
    int width;
    int height;

    public MyViewDragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置为可点击
//        setClickable(true);
        Log.d("weiminsir", "MyViewDragView");
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mDragHelper.cancel();
//            return false;
//        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLayout = (RelativeLayout) getChildAt(0);
        mChild = mLayout.getChildAt(0);

        width = mLayout.getMeasuredWidth();
        height = mLayout.getMeasuredHeight();
        Log.d("weiminsir", "height=" + height);
        Log.d("weiminsir", "width=" + width);
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            Log.d("weiminsir", "tryCaptureView ");

            return mLayout==child;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.d("weiminsir", "onViewPositionChanged top" + top);
            requestLayout();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {


        }


    }
}
