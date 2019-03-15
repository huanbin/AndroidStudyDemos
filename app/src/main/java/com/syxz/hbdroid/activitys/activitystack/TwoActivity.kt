package com.syxz.hbdroid.activitys.activitystack

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : BaseActivity() {

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_two)
        btnBack.setOnClickListener { finish() }
        btnGoThird.setOnClickListener { ActivityUtils.start(this,ThirdActivity::class.java) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
}
