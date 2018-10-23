package com.ulang.modelmvp.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ulang.modelmvp.BaseFragment;
import com.ulang.modelmvp.IMModelImlp;

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    private LoginContract.Presenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new LoginPresenter(IMModelImlp.getsUserDataController(), this);
    }

    @Override
    public void onLoginError(String message) {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }




}
