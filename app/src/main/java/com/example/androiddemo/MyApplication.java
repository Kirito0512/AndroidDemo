package com.example.androiddemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import static android.app.ActivityManager.MOVE_TASK_WITH_HOME;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private int activeActivityCount = 0;
    private boolean mIsStartBoot = true;
    public static int topActivityTaskId;


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                activeActivityCount++;
                if (activeActivityCount == 1) {
                    if (mIsStartBoot) {
                        mIsStartBoot = false;
                        // 冷启动
                        // TODO: 2020/10/14
                    } else {
                        moveTaskToFront(activity);
                    }
                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                activeActivityCount--;
                if (activeActivityCount == 0) {
                    topActivityTaskId = activity.getTaskId();
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    public void moveTaskToFront(Activity activity) {
        if (activity.getTaskId() == topActivityTaskId) {
            return;
        }
        ActivityManager am = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> taskList = am.getAppTasks();
        for (ActivityManager.AppTask appTask : taskList) {
//            if (appTask.getTaskInfo().taskId == topActivityTaskId) {
//                Log.d(TAG, "moveTaskToFront: task id = " + topActivityTaskId);
//                Intent intent = new Intent(activity, SingleTaskOtherTaskActivity.class);
//                startActivity(intent);
//                appTask.moveToFront();
//                return;
//            }
        }
    }
}
