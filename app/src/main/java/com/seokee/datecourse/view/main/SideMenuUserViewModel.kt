package com.seokee.datecourse.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seokee.datecourse.model.User

class SideMenuUserViewModel: ViewModel() {
    lateinit var userInfo: MutableLiveData<User>
}