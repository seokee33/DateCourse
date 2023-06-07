package com.seokee.datecourse.util.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilderProvider {
    val web = "http://43.200.4.254:8080/api/"

    fun getRetrofitLoginBuilder() :Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(web)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun getRetrofitBoardBuilder() :Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(web)
            .addConverterFactory(GsonConverterFactory.create())
    }
}
