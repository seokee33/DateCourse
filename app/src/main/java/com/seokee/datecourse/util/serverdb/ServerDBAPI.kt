package com.seokee.datecourse.util.serverdb

import com.seokee.datecourse.dto.BoardRecommendLocation
import com.seokee.datecourse.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerDBAPI {
    @POST("signup")
    fun signupAPI(@Body user: User): Call<User>

    @GET("/v3/cf5878e5-19b6-4221-b374-16cbb8f2bb42")
    fun getLocationList(): Response<BoardRecommendLocation>
}
