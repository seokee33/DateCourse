package com.seokee.datecourse.util.api.login

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    fun login(@Body body:RequestBody)
}