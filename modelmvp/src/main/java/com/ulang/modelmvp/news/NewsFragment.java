package com.ulang.modelmvp.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ulang.modelmvp.IMModelImlp;
import com.ulang.modelmvp.NResult;

public class NewsFragment extends Fragment implements NewsContract.View {

    NewsContract.Presenter mPresenter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new NewsPresenter(IMModelImlp.getsNewsController(), this);
        mPresenter.onLoadNewsList();

    }

    @Override
    public void onShowNewsList(NResult result) {


    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
