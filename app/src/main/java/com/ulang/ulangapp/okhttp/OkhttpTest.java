package com.ulang.ulangapp.okhttp;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpTest {

    @Test
    public void init() {
        System.out.println("hello world...");


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
