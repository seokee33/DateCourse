package com.seokee.datecourse.view.main.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.dto.BoardRecommendLocation
import com.seokee.datecourse.util.api.RetrofitBuilderProvider
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuListViewModel : ViewModel() {

    private val _locationList = MutableLiveData<ArrayList<BoardRecommendLocation>>()
    val locationList : LiveData<ArrayList<BoardRecommendLocation>>
        get() = _locationList


    private val locationListService = RetrofitBuilderProvider.serverLocationAPI
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    val locationLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    private var items = ArrayList<BoardRecommendLocation>()
    init {
        updateLocationList()
    }

    fun refresh(){
        fetLocationList()
    }

    private fun fetLocationList() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = locationListService.getLocationList()
            withContext(Dispatchers.Main) {
                response.enqueue(object : Callback<BoardLocationList>{
                    override fun onResponse(
                        call: Call<BoardLocationList>,
                        response: Response<BoardLocationList>
                    ) {
                        if (!response.isSuccessful) {
                            // 실패 처리
                            return
                        }
                        response.body()?.let { dto ->
                            items = dto.items
                            _locationList.value = items
                            locationLoadError.value = ""
                            loading.value = false
                        }

                        Log.i("MenuListViewModel", "Update")
                    }

                    override fun onFailure(call: Call<BoardLocationList>, t: Throwable) {
                        onError("Error : ${}")
                    }

                })
            }
        }
    }

    private fun onError(message: String) {
        locationLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        job?.cancel()
    }

    fun updateLocationList() {
        RetrofitBuilderProvider.serverLocationAPI.also {
            it.getLocationList()
                .enqueue(object : Callback<BoardLocationList> {
                    override fun onResponse(
                        call: Call<BoardLocationList>,
                        response: Response<BoardLocationList>
                    ) {
                        if (!response.isSuccessful) {
                            // 실패 처리
                            return
                        }
                        response.body()?.let { dto ->
                            items = dto.items
                            _locationList.value = items
                        }

                        Log.i("MenuListViewModel", "Update")
                    }

                    override fun onFailure(call: Call<BoardLocationList>, t: Throwable) {

                    }

                })
        }

    }

}
