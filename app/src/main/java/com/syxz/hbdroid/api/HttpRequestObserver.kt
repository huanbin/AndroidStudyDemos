package com.syxz.hbdroid.api

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context

abstract class HttpRequestObserver<T>(context: Context) {
    var dialog: Dialog = ProgressDialog(context)

    fun onStart() {
        dialog.show()
    }

    fun onDispose() {
        //dialog.hide()
        dialog.dismiss()
    }

    abstract fun onGetDataSuccess(t: T)

    fun onNext(t: T) {
        onGetDataSuccess(t)
    }

    abstract fun onGetDataFailed(e: Throwable)

    fun onError(e: Throwable) {
        //统一处理异常
        onGetDataFailed(e)
        //dialog.hide()
        dialog.dismiss()
    }


    fun onComplete() {
        //dialog.hide()
        dialog.dismiss()
    }
}