package com.ulang.modelmvp.mvpreference;

import android.os.Bundle;

import com.ulang.modelmvp.BaseActivity;
import com.ulang.modelmvp.R;

public abstract class MVPBaseActivity<V, P extends MVPBasePresenter<V>> extends BaseActivity {
    private static final String TAG = "MVPBaseActivity";
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpbase);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();
}
