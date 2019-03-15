package com.syxz.hbdroid.activitys.activitystack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.MainActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        btnGoHome.setOnClickListener { ActivityUtils.clearTop2SpecialActivity(MainActivity::class.java) }
    }
}
