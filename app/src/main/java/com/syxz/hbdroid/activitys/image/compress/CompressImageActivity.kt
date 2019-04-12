package com.syxz.hbdroid.activitys.image.compress

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Environment
import com.syxz.hbdroid.R
import com.syxz.hbdroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_compress_luban.*
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.io.FileInputStream


class CompressImageActivity : BaseActivity() {

    val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath.plus("/syxz").plus("/IMG_20180908_143408.jpg")
    var targetDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath.plus("/syxz/download")
    override fun initViews(intent: Intent) {

        setContentView(R.layout.activity_compress_luban)
        var bitmap1 = BitmapFactory.decodeStream(FileInputStream(path))
        imgLeft.setImageBitmap(bitmap1)
        var size=bitmap1.byteCount.div(1024)
        println("图片大小：$size")

        btnCompress.setOnClickListener {

            if (!File(targetDir).exists()) {
                File(targetDir).mkdirs()
            }

            Luban.with(this@CompressImageActivity).load(path)
                    .setTargetDir(targetDir)
                    .ignoreBy(1024)
                    .setFocusAlpha(true)
                    .setCompressListener(object : OnCompressListener {
                        override fun onSuccess(file: File?) {
                            println("compress onSuccess ，file=${file?.path}")
                            var bitmap2 = BitmapFactory.decodeStream(FileInputStream(file))
                            var size=bitmap2.byteCount.div(1024)
                            println("图片大小：$size")
                            imgRight.setImageBitmap(bitmap2)
                        }

                        override fun onError(e: Throwable?) {
                        }

                        override fun onStart() {

                        }
                    })
                    .setRenameListener { "hello.jpg" }//图片重命名
                    .launch()
        }
    }

}