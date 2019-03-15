package com.syxz.hbdroid.activitys.backgroundtask

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.syxz.hbdroid.R
import com.syxz.hbdroid.service.MyJobService
import kotlinx.android.synthetic.main.activity_job_schedule.*

class JobScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_schedule)
        btnScheduleJob.setOnClickListener {
            var jobInfo = JobInfo.Builder(1, ComponentName(this, MyJobService::class.java))
                    .setMinimumLatency(3000)
                    .setExtras(PersistableBundle().apply {
                        putString("key", "hello")
                    })
                    .setBackoffCriteria(3000,JobInfo.BACKOFF_POLICY_LINEAR)//相当于任务失败之后重新调度任务（时间间隔机制）
                    //.setRequiresBatteryNotLow(false)
                    // setBackoffCriteria与setRequiresDeviceIdle不可同时设置
                    //.setRequiresDeviceIdle(true)//An idle mode job will not respect any back-off policy,
                    //.setEstimatedNetworkBytes()设置上传下载的流量大小限制
                    .build()
            var jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }
    }
}

