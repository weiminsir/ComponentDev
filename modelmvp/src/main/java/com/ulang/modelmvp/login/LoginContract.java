package com.ulang.modelmvp.login;

import com.ulang.modelmvp.BasePresenter;
import com.ulang.modelmvp.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void onLoginError(String message);
        void onLoginSuccess();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);

    }
}
