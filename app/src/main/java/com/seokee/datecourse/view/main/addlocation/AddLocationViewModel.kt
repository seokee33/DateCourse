package com.seokee.datecourse.view.main.addlocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seokee.datecourse.dto.BoardRecommendLocation
import com.seokee.datecourse.model.RecommendLocation
import com.seokee.datecourse.util.AddLocationFragmentState

class AddLocationViewModel: ViewModel(){
    var nowFragmentState: MutableLiveData<AddLocationFragmentState> = MutableLiveData(AddLocationFragmentState.Count)

    var item: BoardRecommendLocation = BoardRecommendLocation("",
        arrayListOf(RecommendLocation("","","","","",""))
    )

    var boardName: MutableLiveData<String> = MutableLiveData("")

    var nowPage: MutableLiveData<Int> = MutableLiveData(1)
    var pageTab: MutableLiveData<String> = MutableLiveData("$nowPage / 3")

    var locationCount: MutableLiveData<Int> = MutableLiveData(1)
    var locationCountText: MutableLiveData<String> = MutableLiveData("$locationCount")


}