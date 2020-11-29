package com.example.androiddemo.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class MyPresenter : IPresenter {
    override fun onCreate(owner: LifecycleOwner) {
        Log.e(javaClass.simpleName, "onCreate")
    }

    override fun onDestroy() {
        Log.e(javaClass.simpleName, "onDestroy")
    }

    override fun onLifeCycleChanged(owner: LifecycleOwner, eventObject: Lifecycle.Event) {
        Log.e(javaClass.simpleName, "event = ${eventObject.name}")
    }

}