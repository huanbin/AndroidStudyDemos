package com.syxz.hbdroid.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.didichuxing.doraemonkit.DoraemonKit
import com.squareup.leakcanary.LeakCanary
import com.syxz.hbdroid.utils.ActivityUtils
import java.util.*

class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        var activityContainers = Stack<Activity>()

        lateinit var instance: BaseApplication

    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        ActivityUtils.remove(activity!!)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        ActivityUtils.add(activity!!)
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        registerActivityLifecycleCallbacks(this)
        DoraemonKit.install(this)
    }
}
