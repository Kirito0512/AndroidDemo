package com.example.androiddemo

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    val test: String
        get() = this.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tv = findViewById<TextView>(R.id.tv2)
        tv.setOnClickListener {
            val intent = Intent(MainActivity2@ this, SingleTaskOtherTaskActivity::class.java)
            startActivity(intent)

//            this.moveTaskToBack(true)
        }

        val tv2 = findViewById<TextView>(R.id.textView2);
        tv2.setOnClickListener { directMoveToTask(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d(Companion.TAG, "onCreate: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Companion.TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Companion.TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Companion.TAG, "onDestroy: ")
    }

    companion object {
        private const val TAG = "MainActivity2test"
    }

    fun directMoveToTask(activity: Activity) {
        val am: ActivityManager = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks: List<ActivityManager.AppTask> = am.appTasks
        tasks.forEach {
//            if (MyApplication.topActivityTaskId == it.taskInfo.taskId) {
//                Log.d(Companion.TAG, "directMoveToTask: ")
//                val intent = Intent(this, SingleTaskOtherTaskActivity::class.java)
//                startActivity(intent)

//                it.moveToFront()
//            }
        }
    }
}