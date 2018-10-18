package com.ulang.ulangapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MyViewGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_guide);


        findViewById(R.id.btn_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyViewGuideActivity.this,MyScrollViewActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("weimisnir", "width=");
//        showDisplayMetrics();
        Log.d("weiminsir", "onResume2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("weiminsir", "onPause2");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("weiminsir", "onStart2");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("weiminsir", "onStop2");
    }

}
