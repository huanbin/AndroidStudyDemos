package com.syxz.hbdroid.activitys.views.draganddrop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_drag_view.*

class DragViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_view)
        image1.setOnLongClickListener {
            image1.startDrag(null, View.DragShadowBuilder(), null, 0)
        }
    }
}
