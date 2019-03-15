package com.syxz.hbdroid.activitys.network.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.JsonObject
import com.syxz.hbdroid.R
import com.syxz.hbdroid.api.ApiHelper
import com.syxz.hbdroid.api.HttpRequestObserver
import com.syxz.hbdroid.api.RetrofitHelper
import com.syxz.hbdroid.data.entity.BookEntity
import com.syxz.hbdroid.data.entity.UserEntity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_rxjava2_retrofit.*
import java.util.concurrent.TimeUnit

class Rxjava2RetrofitActivity : AppCompatActivity() {
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava2_retrofit)
        var vm = BookViewModel(application)

        btnGson.setOnClickListener {
            //gsonSerialize()
            //gsonDeserialize()
            //var data = RetrofitHelper.gson.fromJson("{'id':'user123','name':null,'age':'true'}", UserEntity::class.java)
            //var data = RetrofitHelper.gson.fromJson("{'id':'user123','name':null,'age':'abc123'}", UserEntity::class.java)
            var data = RetrofitHelper.gson.fromJson("{'id':'user123','name':null,'age':'123'}", UserEntity::class.java)
            tvGsonResult.text = "自定义TypeAdapter，反序列化结果：$data"
        }

        btnGetBookDetail.setOnClickListener {
            getBookDetail(vm)
        }

        btnGetUserDetail.setOnClickListener {
            val disposable = ApiHelper.toSubscrible(ApiHelper.getService().userDetail, object : HttpRequestObserver<UserEntity>(this@Rxjava2RetrofitActivity) {
                override fun onGetDataSuccess(t: UserEntity) {
                    tvResult.text = t.toString()
                }

                override fun onGetDataFailed(e: Throwable) {
                    tvResult.text = e.message
                }
            })
            compositeDisposable.add(disposable)
        }

        btnVirture.setOnClickListener {
            mockRxjava()
        }

        btnCancle.setOnClickListener {
            disposeAllRequest()
        }
    }



    fun disposeAllRequest() {
        compositeDisposable?.clear()
    }

    /**
     * 演示HttpResponseData Gson序列化
     * @SerializedName用法
     */
    private fun getBookDetail(vm: BookViewModel) {
        var disposable = vm.getBookDetail(object : HttpRequestObserver<BookEntity>(this@Rxjava2RetrofitActivity) {
            override fun onGetDataSuccess(t: BookEntity) {
                tvResult.text = t.toString()
            }

            override fun onGetDataFailed(e: Throwable) {
                tvResult.text = e.toString()
            }
        })
        compositeDisposable.add(disposable)
    }

    fun mockRxjava() {
        var disposable = Observable.create(ObservableOnSubscribe<Any> {
            it.onNext(JsonObject().apply {
                addProperty("title", "我是title")
                addProperty("desc", "我是描述信心")
                Log.d("NetworkActivity", "onNext 1 执行线程：".plus(Thread.currentThread().name))
            })
            it.onComplete()
        }).delay(1000 * 5, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())//指定dispose动作的执行线程
                .doOnSubscribe {
                    Log.d("NetworkActivity", "doOnSubscribe 执行线程：".plus(Thread.currentThread().name))
                }
                .doOnDispose {
                    Log.d("NetworkActivity", "doOnDispose 执行线程：".plus(Thread.currentThread().name))
                }
                .subscribe(Consumer<Any> {
                    var data = it as JsonObject
                    tvResult.text = data.toString()
                    Log.d("NetworkActivity", "onNext 2执行线程：".plus(Thread.currentThread().name))
                }, Consumer<Throwable> {
                    Log.d("NetworkActivity", "Throwable 执行线程：".plus(Thread.currentThread().name))
                }, Action {
                    Log.d("NetworkActivity", "onComplete 执行线程：".plus(Thread.currentThread().name))
                })
        compositeDisposable.add(disposable)
    }
}
