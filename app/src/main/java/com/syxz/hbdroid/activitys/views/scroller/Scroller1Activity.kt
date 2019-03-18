package com.syxz.hbdroid.activitys.views.scroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_scroller_usage.*

class ScrollerUsageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller_usage)
        btStartScroll.setOnClickListener {
            scrollView.startScroll()
        }
    }
}
