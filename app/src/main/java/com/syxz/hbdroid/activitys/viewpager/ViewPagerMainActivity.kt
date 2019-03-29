package com.syxz.hbdroid.activitys.viewpager

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.FrameMetrics
import android.view.Window
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.viewpager.splash.SplashActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_view_pager_main.*

class ViewPagerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.addOnFrameMetricsAvailableListener(object : Window.OnFrameMetricsAvailableListener {
                override fun onFrameMetricsAvailable(window: Window?, frameMetrics: FrameMetrics?, dropCountSinceLastInvocation: Int) {
                   var metric= frameMetrics?.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION)
                    println("metric=$metric")
                }
            }, object : Handler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    println(msg.toString())
                }
            })
        }

        btnSplash.setOnClickListener {
            ActivityUtils.start(this, SplashActivity::class.java)
        }

        btnPageTransform.setOnClickListener {
            ActivityUtils.start(this, PageTransformActivity::class.java)
        }
    }
}
