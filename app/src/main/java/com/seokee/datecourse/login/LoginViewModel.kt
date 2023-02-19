package com.seokee.datecourse.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var clickLoginKakao: MutableLiveData<Boolean> = MutableLiveData(false)
    var clickLoginGoogle: MutableLiveData<Boolean> = MutableLiveData(false)
    var clickLoginEmail: MutableLiveData<Boolean> = MutableLiveData(false)
}
