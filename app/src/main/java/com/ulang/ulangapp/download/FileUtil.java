package com.ulang.ulangapp.download;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class FileUtil {

    public boolean DownloadSmallFile(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.1.21:8088/wav/test.wav").build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return false;
            }

            ResponseBody body = response.body();
            long contentLength = body.contentLength();
            Log.d("weiminsir", "contentLength: " + contentLength);
            BufferedSource source = body.source();
            File file = new File("");
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            sink.writeAll(source);
            sink.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
