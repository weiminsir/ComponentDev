package com.ulang.modelmvp.news;

import com.ulang.modelmvp.APIClient;
import com.ulang.modelmvp.IMBaseController;
import com.ulang.modelmvp.NResult;

import rx.Observable;

public class IMNewsControllerImpl extends IMBaseController implements IMNewsController {

    public IMNewsControllerImpl(APIClient apiClient) {
        super(apiClient);
    }


    @Override
    public Observable<NResult> getNews() {
        return apiClient.getNews().compose(this.<NResult>applySchedulers());
    }
}
