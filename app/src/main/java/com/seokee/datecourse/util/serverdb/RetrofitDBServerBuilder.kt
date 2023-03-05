package com.seokee.datecourse.util.serverdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitDBServerBuilder {
    var serverDBAPI: ServerDBAPI

    var serverLocationAPI: ServerDBAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.88.181:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serverDBAPI = retrofit.create(ServerDBAPI::class.java)

        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serverLocationAPI = retrofit2.create(ServerDBAPI::class.java)
    }
}
