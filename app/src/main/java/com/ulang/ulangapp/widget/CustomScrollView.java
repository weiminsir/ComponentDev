package com.ulang.ulangapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
	private View mRootView;
	private int mpreY = 0;
	private Rect mNormalRect;

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		mRootView = getChildAt(0);
		super.onFinishInflate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float curY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			if (mRootView != null) {
				mNormalRect = new Rect(mRootView.getLeft(), mRootView.getTop(),
						mRootView.getRight(), mRootView.getBottom());
			}
		}
			break;
		case MotionEvent.ACTION_MOVE: {
			int delta = (int) ((curY - mpreY) * 0.25);
			if (delta > 0) {
				mRootView.layout(mRootView.getLeft(), mRootView.getTop()
						+ delta, mRootView.getRight(), mRootView.getBottom()
						+ delta);
			}
		}
			break;
		case MotionEvent.ACTION_UP: {
			int curTop = mRootView.getTop();
			mRootView.layout(mNormalRect.left, mNormalRect.top,
					mNormalRect.right, mNormalRect.bottom);
			TranslateAnimation animation = new TranslateAnimation(0, 0, curTop
					- mNormalRect.top, 0);
			animation.setDuration(200);
			mRootView.startAnimation(animation);
		}
			break;
		}
		mpreY = (int) curY;
		return super.onTouchEvent(event);
	}

}
