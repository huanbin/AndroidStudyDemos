package com.syxz.hbdroid.activitys.views.draganddrop

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.DragEvent
import android.view.View
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_drag_view.*

class DragView1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_view)

        for (i in 0 until container.childCount) {

            var child = container.getChildAt(i)

            child.setOnLongClickListener {
                ViewCompat.startDragAndDrop(it, null, View.DragShadowBuilder(), child, 0)
            }
            child.setOnDragListener(object : View.OnDragListener {
                override fun onDrag(v: View?, event: DragEvent?): Boolean {
                    if (event?.action == DragEvent.ACTION_DRAG_STARTED) {
//                        if (v == event.localState) {
//                            v?.visibility = View.GONE
//                        }
                    } else if (event?.action == DragEvent.ACTION_DRAG_ENTERED) {
                        sort(v)
                    }
                    return true
                }
            })
        }


    }

    private fun sort(v: View?) {
        if (v == image1) {
            image2.translationY = -image1.top.toFloat()
        }
    }
}
