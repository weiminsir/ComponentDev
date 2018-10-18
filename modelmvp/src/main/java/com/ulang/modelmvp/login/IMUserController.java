package com.ulang.modelmvp.login;

import com.ulang.modelmvp.NResult;

import rx.Observable;

public interface IMUserController {


    Observable<NResult> register(String username, String password);

    Observable<NResult> login(String username, String password);

    Observable<NResult> getUserStatus(String uid);

    Observable<NResult> logout(String uid);


}
