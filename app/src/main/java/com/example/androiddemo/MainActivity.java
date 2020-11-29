package com.example.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.example.androiddemo.presenter.MyPresenter;
import com.example.androiddemo.viewhint.AndroidViewHintHelper;
import com.example.androiddemo.viewhint.ViewHintInfo;

public class MainActivity extends AppCompatActivity {

    private MyPresenter myPresenter;
    private TextView tv5, tv3, tv4;
    private TextView maskTv;
    private static final String TAG = "xuqiMainActivity";
    private static final String imageUrl = "https://cbshowhot.cdn.changbaimg.com/!/baofang/44557fca2ab879ae295d9a3a0aa9764b_big.jpg";
    private int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.e(getClass().getSimpleName(), "onCreate");
//        myPresenter = new MyPresenter();
//        getLifecycle().addObserver(myPresenter);
        tv5 = findViewById(R.id.textView5);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        maskTv = findViewById(R.id.textView14);
        maskTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomView customView = new CustomView(v.getContext());
                customView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
                decorView.addView(customView);
//                Intent intent = new Intent(MainActivity.this, MaskActivity.class);
//                startActivity(intent);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MaskActivity.class);
//                startActivity(intent);
                new AndroidViewHintHelper(MainActivity.this)
                        .addHintView(R.id.textView3, "点击这里，可以随时下麦，放弃演唱当前歌曲", ViewHintInfo.BOTTOM)
                        .addHintView(R.id.textView14, "我是textView 14", ViewHintInfo.UPPER_LEFT)
                        .show();
            }
        });

//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick run: name = " + Thread.currentThread().getName());
//                new Thread(new Runnable() {
//                    @SuppressLint("DefaultLocale")
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "run: name = " + Thread.currentThread().getName());
//                        tv.setText(String.format("text child thread click%d", temp++));
//                    }
//                }).start();
//            }
//        });

//        final RadioButton ra = findViewById(R.id.room_play_sing_ear_monitor_rb);
//        ra.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ra.isChecked()) {
//                    ra.setChecked(false);
//                } else {
//                    ra.setChecked(true);
//                }
//            }
//        });

        Log.d("xuqi_test", "enum compareto = " + Lifecycle.State.INITIALIZED.compareTo(Lifecycle.State.DESTROYED));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Log.d(TAG, "onAttachedToWindow: tv width = " + tv.getMeasuredWidth());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KtvExitRoomDialog dialog = new KtvExitRoomDialog(MainActivity.this);
                dialog.show();
//                new Thread(new Runnable() {
//                    @SuppressLint("DefaultLocale")
//                    @Override
//                    public void run() {
//                                        MyDialog dialog = new MyDialog();
//                dialog.show(getSupportFragmentManager(), MyDialog.class.getSimpleName());
////                        Log.d(TAG, "tv2 run: thread name = " + Thread.currentThread().getName());
////                        Log.d(TAG, "tv2 run: main thread name =  " + getMainLooper().getThread().getName());
////                        tv3.setText(String.format("tv2 text child thread click%d", temp++));
//                    }
//                }).start();
            }
        });
        tv4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyDialog dialog = new MyDialog();
//                dialog.show(getSupportFragmentManager(), MyDialog.class.getSimpleName());
//                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
//                startActivity(intent);
//                new Thread(new Runnable() {
//                    @SuppressLint("DefaultLocale")
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "tv3 run: thread name = " + Thread.currentThread().getName());
//                        Log.d(TAG, "tv3 run: main thread name =  " + getMainLooper().getThread().getName());
//                        tv5.setText("子线程更新别的View " + temp++);
//                        tv2.setText(String.format("text child thread click%d", temp++));
//                    }
//                }).start();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        Log.e(getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
    }
}