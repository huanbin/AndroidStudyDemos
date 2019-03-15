package com.syxz.hbdroid.activitys

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.syxz.hbdroid.R

import com.syxz.hbdroid.service.BadgeIntentService
import kotlinx.android.synthetic.main.activity_badger.*

import me.leolin.shortcutbadger.ShortcutBadger

class BadgeActivity : AppCompatActivity() {

    private var badgeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badger)

        val numInput = findViewById<EditText>(R.id.numInput)

        val button = findViewById<Button>(R.id.btnSetBadge)
        button.setOnClickListener {
            try {
                badgeCount = Integer.parseInt(numInput.text.toString())
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Error input", Toast.LENGTH_SHORT).show()
            }

            val success = ShortcutBadger.applyCount(this@BadgeActivity, badgeCount)
            Toast.makeText(applicationContext, "Set count=$badgeCount, success=$success", Toast.LENGTH_SHORT).show()
        }

        val launchNotification = findViewById<Button>(R.id.btnSetBadgeByNotification)
        launchNotification.setOnClickListener {
            var badgeCount = 0
            try {
                badgeCount = Integer.parseInt(numInput.text.toString())
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Error input", Toast.LENGTH_SHORT).show()
            }

            finish()
            startService(
                    Intent(this@BadgeActivity, BadgeIntentService::class.java).putExtra("badgeCount", badgeCount)
            )
        }

        val removeBadgeBtn = findViewById<Button>(R.id.btnRemoveBadge)
        removeBadgeBtn.setOnClickListener {
            val success = ShortcutBadger.removeCount(this@BadgeActivity)

            Toast.makeText(applicationContext, "success=$success", Toast.LENGTH_SHORT).show()
        }


        //find the home launcher Package
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        var currentHomePackage = "none"
        val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        // in case of duplicate apps (Xiaomi), calling resolveActivity from one will return null
        if (resolveInfo != null) {
            currentHomePackage = resolveInfo.activityInfo.packageName
        }

        val textViewHomePackage = findViewById<TextView>(R.id.textViewHomePackage)
        textViewHomePackage.text = "launcher:$currentHomePackage"

        btnVivoBadge.setOnClickListener {
            val intent = Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM")
            intent.putExtra("packageName", packageName)
            intent.putExtra("className", packageName.plus(MainActivity::class.java.simpleName))
            intent.putExtra("notificationNum", badgeCount)
            sendBroadcast(intent)
        }
    }


}