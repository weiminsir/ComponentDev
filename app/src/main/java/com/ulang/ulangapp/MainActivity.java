package com.ulang.ulangapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    private BehaviorSubject<Integer> _currentIndexSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


//    public Observable<Integer> getCurrentIndexObservable() {
//        return _currentIndexSubject.asObservable().observeOn(AndroidSchedulers.mainThread());
//    }

}
