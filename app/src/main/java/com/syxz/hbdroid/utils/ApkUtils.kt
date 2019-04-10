package com.syxz.hbdroid.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.text.TextUtils

import java.io.File

/**
 * Created by zhishi on 2017/6/27.
 */

object ApkUtils {

    /**
     * @param context
     * @param apkPath apk文件地址
     */
    fun install(context: Context, apkPath: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setDataAndType(FileProvider.getUriForFile(context, context.packageName + ".fileprovider", File(apkPath)),
                    "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(File(apkPath)),
                    "application/vnd.android.package-archive")
        }
        //如果没有i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);这一步的话，最后安装好了，点打开，是不会打开新版本应用的。
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //读取uri文件权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
        //如果没有android.os.Process.killProcess(android.os.Process.myPid());最后不会提示完成、打开。
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    fun getApkFileNameByVersion(apkVersion: String): String {
        return Constant.DOWNLOAD_APK_NAME_PREFIX + apkVersion + ".apk"
    }

    /**
     * 检测是否安装该app
     *
     * @param packageName
     * @return
     */
    fun isInstallApp(context: Context, packageName: String): Boolean {
        try {
            val installerPackageName = context.packageManager.getInstallerPackageName(packageName)
            return !TextUtils.isEmpty(installerPackageName)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return false
    }
}
