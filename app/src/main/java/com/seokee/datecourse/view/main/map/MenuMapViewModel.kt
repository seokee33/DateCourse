package com.seokee.datecourse.view.main.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.util.api.RetrofitBuilderProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuMapViewModel() : ViewModel() {
    val locationResponse: MutableLiveData<BoardLocationList> = MutableLiveData()

    var selectedLocation: MutableLiveData<String> = MutableLiveData("대구광역시")

    fun getLocation() {
        RetrofitBuilderProvider.serverLocationAPI.also {
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
