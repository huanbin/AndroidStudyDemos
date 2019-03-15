package com.syxz.hbdroid.api

import com.google.gson.annotations.SerializedName

/**
 * 通用的http响应模型
 */
data class HttpResponseData<T>(@SerializedName("errcode") var code: Int,
                               @SerializedName("errmsg") var errorMsg: String,
                               @SerializedName("data") var data: T)