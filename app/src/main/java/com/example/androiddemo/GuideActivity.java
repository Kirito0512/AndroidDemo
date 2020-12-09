package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androiddemo.viewhint.AndroidViewHintHelper;
import com.example.androiddemo.viewhint.HintInfo.ViewHintInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Adapter adapter;
    private TextView tv, circleTv, guideTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        List<String> list = Arrays.asList("第一项", "第二项", "第三项");
        adapter = new Adapter();
        adapter.setmList(list);
        rv = findViewById(R.id.rv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        tv = findViewById(R.id.textView2);
        circleTv = findViewById(R.id.circle_tv);
        guideTv = findViewById(R.id.show_guide_tv);
        guideTv.setOnClickListener(v -> initHintView());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void initHintView() {
        View view = rv.getLayoutManager().findViewByPosition(0);
        new AndroidViewHintHelper(this)
                .addHintView(tv, "我是普通的TextView", ViewHintInfo.LOWER_RIGHT, 0)
                .addHintView(circleTv, "我有圆形背景", ViewHintInfo.BOTTOM, AndroidUtils.dpToPixel(5))
                .addHintView(view, "我是RecyclerView的item", ViewHintInfo.LOWER_LEFT, 0)
                .show();
    }
}