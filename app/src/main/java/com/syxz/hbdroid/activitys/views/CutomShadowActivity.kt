package com.syxz.hbdroid.activitys.views

import android.graphics.Outline
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import android.view.ViewOutlineProvider
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_cutom_shadow.*

class CutomShadowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cutom_shadow)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnChangeShadow.clipToOutline = true
            btnChangeShadow.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view?.width!!, view?.height!!, 2f)
                }
            }
        }
    }
}
