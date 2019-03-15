package com.syxz.hbdroid.activitys.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.recyclerview.adapter.MyBasicAdapter
import kotlinx.android.synthetic.main.activity_recylcerview_main.*

class BasicRecyclerView1Activity : AppCompatActivity() {
    private lateinit var mAdapter: MyBasicAdapter
    private lateinit var mVerticalLayoutManager: LinearLayoutManager
    private lateinit var mHorizontalLayoutManager: LinearLayoutManager
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mHorizontalDividerItemDecoration: DividerItemDecoration
    private lateinit var mVerticalDividerItemDecoration: DividerItemDecoration
    //private lateinit var myDataSet: Array<String>
    private lateinit var myDataSet: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recylcerview_main)
        initRv1()
        initBt()
    }

    private fun initBt() {
        btnAdd.setOnClickListener {
            var datas = mAdapter.getDatas()
            datas.add(datas.size.toString())
            mAdapter.notifyItemInserted(datas.size - 1)
        }

        btnHorizontal.setOnClickListener {
            initRv2()
        }

        btnVertical.setOnClickListener {
            initRv1()
        }

        btnGrid.setOnClickListener {
            initRv3()
        }
    }

    fun initRv1() {
        //myDataSet = Array(6) { i -> (i * i).toString() }
        //myDataSet = List(6) { index -> (index * index).toString() }
        myDataSet = arrayListOf("0", "1", "2", "3")
        mAdapter = MyBasicAdapter(myDataSet)
        mVerticalLayoutManager = LinearLayoutManager(this)
        mVerticalDividerItemDecoration = DividerItemDecoration(this@BasicRecyclerView1Activity, LinearLayoutManager.VERTICAL).apply {
            setDrawable(resources.getDrawable(R.drawable.rv_divider_vertical))
        }
        clearItemDecorations()
        rv.apply {
            setHasFixedSize(true)
            layoutManager = mVerticalLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mVerticalDividerItemDecoration)
        }
    }

    fun initRv2() {
        //myDataSet = Array(6) { i -> (i * i).toString() }
        //myDataSet = List(6) { index -> (index * index).toString() }
        myDataSet = arrayListOf("0", "1", "2", "3")
        mAdapter = MyBasicAdapter(myDataSet)
        mHorizontalLayoutManager = LinearLayoutManager(this@BasicRecyclerView1Activity, LinearLayoutManager.HORIZONTAL, false)
        mHorizontalDividerItemDecoration = DividerItemDecoration(this@BasicRecyclerView1Activity, LinearLayoutManager.HORIZONTAL).apply {
            setDrawable(resources.getDrawable(R.drawable.rv_divider_horizontal))
        }
        clearItemDecorations()
        rv.apply {
            setHasFixedSize(true)
            layoutManager = mHorizontalLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mHorizontalDividerItemDecoration)
        }
    }

    fun initRv3() {
        //myDataSet = Array(6) { i -> (i * i).toString() }
        //myDataSet = List(6) { index -> (index * index).toString() }
        myDataSet = arrayListOf("grid01", "grid11", "grid12", "grid13")
        mAdapter = MyBasicAdapter(myDataSet)
        mGridLayoutManager = GridLayoutManager(this@BasicRecyclerView1Activity, 3)
        mHorizontalDividerItemDecoration = DividerItemDecoration(this@BasicRecyclerView1Activity, GridLayoutManager.VERTICAL).apply {
            setDrawable(resources.getDrawable(R.drawable.rv_divider_horizontal))
        }
        clearItemDecorations()
        rv.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mHorizontalDividerItemDecoration)
        }
    }

    fun clearItemDecorations() {
        var count = rv.itemDecorationCount
        for (i in 0 until count) {
            rv.removeItemDecorationAt(i)
        }
    }
}
