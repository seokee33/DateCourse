package com.seokee.datecourse.view.main.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seokee.datecourse.dto.BoardRecommendLocation
import com.seokee.datecourse.util.serverdb.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MenuMapViewModel(private val repository: Repository) : ViewModel() {
    val locationResponse: MutableLiveData<Response<BoardRecommendLocation>> = MutableLiveData()

    var selectedLocation: MutableLiveData<String> = MutableLiveData("대구광역시")

    fun getLocation() {
        viewModelScope.launch {
            val response = repository.getLocation()
            locationResponse.value = response
        }
    }
}
