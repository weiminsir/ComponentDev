package com.ulang.modelmvp.login;

import com.ulang.modelmvp.APIClient;
import com.ulang.modelmvp.IMBaseController;
import com.ulang.modelmvp.NResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class IMUserControllerImpl extends IMBaseController implements IMUserController {


    public IMUserControllerImpl(final APIClient apiClient) {
       super(apiClient);
    }

    @Override
    public Observable<NResult> register(String username, String password) {
        return apiClient.register(username, password)
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<NResult> login(String username, String password) {
        return apiClient.login(username, password)
                .compose(this.<NResult>applySchedulers());
    }

    @Override
    public Observable<NResult> getUserStatus(String uid) {
        return apiClient.getUserStatus(uid)
                .compose(this.<NResult>applySchedulers());
    }

    @Override
    public Observable<NResult> logout(String uid) {
        return apiClient.logout(uid)
                .compose(this.<NResult>applySchedulers());
    }


}
