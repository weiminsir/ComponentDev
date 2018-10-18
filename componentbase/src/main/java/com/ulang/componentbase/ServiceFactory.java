package com.ulang.componentbase;

public class ServiceFactory {

    private IAccountService accountService;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return InnerFactoryService.instance;
    }

    private static class InnerFactoryService {
        final static ServiceFactory instance = new ServiceFactory();
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public IAccountService getAccountService() {

        if (null == accountService) {
            return new EmptyAccountServiceImpl();
        }
        return accountService;
    }
}
