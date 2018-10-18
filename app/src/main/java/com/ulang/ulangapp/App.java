package com.ulang.ulangapp;


import com.ulang.comlib.BaseApplication;
import com.ulang.ulangapp.utils.Density;

public class App extends BaseApplication {

    @Override
    public void initApp() {



        Density.setDensity(this);

    }
}
