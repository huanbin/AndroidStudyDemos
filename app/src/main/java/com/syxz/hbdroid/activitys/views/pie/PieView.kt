package com.syxz.hbdroid.activitys.views.pie

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class PieView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var datas = arrayListOf<PieEntity>()
    private var percents = arrayListOf(2, 3, 1, 3, 1)
    private var labels = arrayListOf("第一季度", "第二季度", "第三季度", "第四季度", "第五季度")
    private var colors = arrayListOf(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN)
    private var rectWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, Resources.getSystem().displayMetrics)
    //圆环宽度
    private var circleRingWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, Resources.getSystem().displayMetrics)
    //白色圆半径
    private var circleBigRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, Resources.getSystem().displayMetrics)
    private var circleSmallRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, Resources.getSystem().displayMetrics)
    private var circleSmallStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics)
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var rectF: RectF

    //圆弧边上小圆距离
    private var distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, Resources.getSystem().displayMetrics)

    private fun initData() {
        var startAngle = -90
        for (i in 0 until 5) {
            var sweetdAngle = percents[i].div(10.0f).times(360).toInt()
            var p = PieEntity(labels[i], colors[i], startAngle, startAngle + sweetdAngle, sweetdAngle, percents[i])
            datas.add(p)
            startAngle += sweetdAngle
        }
    }

    init {
        initData()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        datas.forEachIndexed { index, it ->
            paint.color = colors[index]
            //绘制圆弧
            canvas?.drawArc(rectF, it.startAngle.toFloat(), it.sweetAngle.toFloat(), true, paint)
            //绘制圆
            paint.color = Color.WHITE
            canvas?.drawCircle(width / 2.0f, height / 2.0f, Math.max(width, height) / 2.0f - circleRingWidth, paint)
            //绘制圆点
            val halfAngle = it.startAngle.plus(it.sweetAngle / 2.0f).toDouble()
            var cos = Math.cos(Math.toRadians(halfAngle))
            var sin = Math.sin(Math.toRadians(halfAngle))
            var x = (Math.max(width, height) / 2.0f.plus(6)).times(cos)
            var y = (Math.max(width, height) / 2.0f.plus(6)).times(sin)
            paint.color = colors[index]
            canvas?.drawCircle(x.toFloat(), y.toFloat(), circleSmallRadius, paint)
            //绘制圆环
//            paint.style = Paint.Style.STROKE
//            paint.strokeWidth = circleSmallStrokeWidth
//            canvas?.drawCircle(x.toFloat(), y.toFloat(), circleBigRadius, paint)
        }
    }
}
