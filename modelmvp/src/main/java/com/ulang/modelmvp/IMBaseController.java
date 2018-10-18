package com.ulang.modelmvp;

import com.ulang.modelmvp.APIClient;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class IMBaseController {

    protected APIClient apiClient;

    public IMBaseController(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    final Observable.Transformer schedulersTransformer =
            new Observable.Transformer() {
                @Override
                public Object call(Object observable) {
                    return ((Observable) observable).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };

    @SuppressWarnings("unchecked")
    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
