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
class MyItemDecoration3 : RecyclerView.ItemDecoration() {


    private var paintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }
    private var paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ffaa66cc")
    }


    private var leftDivider = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, Resources.getSystem().displayMetrics).toInt()
    private var bottomDivider = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, Resources.getSystem().displayMetrics).toInt()
    private var lineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, Resources.getSystem().displayMetrics).toInt()
    private var radiusWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, Resources.getSystem().displayMetrics)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(leftDivider, 0, 0, bottomDivider)
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        paintLine.color = Color.BLUE
        myDraw(parent, c)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        paintLine.color = Color.GREEN
        paintLine.strokeWidth = lineWidth.toFloat()
        var childCount = parent.childCount
        c.save()
        var leftPadding: Int
        var right: Int
        if (parent.clipToPadding) {
            leftPadding = parent.paddingLeft
            right = parent.width - parent.paddingRight
            //裁剪画布（默认情况下clipPadding为ture，当RecyclerView布局中设置padding，导致滑动时绘制的View滑出父View视图控件）
            c.clipRect(leftPadding, parent.paddingTop, right, parent.height - parent.paddingBottom)//可以绘制的rect区域
            println("leftDivider=$leftDivider")
            for (i in 0 until childCount) {
                var child = parent.getChildAt(i)
//                c.drawLine(left + leftDivider / 2.0f, child.top.toFloat(), left + leftDivider / 2.0f, (child.bottom + bottomDivider).toFloat(), paintLine)
//                c.drawCircle(left + leftDivider / 2.0f, child.top + child.dividerHeight2 / 2.0f, radiusWidth, paintCircle)
                c.drawLine((child.left - leftPadding) / 2.0f + leftPadding, child.top.toFloat(), (child.left - leftPadding) / 2.0f + leftPadding, (child.bottom + bottomDivider).toFloat(), paintLine)
                c.drawCircle((child.left - leftPadding) / 2.0f + leftPadding, child.top + child.height / 2.0f, radiusWidth, paintCircle)
            }
        }
        c.restore()
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
                c.drawRect(left.toFloat(), (child.top).toFloat(), right.toFloat(), (child.bottom + bottomDivider).toFloat(), paintLine)
                //考虑margin？一般RecyclerView的item根布局不会添加margin（影响点击事件），margin一般在item内部子View设置margin值，如果需要便宜分割线，绘制时处理
//                var layoutParams = child.layoutParams as RecyclerView.LayoutParams
//                c.drawRect(left.toFloat() + layoutParams.leftMargin, (child.top - top).toFloat(), right.toFloat(), (child.bottom + bottom).toFloat(), paintLine)
//                parent.getDecoratedBoundsWithMargins()
            }
        } else {
            left = 0
            right = parent.width
            for (i in 0 until childCount) {
                var child = parent.getChildAt(i)
                c.drawRect(left.toFloat(), (child.top - parent.paddingTop).toFloat(), right.toFloat(), (child.bottom + bottomDivider + parent.paddingBottom).toFloat(), paintLine)
            }
        }
        c.restore()
    }
}