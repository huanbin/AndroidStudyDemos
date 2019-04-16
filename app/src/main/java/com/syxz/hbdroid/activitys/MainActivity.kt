package com.syxz.hbdroid.activitys

import android.content.Intent
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.activitystack.TwoActivity
import com.syxz.hbdroid.activitys.animation.AnimationActivity1
import com.syxz.hbdroid.activitys.animation.AnimationMainActivity
import com.syxz.hbdroid.activitys.apk.ApkMainActivity
import com.syxz.hbdroid.activitys.backgroundtask.JobScheduleActivity
import com.syxz.hbdroid.activitys.image.ImageMainActivity
import com.syxz.hbdroid.activitys.clip.ClipChildrenActivity
import com.syxz.hbdroid.activitys.clip.RecyclerViewClipActivity
import com.syxz.hbdroid.activitys.network.NetworkMainActivity
import com.syxz.hbdroid.activitys.recyclerview.RecyclerviewMainActivity
import com.syxz.hbdroid.activitys.statusbar.StatusbarActivity
import com.syxz.hbdroid.activitys.viewpager.ViewPagerMainActivity
import com.syxz.hbdroid.activitys.views.ViewMainActivity
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

        btnClip.setOnClickListener {
            ActivityUtils.start(this, RecyclerViewClipActivity::class.java)
        }
        btnClipChildren.setOnClickListener {
            ActivityUtils.start(this, ClipChildrenActivity::class.java)
        }
        btnWindow.setOnClickListener {
            ActivityUtils.start(this, StatusbarActivity::class.java)
        }

        btnCustomViews.setOnClickListener {
            ActivityUtils.start(this, ViewMainActivity::class.java)
        }

        btnViewPager.setOnClickListener {
            ActivityUtils.start(this, ViewPagerMainActivity::class.java)
        }

        btnImageHandle.setOnClickListener {
            ActivityUtils.start(this, ImageMainActivity::class.java)
        }

        btnApk.setOnClickListener {
            ActivityUtils.start(this, ApkMainActivity::class.java)
        }

        btnAnimation.setOnClickListener {
            ActivityUtils.start(this, AnimationMainActivity::class.java)
        }
    }
}
