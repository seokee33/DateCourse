package com.seokee.datecourse.util.serverdb

import com.seokee.datecourse.dao.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerDBAPI {
    @POST("signup")
    fun signupAPI(@Body user: User): Call<User>
}
