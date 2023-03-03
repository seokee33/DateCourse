package com.seokee.datecourse.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.seokee.datecourse.model.RecommendLocation

data class BoardRecommendLocation(
    @Expose
    @SerializedName("boardName")
    var boardName: String,

    @Expose
    @SerializedName("locationList")
    var locationList: ArrayList<RecommendLocation>,
)