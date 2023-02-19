package com.seokee.datecourse.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("userEmail")
    var userEmail: String,

    @Expose
    @SerializedName("password")
    var password: String,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("birthDay")
    var birthDay: String,

    @Expose
    @SerializedName("gender")
    var gender: String,
)
