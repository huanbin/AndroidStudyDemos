package com.syxz.hbdroid.activitys.viewpager.splash

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.webkit.DateSorter
import android.widget.ImageView
import android.widget.Toast
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.MainActivity
import com.syxz.hbdroid.base.BaseActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    var datas = arrayListOf(R.drawable.splash_drawable1, R.drawable.splash_drawable2, R.drawable.splash_drawable3)
    private lateinit var splashAdapter: SplashViewPagerAdapter
    override fun initViews(intent: Intent) {
        setContentView(R.layout.activity_splash)

        var id = intent.data?.getQueryParameter("id") ?: 0
        Toast.makeText(this@SplashActivity, "参数id=$id", Toast.LENGTH_LONG).show()

        initViewPager()
        initListener()
        btnJump.visibility = View.VISIBLE
        btnStartUse.visibility = View.GONE
    }

    private fun initListener() {
        btnJump.setOnClickListener {
            ActivityUtils.clearTop2SpecialActivity(MainActivity::class.java)
        }

        btnStartUse.setOnClickListener {
            ActivityUtils.clearTop2SpecialActivity(MainActivity::class.java)
        }
    }

    private fun initViewPager() {
        var listener = object : SplashViewPagerAdapter.SplashCallback {
            override fun doClicked(type: SplashViewPagerAdapter.ClickButtonType) {
                Toast.makeText(this@SplashActivity, "点击了 item:$type", Toast.LENGTH_SHORT).show()
            }
        }
        splashAdapter = object : SplashViewPagerAdapter(datas) {
            override fun getView(position: Int): View {
                var view = ImageView(this@SplashActivity)
                view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                view.setImageResource(this.dataList[position])
                view.setOnClickListener {
                    listener?.doClicked(ClickButtonType.BTN_JUMP)
                }
                return view
            }
        }
        splashViewPager.apply {
            adapter = splashAdapter
            currentItem = 0
//            setPageTransformer(false) { view, fValue ->
//                view.alpha=fValue
//            }
            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(pos: Int) {
                    if (pos == splashAdapter.count - 1) {
                        btnJump.visibility = View.GONE
                        btnStartUse.visibility = View.VISIBLE
                    } else {
                        btnJump.visibility = View.VISIBLE
                        btnStartUse.visibility = View.GONE
                    }
                }
            })
        }
    }
}