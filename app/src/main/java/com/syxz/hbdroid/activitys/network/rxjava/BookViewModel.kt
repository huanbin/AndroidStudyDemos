package com.syxz.hbdroid.activitys.network.rxjava

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.syxz.hbdroid.api.ApiHelper
import com.syxz.hbdroid.api.HttpRequestObserver
import com.syxz.hbdroid.data.entity.BookEntity
import io.reactivex.disposables.Disposable

class BookViewModel(application: Application) : AndroidViewModel(application) {

    fun getBookDetail(observer:HttpRequestObserver<BookEntity>):Disposable{
       return ApiHelper.toSubscrible(ApiHelper.getService().bookDetail, observer)
    }

}


