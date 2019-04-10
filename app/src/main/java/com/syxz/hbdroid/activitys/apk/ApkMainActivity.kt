package com.syxz.hbdroid.activitys.apk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import com.syxz.hbdroid.utils.ApkUtils
import kotlinx.android.synthetic.main.activity_apk_main.*

class ApkMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apk_main)

        btnInstallApk.setOnClickListener {
            ApkUtils.install(this@ApkMainActivity,"")
        }
    }
}
