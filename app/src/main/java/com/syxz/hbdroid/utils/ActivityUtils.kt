package com.syxz.hbdroid.utils

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.syxz.hbdroid.base.BaseApplication
import java.util.*

object ActivityUtils {

    /**
     * 添加activity到容器中
     */
    fun add(activity: Activity) {//Activity? 可能为空
        BaseApplication.activityContainers.add(activity)//Activity!!断言不为空
    }

    /**
     * 移除activity容器中activity
     */
    fun remove(activity: Activity?) {
        BaseApplication.activityContainers.remove(activity)
    }

    /**
     * 启动指定的Activity
     */
    fun <T> start(activity: Activity, clazz: Class<T>) {
        val intent = Intent(activity, clazz)
        activity.startActivity(intent)
    }

    /**
     * finish指定的Activity
     */
    fun finish(activity: Activity) {
        activity.finish()
    }

    /**
     * 移除某个Activity上面的所有Activity
     * 显示栈中指定的Activity
     */
    fun <T> clearTop2SpecialActivity(activityDest: Class<T>) {
        for (i in BaseApplication.activityContainers.size - 1 downTo 1) {
            var activity = BaseApplication.activityContainers[i]
            if (TextUtils.equals(activity.javaClass.simpleName, activityDest.simpleName)) {
                return
            } else {
                activity.finish()
            }
        }
    }

    fun clearTop2SpecialActivity(activityDest: Activity) {
        for (i in BaseApplication.activityContainers.size - 1 downTo 1) {
            var activity = BaseApplication.activityContainers[i]
            if (activity == activityDest) {
                return
            } else {
                activity.finish()
            }
        }
    }
}
