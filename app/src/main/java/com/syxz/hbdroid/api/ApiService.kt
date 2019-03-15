package com.syxz.hbdroid.api

import com.syxz.hbdroid.data.entity.BookEntity
import com.syxz.hbdroid.data.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @get:GET("books/detail/1")
    val bookDetail: Observable<HttpResponseData<BookEntity>>

    @get:GET("users/11")
    val userDetail: Observable<HttpResponseData<UserEntity>>
}
