package com.seokee.datecourse.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecommendLocation(
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("address")
    var address: String,

    @Expose
    @SerializedName("latitude")
    var latitude: String,

    @Expose
    @SerializedName("longitude")
    var longitude: String,

    @Expose
    @SerializedName("category")
    var category: String,

    @Expose
    @SerializedName("description")
    var description: String,

)
