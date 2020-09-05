package com.demo.aspectj;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("test");
        button.setTextSize(30);
        button.setOnClickListener(this);
        setContentView(button);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) button.getLayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
    }

    @MethodExecutionTime(value = "MainActivity点击事件")
    @Override
    public void onClick(View v) {
        long begin = System.currentTimeMillis();
        SystemClock.sleep(new Random().nextInt(5000));
        Log.d("gxd", (System.currentTimeMillis() - begin) + "...");
    }
}
