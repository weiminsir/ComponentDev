package com.ulang.componentbase;

public class EmptyAccountServiceImpl implements IAccountService {


    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
