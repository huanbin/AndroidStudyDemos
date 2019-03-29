package com.syxz.hbdroid.activitys.image

import android.content.ActivityNotFoundException
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_image_main.*
import android.content.Intent
import android.net.Uri


class ImageMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_main)
//        btnBitmapMemory.setOnClickListener {
//            ActivityUtils.start(this, BitmapActivity::class.java)
//        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                var isHttp = url.startsWith("http://", true)
                var isHttps = url.startsWith("https://", true)
                if (isHttp || isHttps) {
                    view?.loadUrl(url)
                } else {
                    try {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    } catch (e: Throwable) {
                        // TODO: handle exception
                    }
                }
                return true
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://engine.tuicoco.com/index/activity?appKey=2cWAe3v3ebq2mV8Brbyj52wSK75R&adslotId=272283")
    }
}
