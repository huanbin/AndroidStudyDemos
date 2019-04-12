package com.syxz.hbdroid.activitys.views.webview

import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.syxz.hbdroid.utils.ApkUtils

class SystemDownloadUtil {

    fun download(context: Context, downloadPath: String, savePath: String) {

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val request = Request(Uri.parse(downloadPath))
        //request.setVisibleInDownloadsUi(true)//默认true
        //request.setTitle("")//默认显示下载文件的名称
        request.allowScanningByMediaScanner()
        //如果允许下载显示notifications，将会显示在通知里
        //request.setDescription("正在下载")
        //下载保存地址(注意子目录包含文件名称)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, savePath)
        //下载progress时显示（下载完成时不可见）
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE)
        //request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadId = downloadManager.enqueue(request)
    }


    class DownloadBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val action = intent.action
            when (action) {
                DownloadManager.ACTION_DOWNLOAD_COMPLETE -> {
                    //下载完成
                    val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)
                    println("id=$downloadId,下载完成")
                    var apkPath = downloadManager.getUriForDownloadedFile(downloadId)
                    println("apkPath=$apkPath")
                    //自动安装
                    ApkUtils.installOtherApp(context as WebViewLeakActivity, apkPath)
                }
                DownloadManager.ACTION_NOTIFICATION_CLICKED -> {
                    //点击下载通知
                    val longArrayDownloadIds = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS)
                    longArrayDownloadIds.forEach {
                        println("用户点击了:$it")
                        var query = DownloadManager.Query().setFilterById(it)
                        var cursor = downloadManager.query(query)
                        cursor?.moveToNext()
                        var status = cursor?.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                        if (DownloadManager.STATUS_PAUSED == status) {


                        } else if (DownloadManager.STATUS_RUNNING == status) {

                        }
                    }
                }
            }
        }
    }

    class InstallPackageBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_PACKAGE_ADDED -> {
                    //安装新包了（注意schemeSpecificPart，配合IntentFilter）
                    val packageName = intent.data.schemeSpecificPart
                    Toast.makeText(context, "安装{$packageName}成功", Toast.LENGTH_LONG).show()
                    (context as WebViewLeakActivity).updateInstallResult("安装{$packageName}成功")
                }
            }
        }
    }
}
