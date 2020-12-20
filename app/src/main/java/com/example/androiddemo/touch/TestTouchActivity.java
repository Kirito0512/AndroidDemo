package com.example.androiddemo.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.androiddemo.R;
import com.example.mylibrary.viewhint.AndroidViewHintHelper;
import com.example.mylibrary.viewhint.HintInfo.ViewHintInfo;

public class TestTouchActivity extends AppCompatActivity {
    private static final String TAG = "xuqi_TestTouchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch);
        TextView tv = findViewById(R.id.textView3);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: " + event.getAction() + " rawx = " + event.getRawX() + " rawy = " + event.getRawY());
                return true;
            }
        });
        tv.post(new Runnable() {
            @Override
            public void run() {
                new AndroidViewHintHelper(TestTouchActivity.this).addHintView(R.id.textView3, "测试", ViewHintInfo.TOP).show();
            }
        });
    }

    public static void showActivity(AppCompatActivity context) {
        Intent intent = new Intent(context, TestTouchActivity.class);
        context.startActivity(intent);
    }
}