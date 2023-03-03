package com.seokee.datecourse.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("idToken")
    var idToken: String,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("birthDay")
    var birthDay: String,

    @Expose
    @SerializedName("gender")
    var gender: String,

    @Expose
    @SerializedName("email")
    var email: String,
)
