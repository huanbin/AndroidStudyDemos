package com.syxz.hbdroid.api

import com.google.gson.*
import com.syxz.hbdroid.api.adapter.IntegerTypeAdapter
import com.syxz.hbdroid.api.adapter.StringTypeAdapter
import com.syxz.hbdroid.api.interceptors.MockDataInterceptor
import com.syxz.hbdroid.base.BaseApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class RetrofitHelper private constructor() {

    private val baseUrl = "http://192.168.3.27:8080/"
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            //.cookieJar(CookieJar)//自定义CookieJar实现http 持久化Cookie管理
            .cache(Cache(BaseApplication.instance!!.cacheDir, (1024 * 1024 * 10).toLong()))
            //application interceptor与network interceptor区别（主要是对重定向请求，前者intercept执行一次，后者intercept执行2次）
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(MockDataInterceptor())
            .build()

    companion object {

        val instance: RetrofitHelper
            get() = SingleInstanceHolder.INSTANCE

        var gson: Gson = GsonBuilder()
                .setPrettyPrinting()
                .setVersion(1.0)
//                //注册反序列化适配器（JsonDeserializer与JsonSerializer 早起版本，效率低内存开销大）
//                .registerTypeAdapter(Int::class.java, JsonDeserializer<Int> { json, typeOfT, context ->
//                    try {
//                        json.asInt
//                    } catch (e: Exception) {
//                        0
//                    }
//                })
//                //注册序列化适配器(将字符串序列化为整数)
//                .registerTypeAdapter(Int::class.java, JsonSerializer<String> { s: String, type: Type, jsonSerializationContext: JsonSerializationContext ->
//                    try {
//                        JsonPrimitive(s.toInt())
//                    } catch (e: Exception) {
//                        JsonPrimitive(0)
//                    }
//                })
                //推荐TypeAdapter
                .registerTypeAdapter(Int::class.java, IntegerTypeAdapter())
                .registerTypeAdapter(String::class.java, StringTypeAdapter())
                .create()
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    /**
     * 静态内部类实现单例模式
     */
    private object SingleInstanceHolder {
        var INSTANCE = RetrofitHelper()
    }

    fun <T> createService(tClass: Class<T>): T {
        return retrofit.create(tClass)
    }
}
