package com.ulang.modelmvp.mvpreference;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ulang.modelmvp.BaseFragment;

public abstract class MVPBaseFragment<V,P extends MVPBasePresenter<V>> extends BaseFragment {
    private static final String TAG = "MVPBaseFragment";
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();
}