package com.seokee.datecourse.util.api

import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor {
    companion object{
        fun getLogger() : HttpLoggingInterceptor{
            var loggingInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return loggingInterceptor
        }
    }
}