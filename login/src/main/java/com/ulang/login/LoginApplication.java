package com.ulang.login;

import com.ulang.comlib.BaseApplication;
import com.ulang.componentbase.ServiceFactory;

public class LoginApplication extends BaseApplication {

    @Override
    public void initApp() {
        ServiceFactory.getInstance().setAccountService(new AccountServiceImpl());

    }
}
