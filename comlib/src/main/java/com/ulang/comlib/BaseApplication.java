package com.ulang.comlib;

import android.app.Application;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initApp();



    }


    public abstract void initApp();
}
