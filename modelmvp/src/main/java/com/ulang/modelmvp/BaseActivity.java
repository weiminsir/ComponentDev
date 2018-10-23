package com.ulang.modelmvp;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity implements BaseView<BasePresenter>{
    @Override
    public void setPresenter(BasePresenter presenter) {

    }


}