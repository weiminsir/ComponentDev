package com.ulang.modelmvp.login;


import com.ulang.modelmvp.NResult;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    IMUserController module;
    private Disposable disposable;

    public LoginPresenter(IMUserController module, LoginContract.View view) {
        this.module = module;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(String username, String password) {
        disposable = module.login(username, password)
                .compose(view.<NResult>bindToLife())
                .subscribe(new Consumer<NResult>() {
                    @Override
                    public void accept(NResult nResult) throws Exception {

                    }
                });


    }

    @Override
    public void unsubscribe() {

        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void subscribe() {

    }
}
