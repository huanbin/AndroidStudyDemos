package com.syxz.hbdroid.activitys.image

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.image.bitmap.BitmapActivity
import com.syxz.hbdroid.activitys.image.compress.CompressImageActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_image_main.*


class ImageMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_main)
        btnBitmapMemory.setOnClickListener {
            ActivityUtils.start(this, BitmapActivity::class.java)
        }
        btnLuban.setOnClickListener {
            ActivityUtils.start(this, CompressImageActivity::class.java)
        }
    }
}
