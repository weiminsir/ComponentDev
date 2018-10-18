package com.ulang.router;

import android.app.Application;
import android.os.Handler;
import android.os.StrictMode;

import com.nshmura.strictmodenotifier.StrictModeNotifier;

public class MyApp extends Application {

    public static MyApp getApp() {
        return instance;
    }

    private static MyApp instance;


    @Override

    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            //setup this library
            StrictModeNotifier.install(this);

            //setup StrictMode.
            //
            // penaltyLog() should be called for strictmode-notifier
            //
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .permitDiskReads()
                            .permitDiskWrites()
                            .penaltyLog() // Must!
                            .build();
                    StrictMode.setThreadPolicy(threadPolicy);

                    StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog() // Must!
                            .build();
                    StrictMode.setVmPolicy(vmPolicy);
                }
            });
        }

    }
}
