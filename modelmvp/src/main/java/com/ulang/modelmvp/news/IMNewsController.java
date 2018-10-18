package com.ulang.modelmvp.news;

import com.ulang.modelmvp.NResult;

import rx.Observable;


public interface IMNewsController {

    Observable<NResult> getNews();

}
