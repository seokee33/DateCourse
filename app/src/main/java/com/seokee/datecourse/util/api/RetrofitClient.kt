package com.seokee.datecourse.util.api

import retrofit2.Retrofit

class RetrofitClient {
    companion object{
        var retrofitLogin: Retrofit? = null
        var retrofitBoard: Retrofit? = null

        fun getLoginService():Login{

        }
    }


}