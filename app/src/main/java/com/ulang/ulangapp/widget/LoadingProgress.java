package com.ulang.ulangapp.widget;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ulang.ulangapp.R;

public class LoadingProgress extends DialogFragment {

    private View mRootView;
    private ProgressWheel mProgressWheel;

    public FragmentActivity activity;
    private String mText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);

        activity = getActivity();
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.dialog_load_progress, container, false);
            mProgressWheel = (ProgressWheel) mRootView.findViewById(R.id.progress);
        }

        if (!TextUtils.isEmpty(mText)) {
            mProgressWheel.setText(mText);
        }

        return mRootView;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.DialogFragment#onStart()
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgressWheel.spin();
    }

    @Override
    public void onPause() {
        super.onPause();
        mProgressWheel.stopSpinning();
    }

    public void setText(String text) {
        mText = text;
    }
}
