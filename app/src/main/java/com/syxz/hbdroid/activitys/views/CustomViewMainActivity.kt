package com.syxz.hbdroid.activitys.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.views.draganddrop.DragView1Activity
import com.syxz.hbdroid.activitys.views.nestedscroll.NestedScrollActivity1
import com.syxz.hbdroid.activitys.views.pie.PieActivity
import com.syxz.hbdroid.activitys.views.scroller.Scroller1Activity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_custom_view_main.*
import kotlinx.android.synthetic.main.activity_main.*

class CustomViewMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_main)

        btnScroller1.setOnClickListener {
            ActivityUtils.start(this, Scroller1Activity::class.java)
        }
        btnDragDrop1.setOnClickListener {
            ActivityUtils.start(this, DragView1Activity::class.java)
        }
        btnPieView.setOnClickListener {
            ActivityUtils.start(this, PieActivity::class.java)
        }
        btnNestedScroll1.setOnClickListener {
            ActivityUtils.start(this, NestedScrollActivity1::class.java)
        }
    }
}
