package com.syxz.hbdroid.activitys.image.bitmap

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_bitmap.*

class BitmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap)

        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.bit)
        imageView4.setImageBitmap(bitmap)

        btnReleaseBitmap.setOnClickListener {
            if (bitmap != null && !bitmap.isRecycled) {
                bitmap.recycle()
                bitmap = null
            }
        }
    }
}
