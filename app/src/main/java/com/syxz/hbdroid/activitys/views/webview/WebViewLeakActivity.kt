package com.syxz.hbdroid.activitys.views.webview

import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview_leak.*

class WebViewLeakActivity : BaseActivity(){
    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_webview_leak)
        var url="http://www.jd.com/"
        webView1.webViewClient= object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        webView1.loadUrl(url)
    }

}
