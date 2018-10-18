package com.ulang.modelmvp.news;

import com.ulang.modelmvp.NResult;
import com.ulang.modelmvp.BasePresenter;
import com.ulang.modelmvp.BaseView;

public class NewsContract {


    public interface View extends BaseView<Presenter> {

        void onShowNewsList(NResult result);

    }

    public interface Presenter extends BasePresenter {
        void onLoadNewsList();

    }
}
