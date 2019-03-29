package com.syxz.hbdroid.activitys.network.rxjava

import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import com.syxz.hbdroid.bus.Event
import com.syxz.hbdroid.bus.RxBus
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_rx_bus_memory_leak.*

/**
 * RxBus没有线程泄漏
 */
class RxBusMemoryLeakActivity : BaseActivity() {
    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_rx_bus_memory_leak)

        var disposable = RxBus.getDefault().toObservable(String::class.java).subscribe {
            println("hello $it")
        }

        btnSendMsg.setOnClickListener {
            RxBus.getDefault().post("hello")
        }

        btnRxBusDoUnSububscription.setOnClickListener {
           // weixin://dl/business/?ticket=xxxx
            disposable.dispose()
//            webView.webViewClient= object : WebViewClient() {
//                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                    return super.shouldOverrideUrlLoading(view, request)
//                }
//            }
//            webView.loadUrl("file:///android_asset/a.html")
        }
    }
}
