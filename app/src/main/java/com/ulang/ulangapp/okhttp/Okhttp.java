package com.ulang.ulangapp.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Okhttp {


    //缓存文件夹
    File cacheFile = new File("", "cache");
    //缓存大小为10M
    int cacheSize = 10 * 1024 * 1024;
    //创建缓存对象
    Cache cache = new Cache(cacheFile, cacheSize);

    OkHttpClient client = new OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return null;



                }
            })
            .build();

}
