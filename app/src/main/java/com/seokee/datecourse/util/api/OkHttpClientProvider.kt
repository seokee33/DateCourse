package com.seokee.datecourse.util.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpClientProvider {
    companion object{
        fun getDefaultOkHttpClient():OkHttpClient{
            val loggingInterceptor: HttpLoggingInterceptor = LoggingInterceptor.getLogger()

            var client :OkHttpClient = OkHttpClient()
            client.newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()

            return client
        }
    }
}