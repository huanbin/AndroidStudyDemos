package com.syxz.hbdroid.activitys.clip

import android.content.Intent
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_clipchildren.*

class ClipChildrenActivity:BaseActivity() {
    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_clipchildren)
        btSwitchClipChildren.setOnClickListener {
            rootViewLayout.clipChildren=!rootViewLayout.clipChildren
        }
    }
}