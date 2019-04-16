package com.syxz.hbdroid.activitys.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_vector_drawable.*

/**
 * drawable动画、view动画（显示与隐藏动画、布局更新动画、布局过渡动画、view（enlarge）增大动画、平移、缩放、旋转）
 * activity启动动画
 */
class VectorDrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector_drawable)
        vector()
        reveal()
        move()
        transitionDrawable()
    }

    private fun transitionDrawable() {
        var a = false
        var transition=ivTransitionDrawable.drawable as TransitionDrawable
        btTransitionDrawable.setOnClickListener {
            a = !a
            if (a) {
                transition.startTransition(1000)
            } else {
                transition.reverseTransition(1000)
            }
        }
    }

    /**
     * 移动视图动画
     */
    private fun move() {
        btMove1.setOnClickListener {
            ObjectAnimator.ofFloat(tvTestAniamtion, "translationX", 100f).apply {
                duration = 1000
                start()
            }
        }

        btMove2.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val path = Path().apply {
                    lineTo(50f, 100f)
                    lineTo(400f, -600f)
                }
                ObjectAnimator.ofFloat(tvTestAniamtion, "translationX", "translationY", path).apply {
                    start()
                }
            }
        }
    }

    /**
     * 显示一个View的动画
     *
     */
    private fun reveal() {
        btShowMessage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx = tvReveal.width / 2
                val cy = tvReveal.height / 2
                val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
                val animator = ViewAnimationUtils.createCircularReveal(tvReveal, cx, cy, 0f, finalRadius)
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        tvReveal.visibility = View.VISIBLE
                    }
                })
                animator.duration = 1000
                animator.startDelay = 100
                animator.start()
            }
        }
    }

    /**
     * AnimatedVectorDrawable
     * 带动画的Vector drawable
     */
    fun vector() {
        var vectorDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageButton.drawable as AnimatedVectorDrawable
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        imageButton.setOnClickListener {
            vectorDrawable.start()
            //vectorDrawable.reverse()
        }

        imageView2.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((it as ImageView).drawable as AnimatedVectorDrawable).start()
            }
        }
    }
}
