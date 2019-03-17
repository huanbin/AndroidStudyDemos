package com.syxz.hbdroid.activitys

import android.content.Intent
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.activitystack.TwoActivity
import com.syxz.hbdroid.activitys.animation.AnimationActivity1
import com.syxz.hbdroid.activitys.backgroundtask.JobScheduleActivity
import com.syxz.hbdroid.activitys.network.NewTypeMonitorNetworkActivity
import com.syxz.hbdroid.activitys.network.rxjava.Rxjava2RetrofitActivity
import com.syxz.hbdroid.activitys.clip.ClipChildrenActivity
import com.syxz.hbdroid.activitys.clip.RecyclerViewClipActivity
import com.syxz.hbdroid.activitys.network.NetworkMainActivity
import com.syxz.hbdroid.activitys.recyclerview.RecyclerviewMainActivity
import com.syxz.hbdroid.activitys.statusbar.StatusbarActivity
import com.syxz.hbdroid.activitys.viewpager.PageTransformActivity
import com.syxz.hbdroid.activitys.viewpager.splash.SplashActivity
import com.syxz.hbdroid.activitys.views.draganddrop.DragViewActivity
import com.syxz.hbdroid.activitys.views.scroller.ScrollerUsageActivity
import com.syxz.hbdroid.base.BaseActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_main)

        btnSetBadge.setOnClickListener {
            ActivityUtils.start(this, BadgeActivity::class.java)
        }

        btnActivityStack.setOnClickListener {
            ActivityUtils.start(this, TwoActivity::class.java)
        }

        btnRv.setOnClickListener {
            ActivityUtils.start(this, RecyclerviewMainActivity::class.java)
        }

        btnNetwork.setOnClickListener {
            ActivityUtils.start(this, NetworkMainActivity::class.java)
        }

        btnSchduleJob.setOnClickListener {
            ActivityUtils.start(this, JobScheduleActivity::class.java)
        }

        btnSplash.setOnClickListener {
            ActivityUtils.start(this, SplashActivity::class.java)
        }

        btnAnimation.setOnClickListener {
            ActivityUtils.start(this, AnimationActivity1::class.java)
        }

        btnClip.setOnClickListener {
            ActivityUtils.start(this, RecyclerViewClipActivity::class.java)
        }
        btnClipChildren.setOnClickListener {
            ActivityUtils.start(this, ClipChildrenActivity::class.java)
        }
        btnWindow.setOnClickListener {
            ActivityUtils.start(this, StatusbarActivity::class.java)
        }

        btnScrollview.setOnClickListener {
            ActivityUtils.start(this, ScrollerUsageActivity::class.java)
        }

        btnDragDrop.setOnClickListener {
            ActivityUtils.start(this, DragViewActivity::class.java)
        }

        btnPageTransform.setOnClickListener {
            ActivityUtils.start(this, PageTransformActivity::class.java)
        }
    }
}
