package com.syxz.hbdroid.activitys.apk

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.syxz.hbdroid.R
import com.syxz.hbdroid.utils.ApkUtils
import kotlinx.android.synthetic.main.activity_apk_main.*
import java.io.File

class ApkMainActivity : AppCompatActivity() {

    private lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apk_main)

        path = Environment.getExternalStorageDirectory().absolutePath + File.separator + ApkUtils.getApkFileNameByVersion("4.1.4")
        println("path=$path")

        btnInstallApk.setOnClickListener {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this@ApkMainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ApkUtils.install(this@ApkMainActivity, path)
                } else {
                    goSetUnknowSource()
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this@ApkMainActivity.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
                }
            }
        }
    }

    fun goSetUnknowSource() {
        //开启安装未知来源app（老版本每个app默认是有该权限的）
        var intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
            data = Uri.parse("package:com.syxz.hbdroid")
        }
        startActivityForResult(intent, 300)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300 && resultCode == Activity.RESULT_OK) {
            ApkUtils.install(this@ApkMainActivity, path)
        } else if (requestCode == 201 && resultCode == Activity.RESULT_OK) {
            goSetUnknowSource()
        } else if (requestCode == 400 && resultCode == Activity.RESULT_OK) {
            //允许安装-----》去设置权限
            goSetUnknowSource()
        }
    }
}
