package com.seokee.datecourse.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BoardLocationList(
    @Expose
    @SerializedName("items")
    var items: ArrayList<BoardRecommendLocation>,

)
