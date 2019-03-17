package com.syxz.hbdroid.activitys.viewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_page_transform.*

class PageTransformActivity : AppCompatActivity() {

    private var colors =
            listOf(android.R.color.holo_red_dark, android.R.color.holo_purple, android.R.color.holo_orange_dark)
    private var alphaMin = 0.6f
    private var scale1Min = 0.65f
    private var scale2Min = 0.75f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_transform)
        viewPager.apply {
            adapter = object : PagerAdapter() {
                override fun isViewFromObject(p0: View, p1: Any): Boolean {
                    return p0 == p1
                }

                override fun getCount(): Int {
                    return 3
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    return LinearLayout(context).apply {
                        ImageView(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            (layoutParams as LinearLayout.LayoutParams).apply {
                                leftMargin = 60
                                rightMargin = 60
                                topMargin = 80
                                bottomMargin = 80
                            }
                            setBackgroundColor(ContextCompat.getColor(this@PageTransformActivity, colors[position]))
                            addView(this, layoutParams)
                        }
                        container.addView(this)
                    }
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View?)
                }
            }

            setPageTransformer(false) { p0, p1 ->
                println("view is $p0,position is $p1")
                when {
                    p1 < -1 -> {
                        p0.alpha = alphaMin
                        p0.scaleX = scale1Min
                        p0.scaleY = scale2Min
                    }
                    p1 < 0 -> {
                        p0.alpha = alphaMin + (1 - alphaMin) * (1 + p1)
                        p0.scaleX = scale1Min + (1 - scale1Min) * (1 + p1)
                        p0.scaleY = scale2Min + (1 - scale2Min) * (1 + p1)
                    }
                    p1 < 1 -> {
                        p0.alpha = alphaMin + (1 - alphaMin) * (1 - p1)
                        p0.scaleX = scale1Min + (1 - scale1Min) * (1 - p1)
                        p0.scaleY = scale2Min + (1 - scale2Min) * (1 - p1)
                    }
                    else -> {
                        p0.alpha = alphaMin
                        p0.scaleX = scale1Min
                        p0.scaleY = scale2Min
                    }
                }
            }

            pageMargin = 20
        }
    }

}
