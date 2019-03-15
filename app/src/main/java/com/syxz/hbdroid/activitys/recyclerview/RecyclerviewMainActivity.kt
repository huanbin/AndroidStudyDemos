package com.syxz.hbdroid.activitys.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.syxz.hbdroid.R
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_recylcerview_list.*
import kotlinx.android.synthetic.main.activity_recylcerview_main.*

class RecyclerviewMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recylcerview_list)

        btRv1.setOnClickListener {
            ActivityUtils.start(this,BasicRecyclerView1Activity::class.java)
        }

        btRv2.setOnClickListener {
            ActivityUtils.start(this,ItemDecorationRvActivity::class.java)
        }
    }
}
