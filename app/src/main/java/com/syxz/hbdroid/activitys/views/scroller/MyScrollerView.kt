package com.syxz.hbdroid.activitys.views.scroller

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.OverScroller
import android.widget.Scroller

/**
 * 演示Scroller的基本用法
 */
class MyScrollerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    var scroller = Scroller(context)
    //var scroller=OverScroller(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        setMeasuredDimension(200,600)
    }

    override fun onDraw(canvas: Canvas?) {
        setBackgroundColor(Color.YELLOW)
        canvas?.drawCircle(width * 1.0f / 2, height * 1.0f / 2 , 50f, paint)
    }


    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            var x = scroller.currX
            var y = scroller.currY
            scrollTo(x, y)
            invalidate()
        }
    }

    fun startScroll() {
        scroller.forceFinished(true)
        scroller.startScroll(0, 0, 0, 300, 1000)
        invalidate()
    }

}
