package com.syxz.hbdroid.activitys.views.nestedscroll

import android.content.Intent
import android.support.v4.view.*
import android.widget.ScrollView
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_nested_scroll1.*

class NestedScrollActivity1 : BaseActivity() {
    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_nested_scroll1)

        btnAllowNestedScroll.setOnClickListener {
            scrollView2.isNestedScrollingEnabled = true
        }

        btnDisallowNestedScroll.setOnClickListener {
            //该属性只对嵌套中的滑动视图生效
            //不允许第二个NestedScrollView（嵌套）滚动，即滚动手势全部由外层的滚动视图NestedScrollView处理
            scrollView2.isNestedScrollingEnabled = false
        }

//        NestedScrollingChild
//        NestedScrollingChildHelper
//        NestedScrollingParent2
//        NestedScrollingParentHelper
    }

}
