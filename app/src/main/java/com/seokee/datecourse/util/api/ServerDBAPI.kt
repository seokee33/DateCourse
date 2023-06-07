package com.seokee.datecourse.util.api

import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerDBAPI {
    @POST("signup")
    fun signupAPI(@Body user: User): Call<User>

    @GET("/v3/59f0b497-a791-416a-a351-818513ab0e3c")
    fun getLocationList2(): Response<BoardLocationList>

    @GET("/v3/59f0b497-a791-416a-a351-818513ab0e3c")
    fun getLocationList(): Call<BoardLocationList>



}
