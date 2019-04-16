package com.syxz.hbdroid.activitys.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main_animation.*

class AnimationMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animation)

        btnActivityAnimation.setOnClickListener {
            ActivityUtils.start(this, AnimationActivity1::class.java)
        }

        btnVectorDrawable.setOnClickListener {
            ActivityUtils.start(this, VectorDrawableActivity::class.java)
        }
    }
}
