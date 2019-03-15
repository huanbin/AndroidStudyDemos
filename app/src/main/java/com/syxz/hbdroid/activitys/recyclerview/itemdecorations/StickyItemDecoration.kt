package com.syxz.hbdroid.activitys.recyclerview.itemdecorations

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.TypedValue
import android.view.View

/**
 * 实现分组
 * 吸顶效果
 */
class StickyItemDecoration : RecyclerView.ItemDecoration() {

    private var backgroundPaint1 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ffaa00cc")
    }

    private var backgroundPaint2 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ffaa66cc")
    }

    var textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ff000000")
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, Resources.getSystem().displayMetrics)
    }


    var dividerHeight1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics).toInt()
    var dividerHeight2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, Resources.getSystem().displayMetrics).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        var pos = parent.getChildAdapterPosition(view)
        if (pos % 4 == 0) {
            outRect.set(0, dividerHeight2, 0, 0)
        } else {
            outRect.set(0, dividerHeight1, 0, 0)
        }
    }

    /**
     * 吸顶效果关键代码
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var childCount = parent.childCount
        for (i in 0 until childCount) {
            var child = parent.getChildAt(i)
            var isGroupFirst = parent.getChildAdapterPosition(child) % 4 == 0
            if (isGroupFirst) {
                c.drawRect(0f, (child.top - dividerHeight2).toFloat(), child.right.toFloat(), child.top.toFloat(), backgroundPaint2)
                var group = parent.getChildAdapterPosition(child) / 4
                var text = "我是分组：$group"
                var bounds = Rect()
                textPaint.getTextBounds(text, 0, text.length, bounds)
                var widthSpace = child.right - child.left
                //绘制文本注意x和y坐标是基准线的位置
                c.drawText(text, child.left + (widthSpace - bounds.width()) / 2.0f, child.top - (dividerHeight2 - bounds.height()) / 2.0f, textPaint)
            } else {
                c.drawRect(0f, (child.top - dividerHeight1).toFloat(), child.right.toFloat(), child.top.toFloat(), backgroundPaint1)
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.left + parent.paddingLeft
        val right = parent.right - parent.paddingRight
        var preGroupName: String?      //标记上一个item对应的Group
        var currentGroupName: String? = null       //当前item对应的Group
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupName = currentGroupName
            currentGroupName = getGroupName(position)
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName))
                continue
            val viewBottom = view.bottom
            var top = Math.max(dividerHeight2, view.top)//top 决定当前顶部第一个悬浮Group的位置
            if (position + 1 < itemCount) {
                //获取下个GroupName
                val nextGroupName = getGroupName(position + 1)
                //下一组的第一个View接近头部
                if (currentGroupName != nextGroupName && viewBottom < top) {
                    top = viewBottom.toFloat().toInt()//让当前吸顶的group跟随自己分组最后一个item向上移动
                }
            }
            //根据top绘制group
            c.drawRect(left.toFloat(), (top - dividerHeight2).toFloat(), right.toFloat(), top.toFloat(), backgroundPaint2)
            val fm = textPaint.fontMetrics
            //文字竖直居中显示
            val baseLine = top - (dividerHeight2 - (fm.bottom - fm.top)) / 2 - fm.bottom
            c.drawText(currentGroupName, (left + 20).toFloat(), baseLine, textPaint)
        }
    }

    private fun getGroupName(position: Int): String? {
        return "我是分组：".plus(position / 4)
    }

}
