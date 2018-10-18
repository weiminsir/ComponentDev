package com.ulang.ulangapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ulang.ulangapp.R;

public class MoreTextView extends View {
    private String mText;
    private int mMaxLine;
    private String mMoreText;
    private TextPaint mPaint;
    private Paint.FontMetrics mFontMetrics;
    private String mDrawText;
    private StaticLayout mStaticLayout;
    private boolean mClickable;

    public MoreTextView(Context context) {
        super(context);
        init(context);
    }

    public MoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.moretextview));
        mPaint.setColor(Color.WHITE);
        mFontMetrics = mPaint.getFontMetrics();
    }

    public void setText(String text, int maxLine, String moreText) {
        this.mText = text;
        this.mMaxLine = maxLine;
        this.mMoreText = moreText;
        post(new Runnable() {
            @Override
            public void run() {
                String replaced = mText.replaceAll("\\n", " ");

                char[] chars = replaced.toCharArray();
                int line1Size = mPaint.breakText(chars, 0, chars.length, getWidth(), null);
                String s1 = String.copyValueOf(chars, 0, line1Size);

                int line2Size = mPaint.breakText(chars, line1Size, chars.length - line1Size, getWidth(), null);
                String s2 = String.copyValueOf(chars, line1Size, line2Size);

                int line3Size = mPaint.breakText(chars, line1Size + line2Size, chars.length - line1Size - line2Size, getWidth(), null);
                String s3 = String.copyValueOf(chars, line1Size + line2Size, line3Size);

                mClickable = chars.length > line1Size + line2Size + line3Size;

                char[] chars1 = s3.toCharArray();
                int len = chars1.length;
                if (!TextUtils.isEmpty(s3) && mClickable) {
                    while (true) {
                        if (mPaint.measureText(chars1, 0, len) + mPaint.measureText(mMoreText) < getWidth()) {
                            break;
                        }
                        len--;
                    }
                }

                if (TextUtils.isEmpty(s2)) {
                    mDrawText = s1;
                } else if (TextUtils.isEmpty(s3)) {
                    mDrawText = s1 + "\n" + s2;
                } else {
                    if (mClickable) {
                        mDrawText = s1 + "\n" + s2 + "\n" + String.copyValueOf(chars1, 0, len) + mMoreText;
                    } else {
                        mDrawText = s1 + "\n" + s2 + "\n" + s3;
                    }
                }

                SpannableString spannableString = new SpannableString(mDrawText);
                if (!TextUtils.isEmpty(s3) && mClickable) {
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), mDrawText.length() - 5, mDrawText.length(), Spanned.SPAN_COMPOSING);
                }

                mStaticLayout = new StaticLayout(spannableString, mPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.2F, 0.0F, false);
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = mStaticLayout.getHeight();
                MoreTextView.this.setLayoutParams(layoutParams);
                requestLayout();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawText == null) return;
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    public boolean canClick() {
        return mClickable;
    }
}
