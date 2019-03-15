package com.syxz.hbdroid.activitys.recyclerview.itemdecorations

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

/**
 * 绘制垂直方向的分割线
 */
class MyItemDecoration2 : RecyclerView.ItemDecoration() {


    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }

    private var left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, Resources.getSystem().displayMetrics).toInt()
    private var top = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, Resources.getSystem().displayMetrics).toInt()
    private var right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, Resources.getSystem().displayMetrics).toInt()
    private var bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, Resources.getSystem().displayMetrics).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(left, top, right, bottom)
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        myDraw(parent, c)
    }

    fun myDraw(parent: RecyclerView, c: Canvas) {
        var childCount = parent.childCount
        c.save()
        var left: Int
        var right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            //裁剪画布（默认情况下clipPadding为ture，当RecyclerView布局中设置padding，导致滑动时绘制的View滑出父View视图控件）
            c.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)//可以绘制的rect区域
            for (i in 0 until childCount) {
                var child = parent.getChildAt(i)
                c.drawRect(left.toFloat(), (child.top - top).toFloat(), right.toFloat(), (child.bottom + bottom).toFloat(), paint)
                //考虑margin？一般RecyclerView的item根布局不会添加margin（影响点击事件），margin一般在item内部子View设置margin值，如果需要便宜分割线，绘制时处理
//                var layoutParams = child.layoutParams as RecyclerView.LayoutParams
//                c.drawRect(left.toFloat() + layoutParams.leftMargin, (child.top - top).toFloat(), right.toFloat(), (child.bottom + bottom).toFloat(), backgroundPaint2)
            }
        } else {
            left = 0
            right = parent.width
            for (i in 0 until childCount) {
                var child = parent.getChildAt(i)
                c.drawRect(left.toFloat(), (child.top -parent.paddingTop-top).toFloat(), right.toFloat(), (child.bottom + bottom+parent.paddingBottom).toFloat(), paint)
            }
        }
        c.restore()
    }
}