package com.seokee.datecourse.util.serverdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDBServerBuilder {
    var serverDBAPI: ServerDBAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.88.181:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serverDBAPI = retrofit.create(ServerDBAPI::class.java)
    }
}
