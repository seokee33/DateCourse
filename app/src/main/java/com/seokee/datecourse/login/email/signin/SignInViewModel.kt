package com.seokee.datecourse.login.email.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var email: MutableLiveData<String> = MutableLiveData("")
    var passwd: MutableLiveData<String> = MutableLiveData("")

    var btnEmailLogin: MutableLiveData<Boolean> = MutableLiveData(false)
}
