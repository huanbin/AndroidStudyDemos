package com.syxz.hbdroid.activitys.animation

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_animation1.*

/**
 * Activity过渡动画
 */
class AnimationActivity1 : BaseActivity() {

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_animation1)
        btnStartWithTransition.setOnClickListener {
            startActivity(Intent(this@AnimationActivity1, AnimationActivity2::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@AnimationActivity1, Pair.create(shareView, "share_view") as Pair<View, String>, Pair.create(imageView, "share_image") as Pair<View, String>).toBundle())
        }
    }
}