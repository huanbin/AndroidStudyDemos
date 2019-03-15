package com.syxz.hbdroid.activitys.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.net.wifi.rtt.WifiRttManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.syxz.hbdroid.R
import kotlinx.android.synthetic.main.activity_new_type_monitor_network.*

class NewTypeMonitorNetworkActivity : AppCompatActivity() {

    private lateinit var networkRequest: NetworkRequest
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_type_monitor_network)

        var networkManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkRequest = NetworkRequest.Builder().build()
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
                    Log.d("NetworkActivity", "NewTypeMonitorNetworkActivity onAvailable")
                }

                override fun onLosing(network: Network?, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    Log.d("NetworkActivity", "NewTypeMonitorNetworkActivity onLosing")
                }

                override fun onLost(network: Network?) {
                    super.onLost(network)
                    Log.d("NetworkActivity", "NewTypeMonitorNetworkActivity onLost")
                }
            }
        }
        btnMonitorSingleNetworkRequest.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                networkManager.requestNetwork(networkRequest, object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network?) {
                        super.onAvailable(network)
                        Log.d("NetworkActivity", "NewTypeMonitorNetworkActivity onAvailable")
                    }
                })
            }
        }

        btnStartMonitor.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                networkManager.registerNetworkCallback(networkRequest, networkCallback)
            }
        }

        btnStopMonitor.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                networkManager.unregisterNetworkCallback(networkCallback)
            }
        }

        btnScanWifi.setOnClickListener {
            scanWifi()
        }
    }

    /**
     * 8.0 和9.0版本均有操作次数限制和权限管理
     * 有华为手机无法使用该功能
     */
    fun scanWifi() {
        var wifiManager: WifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        applicationContext.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                var listResult = wifiManager.scanResults
                listResult.forEach {
                    Log.d("NetworkActivity", "SSID ${it.SSID}")
                }
            }
        }, intentFilter)
        wifiManager.startScan()
    }
}
