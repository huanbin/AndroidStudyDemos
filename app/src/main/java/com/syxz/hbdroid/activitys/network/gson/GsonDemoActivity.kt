package com.syxz.hbdroid.activitys.network.gson

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.syxz.hbdroid.R
import com.syxz.hbdroid.api.RetrofitHelper
import com.syxz.hbdroid.data.entity.UserEntity
import kotlinx.android.synthetic.main.activity_gson_demo.*

class GsonDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_demo)
        initListener()
    }

    private fun initListener() {
        /**
         * 注意UserEntity中name字段的注解，不参与序列化和反序列化
         */
        btnSerialize.setOnClickListener {
            var user = UserEntity("user123", "tom", 100)
            var data = RetrofitHelper.gson.toJson(user, UserEntity::class.java)
            println("gson序列化结果是：$data")
        }

        btnDeserialize.setOnClickListener {
            //结果为：id=user123,name=user_tom，age=0
            var data = RetrofitHelper.gson.fromJson("{'id':'user123','name':'user_tom','age':'age123'}", UserEntity::class.java)
            println("gson反序列化结果是：$data")
        }

        btnEscape.setOnClickListener {
            val gson = RetrofitHelper.gson.toJson("{\"name\":\"tom\",\"address\":\"www.152@163.com\"}")
            println("gson=$gson")
        }
    }
}
