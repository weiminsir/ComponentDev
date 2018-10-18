package com.ulang.modelmvp;

import android.content.Context;
import android.util.Log;

import com.ulang.modelmvp.login.IMUserController;
import com.ulang.modelmvp.login.IMUserControllerImpl;
import com.ulang.modelmvp.news.IMNewsController;
import com.ulang.modelmvp.news.IMNewsControllerImpl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class IMModelImlp {
    private static IMUserController sUserController;
    private static IMNewsController sNewsController;

    public static void init(Context context) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient sAPIClient = retrofit.create(APIClient.class);
        sUserController = new IMUserControllerImpl(sAPIClient);
        sNewsController = new IMNewsControllerImpl(sAPIClient);


    }


    public static void destroy() {
        sUserController = null;
        sNewsController = null;
    }

    public static IMUserController getsUserDataController() {
        return sUserController;
    }

    public static IMNewsController getsNewsController() {
        return sNewsController;
    }
}
