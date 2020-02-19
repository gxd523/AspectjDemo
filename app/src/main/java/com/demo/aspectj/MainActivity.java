package com.demo.aspectj;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @MethodExecutionTime(value = "MainActivity点击事件")
    public void onClick(View view) {
        long begin = System.currentTimeMillis();
        SystemClock.sleep(new Random().nextInt(5000));
        Log.d("gxd", (System.currentTimeMillis() - begin) + "...");
    }
}
