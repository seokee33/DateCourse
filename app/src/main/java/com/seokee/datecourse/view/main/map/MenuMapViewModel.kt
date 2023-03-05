package com.seokee.datecourse.view.main.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.dto.BoardRecommendLocation
import com.seokee.datecourse.util.serverdb.Repository
import com.seokee.datecourse.util.serverdb.RetrofitDBServerBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuMapViewModel() : ViewModel() {
    val locationResponse: MutableLiveData<BoardLocationList> = MutableLiveData()

    var selectedLocation: MutableLiveData<String> = MutableLiveData("대구광역시")

    fun getLocation() {
        RetrofitDBServerBuilder.serverLocationAPI.also {
            it.getLocationList()
                .enqueue(object: Callback<BoardLocationList>{
                    override fun onResponse(
                        call: Call<BoardLocationList>,
                        response: Response<BoardLocationList>
                    ) {
                        if(!response.isSuccessful){
                            // 실패 처리
                            return
                        }
                        response.body()?.let { dto ->
                            locationResponse.value = dto
                        }
                    }

                    override fun onFailure(call: Call<BoardLocationList>, t: Throwable) {

                    }

                })

        }
    }


}
