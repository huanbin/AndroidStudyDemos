package com.syxz.hbdroid.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ApiHelper {
    companion object {
        fun getService(): ApiService {
            return RetrofitHelper.instance.createService(ApiService::class.java)
        }

        fun <T> toSubscrible(observable: Observable<HttpResponseData<T>>, observer: HttpRequestObserver<T>): Disposable {
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        observer.onStart()
                    }
                    .doOnDispose {
                        observer.onDispose()
                    }
                    .subscribe(Consumer {
                        observer.onNext(it.data)
                    }, Consumer {
                        observer.onError(it)
                    }, Action {
                        observer.onComplete()
                    })
        }
    }
}