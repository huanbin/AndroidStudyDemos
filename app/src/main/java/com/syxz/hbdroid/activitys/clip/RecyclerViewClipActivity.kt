package com.syxz.hbdroid.activitys.clip

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.recyclerview.adapter.MyBasicAdapter
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_recylcerview_clip.*

class RecyclerViewClipActivity : BaseActivity() {

    private lateinit var mAdapter: MyBasicAdapter
    private lateinit var mVerticalLayoutManager: LinearLayoutManager
    private lateinit var mVerticalDividerItemDecoration: DividerItemDecoration
    private lateinit var myDataSet: ArrayList<String>

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_recylcerview_clip)
        initRv1()
        initRv2()

        //修改RecylcerView 属性
        btSwitchClip.setOnClickListener {
            rv1.clipToPadding=!rv1.clipToPadding
            rv2.clipToPadding=!rv2.clipToPadding
        }
    }

    private fun initRv1() {
        //myDataSet = Array(6) { i -> (i * i).toString() }
        //myDataSet = List(6) { index -> (index * index).toString() }
        myDataSet = arrayListOf("0", "1", "2", "3","0", "1", "2", "30","09", "14", "26", "30","04", "81", "92", "3")
        mAdapter = MyBasicAdapter(myDataSet)
        mVerticalLayoutManager = LinearLayoutManager(this)
        mVerticalDividerItemDecoration = DividerItemDecoration(this@RecyclerViewClipActivity, LinearLayoutManager.VERTICAL).apply {
            setDrawable(resources.getDrawable(R.drawable.rv_divider_vertical))
        }
        rv1.apply {
            setHasFixedSize(true)
            layoutManager = mVerticalLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mVerticalDividerItemDecoration)
        }
    }

    private fun initRv2() {
        //myDataSet = Array(6) { i -> (i * i).toString() }
        //myDataSet = List(6) { index -> (index * index).toString() }
        myDataSet = arrayListOf("哈哈0", "1", "2", "3","0", "1", "哈哈2", "30","09", "14", "26", "30","04", "81", "92", "3")
        mAdapter = MyBasicAdapter(myDataSet)
        mVerticalLayoutManager = LinearLayoutManager(this)
        mVerticalDividerItemDecoration = DividerItemDecoration(this@RecyclerViewClipActivity, LinearLayoutManager.VERTICAL).apply {
            setDrawable(resources.getDrawable(R.drawable.rv_divider_vertical))
        }
        rv2.apply {
            setHasFixedSize(true)
            layoutManager = mVerticalLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mVerticalDividerItemDecoration)
        }
    }
}