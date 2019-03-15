package com.syxz.hbdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.Since
import com.google.gson.annotations.Until

/**
 * @Expose ---->指定那些成员允许序列化和反序列化
 * 下面2个注解一般在web-service中使用,配合GsonBuilder.setVersion(1.0)(setVersion指定参与（参与序列化和返序列化）的版本号)
 * @Since------>指定该字段的版本号，<=setVersion版本号，则参与序列化和反序列化
 * @Since(0.9)----->yes
 * @Since(1.0)----->yes
 * @Since(1.1)----->no
 * @Until----->指定该字段的版本号，>=setVersion版本号，则参与序列化和反序列化
 * @Until(1.0)------>yes
 * @Until(1.1)------>yes
 * @Until(1.2)------>no
 */
data class UserEntity(@Expose(serialize = true, deserialize = true) var id: String,
                      @Since(1.2) var name: String,
                      @Until(0.9) var age: Int)
