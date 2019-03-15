package com.syxz.hbdroid.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService : JobService() {

    private val TAG = MyJobService::class.java!!.simpleName

    override fun onStartJob(params: JobParameters): Boolean {
        Log.d(TAG, "onStartJob::"+ params.jobId+" in " + Thread.currentThread().name)
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Log.d(TAG, "onStopJob::"+ params.jobId+" in " + Thread.currentThread().name)
        return false
    }

}
