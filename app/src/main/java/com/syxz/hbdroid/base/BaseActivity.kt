package com.syxz.hbdroid.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews(intent)
    }

    abstract fun initViews(intent: Intent)
}
