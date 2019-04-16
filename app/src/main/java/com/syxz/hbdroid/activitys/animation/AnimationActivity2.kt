package com.syxz.hbdroid.activitys.animation

import android.content.Intent
import android.os.Build
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_animation2.*

class AnimationActivity2 : BaseActivity() {

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_animation2)
        btnStartWithTransition2.setOnClickListener {
            // reverse the scene transition animation when you finish the second activity instead of Activity.finish()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            }
        }
    }
}