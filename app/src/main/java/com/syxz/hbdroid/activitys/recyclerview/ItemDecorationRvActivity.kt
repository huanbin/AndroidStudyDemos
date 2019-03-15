package com.syxz.hbdroid.activitys.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.recyclerview.adapter.MyBasicAdapter
import com.syxz.hbdroid.activitys.recyclerview.itemdecorations.MyItemDecoration2
import com.syxz.hbdroid.activitys.recyclerview.itemdecorations.MyItemDecoration3
import com.syxz.hbdroid.activitys.recyclerview.itemdecorations.StickyItemDecoration
import kotlinx.android.synthetic.main.activity_item_decoration_rv.*

class ItemDecorationRvActivity : AppCompatActivity() {

    private lateinit var datas: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_decoration_rv)
        datas = arrayListOf("ahdsh1", "大沙地2", "j打回家就好3", "阿萨德科技离开家4", "奥术大师5", "阿打算6", "解决健康7", "阿打算888", "阿达999", "撒大声地100", "奥术大师5", "阿打算6", "解决健康7", "奥术大师5", "阿打算6", "解决健康7", "奥术大师5", "阿打算6", "解决健康7")
        rv.adapter = MyBasicAdapter(datas)
        rv.layoutManager = LinearLayoutManager(this)
//        rv.addItemDecoration(MyItemDecoration2())
//        rv.addItemDecoration(MyItemDecoration3())
        rv.addItemDecoration(StickyItemDecoration())
    }
}
