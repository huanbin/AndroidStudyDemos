package com.syxz.hbdroid.api.interceptors

import com.google.gson.JsonObject
import com.syxz.hbdroid.BuildConfig
import com.syxz.hbdroid.base.BaseApplication
import okhttp3.*

/**
 * 模拟webserver，响应http请求
 */
class MockDataInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (BuildConfig.DEBUG) {
            var path = request.url().uri().path
            when {
                path.equals("/books/detail/1", true) -> {
                    Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_0)
                            .code(200)
                            .message("hello message")
                            .body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("book.json")))
                            .build()
                }
                path.contains(Regex(pattern = "\\/users\\/\\d+")) -> {
                    Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_0)
                            .code(200)
                            .message("hello message")
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user.json")))
                            //自动类型转换，将字符串"28"转为整数28
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user2.json")))
                            //解析异常NumberFormatException
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user3.json")))
                            //返回空对象，所有属性均取默认值
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user6.json")))
                            .body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user7.json")))
                            //服务端返回数据多一个字段，没有影响
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user4.json")))
                            //服务端返回数据少一个字段（取默认值，int为默认为0）
                            //.body(ResponseBody.create(MediaType.parse("application/json"), getMockJsonData("user5.json")))
                            .build()
                }
                else -> Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_0)
                        .code(200)
                        .message("数据为空")
                        .body(ResponseBody.create(MediaType.parse("application/json"), ""))
                        .build()
            }
        } else {
            chain.proceed(request)
        }
    }

    private fun getMockJsonData(assetFileName: String): String {
        var inputStream = BaseApplication.instance.assets.open(assetFileName)
        val bufferedReader = inputStream.reader().buffered()
        var data = StringBuilder()
        var line: String?
        while (true) {
            line = bufferedReader.readLine()
            if (line == null) {
                break
            }
            data.append(line)
        }
        return data.toString()
    }
}