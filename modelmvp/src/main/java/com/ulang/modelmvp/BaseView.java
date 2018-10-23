package com.ulang.modelmvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by WangQi on 2016/12/12.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    <T> LifecycleTransformer<T> bindToLife();



}
