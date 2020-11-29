package com.example.androiddemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 退出包房
 */
public class KtvExitRoomDialog extends Dialog implements View.OnClickListener {
    private TextView exitTv, smallRoomTv;
    private KtvExitRoomDialogListener ktvExitRoomDialogListener;
    public KtvExitRoomDialog(Context context) {
        super(context, R.style.translunt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ktv_exit_room_dialog);
        exitTv = findViewById(R.id.confirm_btn);
        smallRoomTv = findViewById(R.id.small_btn);
        exitTv.setOnClickListener(this);
        smallRoomTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
//                DataStats.onEvent("房间_退出房间_离开房间");
                dismiss();
//                KtvRoomEventTracker.getInstance().report(ActionNodeReport.ACTION_CLICK, "ktv房间页_退出弹窗", "退出");
                if (ktvExitRoomDialogListener != null) {
                    ktvExitRoomDialogListener.onClickExit();
                }
                break;
            case R.id.small_btn:
//                KtvRoomEventTracker.getInstance().report(ActionNodeReport.ACTION_CLICK, "ktv房间页_退出弹窗", "最小化");
                if (ktvExitRoomDialogListener != null) {
                    ktvExitRoomDialogListener.onClickSmallRoom();
                }
                dismiss();
                break;
        }
    }

    public void setKtvExitRoomDialogListener(KtvExitRoomDialogListener ktvExitRoomDialogListener) {
        this.ktvExitRoomDialogListener = ktvExitRoomDialogListener;
    }

    public interface KtvExitRoomDialogListener {
        void onClickExit();
        void onClickSmallRoom();
    }
}
