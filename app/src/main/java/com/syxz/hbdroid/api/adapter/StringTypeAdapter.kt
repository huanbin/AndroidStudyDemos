package com.syxz.hbdroid.api.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * String类型适配器
 * 如果返回值为null，反序列化为空字符串""
 */
class  StringTypeAdapter : TypeAdapter<String>() {
    override fun write(out: JsonWriter?, value: String?) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): String {
        if (`in`?.peek()==JsonToken.NULL) {
            `in`.skipValue()
            return ""
        }
        return `in`?.nextString()!!
    }

}