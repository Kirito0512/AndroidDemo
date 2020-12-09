package com.example.androiddemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddemo.presenter.MyPresenter;

import java.util.Arrays;
import java.util.List;

import static android.view.Window.ID_ANDROID_CONTENT;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener {

    private MyPresenter myPresenter;
    private TextView tv5, tv3, tv4;
    private TextView maskTv;
    private static final String TAG = "xuqiMainActivity";
    private static final String imageUrl = "https://cbshowhot.cdn.changbaimg.com/!/baofang/44557fca2ab879ae295d9a3a0aa9764b_big.jpg";
    private int temp = 0;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        Adapter adapter = new Adapter();
        adapter.setItemClickListener(this);
        List<String> mList = getList();
        adapter.setmList(mList);
        rv.setAdapter(adapter);
        Log.d("xuqi_test", "enum compareto = " + Lifecycle.State.INITIALIZED.compareTo(Lifecycle.State.DESTROYED));
    }

    private List<String> getList() {
        return Arrays.asList("新手引导遮罩", "添加view", "测试NavigationBar", "进入新手引导");
    }

    @Override
    protected void onDestroy() {
        Log.e(getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClickItem(View view) {
        switch ((int) view.getTag()) {
            case 0:
                break;
            case 1:
                FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
                TextView skipTv = new TextView(this);
                skipTv.setText("跳过");
                skipTv.setTextColor(Color.parseColor("#FF121212"));
                skipTv.setTextSize(12);
                skipTv.setGravity(Gravity.CENTER);
                skipTv.setBackground(skipTv.getResources().getDrawable(R.drawable.skip_textview_background, null));
                skipTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("xuqi_test", "click skiptv");
                    }
                });
                skipTv.setTag("HintView");
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(48 * 3, 24 * 3);
                lp.gravity = Gravity.BOTTOM | Gravity.START;
                lp.leftMargin = 45;
                lp.bottomMargin = 45;
                skipTv.setLayoutParams(lp);
                FrameLayout frameLayout = decorView.findViewById(ID_ANDROID_CONTENT);
                frameLayout.addView(skipTv);
                break;
            case 2:
                Log.d(TAG, "new navigation bar height = " + AndroidUtils.getDeviceNavigationBarHeight());
                Log.d(TAG, "csdn navigation bar height = " + AndroidUtils.hasNavigationBar(this));
                String text = AndroidUtils.hasNavigationBar(this) ? "has bar " + AndroidUtils.getDeviceNavigationBarHeight() : "hide bar";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent intent = new Intent(this, GuideActivity.class);
                startActivity(intent);
                break;
        }
    }
}