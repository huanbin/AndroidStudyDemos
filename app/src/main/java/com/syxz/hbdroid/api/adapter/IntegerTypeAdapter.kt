package com.syxz.hbdroid.api.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * Int 类型适配器
 * 如果值为null,反序列化值为-1
 * 如果不是null,则以字符串读取并转为Int类型，出错则返回0
 *
 */
class IntegerTypeAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int?) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        if (`in`?.peek() == JsonToken.NULL) {
            //`in`.skipValue()
            return -1
        }
        return try {
            `in`?.nextString()?.toInt()!!
        } catch (e: Exception) {
            0
        }
    }

}
