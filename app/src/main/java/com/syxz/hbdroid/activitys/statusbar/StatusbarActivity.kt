package com.syxz.hbdroid.activitys.statusbar

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_status_window.*

class StatusbarActivity : BaseActivity() {

    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_status_window)
        initListner()
    }

    private fun fullscreen() {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor=Color.TRANSPARENT
        }
    }

    private fun setStatusbarColor() {
        //修改状态栏颜色
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.RED
        }
        //修改状态栏文字颜色
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun initListner() {
        btnSwitchFullScreen.setOnClickListener {
            fullscreen()
        }

        btnStatusbarColor.setOnClickListener {
            setStatusbarColor()
        }
    }
}