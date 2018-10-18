package com.ulang.ulangapp.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MyDragLinerlayout extends LinearLayout {
    private GestureDetector mGestureDetector;
    private int width;
    private int height;
    private RelativeLayout layout;
    private View mChild;
    String TAG = "weiminsir";

    public MyDragLinerlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置为可点击
        setClickable(true);
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
        //mGestureDetector.setIsLongpressEnabled(false);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("weiminsir", "onTouchEvent 传递");
//
//        return mGestureDetector.onTouchEvent(event);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layout = (RelativeLayout) getChildAt(0);
        mChild = layout.getChildAt(0);

        width = layout.getMeasuredWidth();
        height = layout.getMeasuredHeight();
        Log.d("weiminsir", "height=" + height);
        Log.d("weiminsir", "width=" + width);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /*
     *
     * 手势监听类
     * */
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public MyGestureListener() {
            super();
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("weiminsir", "onScroll=" );
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("weiminsir", "onFling=" );
            return true;
        }
    }

}
