package com.seokee.datecourse.util.serverdb

import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.dto.BoardRecommendLocation
import retrofit2.Call
import retrofit2.Response

class Repository {
    suspend fun getLocation(): Call<BoardLocationList> {
        return RetrofitDBServerBuilder.serverLocationAPI.getLocationList()
    }
}
