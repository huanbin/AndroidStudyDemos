package com.syxz.hbdroid.activitys.animation

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.transition.Explode
import android.transition.Transition
import android.view.View
import android.view.Window
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_animation1.*

class AnimationActivity1 : BaseActivity() {

    override fun initViews(intent: Intent) {
        enableTransition()
        setContentView(R.layout.activity_animation1)
        btnStartWithTransition.setOnClickListener {
            startActivity(Intent(this, AnimationActivity2::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }


        var transition = ResourcesCompat.getDrawable(resources, R.drawable.transition_drawable, null) as TransitionDrawable
        imageView3.setImageDrawable(transition)
        var a = false
        btnTransitionDrawable.setOnClickListener {
            a = !a
            if (a) {
                transition.startTransition(1000)
            } else {
                transition.reverseTransition(1000)
            }
        }

    }

    fun enableTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                exitTransition = Explode()
                        .excludeTarget(android.R.id.statusBarBackground, true)
                        .excludeTarget(android.R.id.navigationBarBackground, true)
                enterTransition = Explode()
                        .excludeTarget(android.R.id.statusBarBackground, true)
                        .excludeTarget(android.R.id.navigationBarBackground, true)
            }
        }
    }
}