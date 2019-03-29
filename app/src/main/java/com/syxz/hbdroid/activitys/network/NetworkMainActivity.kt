package com.syxz.hbdroid.activitys.network

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syxz.hbdroid.R
import com.syxz.hbdroid.activitys.network.gson.GsonDemoActivity
import com.syxz.hbdroid.activitys.network.rxjava.RxBusMemoryLeakActivity
import com.syxz.hbdroid.activitys.network.rxjava.Rxjava2RetrofitActivity
import com.syxz.hbdroid.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_network_main.*

class NetworkMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_main)

        btnRxjavaRetrofit.setOnClickListener {
            ActivityUtils.start(this, Rxjava2RetrofitActivity::class.java)
        }
        btnMonitorNetworkState.setOnClickListener {
            ActivityUtils.start(this, NewTypeMonitorNetworkActivity::class.java)
        }
        btnGsonOpt.setOnClickListener {
            ActivityUtils.start(this, GsonDemoActivity::class.java)
        }

        btnRxleak.setOnClickListener {
            ActivityUtils.start(this, RxBusMemoryLeakActivity::class.java)
        }


    }
}
