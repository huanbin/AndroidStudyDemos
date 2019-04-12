package com.syxz.hbdroid.activitys.views.webview

import android.app.DownloadManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview_leak.*

class WebViewLeakActivity : BaseActivity() {

    private var url = "file:///android_asset/a.html"
    private lateinit var downloadReceiver: SystemDownloadUtil.DownloadBroadcastReceiver
    private lateinit var installReceiver: SystemDownloadUtil.InstallPackageBroadcastReceiver

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_webview_leak)
        initWebview()
        registerReceiver()
    }

    fun updateInstallResult(result:String){
        tvInstallResult?.text = result
    }

    private fun registerReceiver() {
        downloadReceiver = SystemDownloadUtil.DownloadBroadcastReceiver()
        var intentFilter = IntentFilter().apply {
            addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        }
        registerReceiver(downloadReceiver, intentFilter)

        installReceiver = SystemDownloadUtil.InstallPackageBroadcastReceiver()
        var intentFilter2 = IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addDataScheme("package")
        }
        registerReceiver(installReceiver, intentFilter2)
    }

    fun initWebview() {
        webView1.settings.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView1.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webView1.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                var a = url?.startsWith("http://") ?: false
                var b = url?.startsWith("https://") ?: false
                if (a xor b) {
                    view?.loadUrl(url)
                }
                return true
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                var url = request?.url.toString()
                var a = url?.startsWith("http://")
                var b = url?.startsWith("https://")
                if (a xor b) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView1.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            println("准备下载：url=$url,mimetype=$mimetype,contentLength=$contentLength")
            SystemDownloadUtil().download(this@WebViewLeakActivity, url, "syxz/" + url.subSequence(url.lastIndexOf("/"), url.length))
        }
        webView1.loadUrl(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
        unregisterReceiver(installReceiver)
    }

}
