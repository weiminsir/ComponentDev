package com.ulang.modelmvp.mvpreference;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class MVPBasePresenter<V> {

    protected Reference<V> mViewRef;//View 接口类型的弱引用

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //每个Presenter都会有初始化的工作，可以在这里统一处理
    // 当然也可以不处理，这里只是一个公用的示范方法
    public abstract void start();

    //这里也可以理解为一个公用的示范方法
    public abstract void update();
}
