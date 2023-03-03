package com.seokee.datecourse.view.main.info

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seokee.datecourse.util.AppData
import com.seokee.datecourse.util.MenuInfoFragmentState

class MenuInfoViewModel(): ViewModel() {
    var userName: MutableLiveData<String> = MutableLiveData("UserName")
    var userEmail: MutableLiveData<String> = MutableLiveData("UserEmail")

    var fragmentState: MutableLiveData<MenuInfoFragmentState> = MutableLiveData(MenuInfoFragmentState.MenuInfoMain)

    init {
        userName.value = AppData.appUserInfo?.name
        userName.value = AppData.appUserInfo?.email
    }

    fun onclickUserInfoChange(v: View){

    }


}