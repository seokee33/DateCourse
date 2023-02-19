package com.seokee.datecourse.login.email.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var id: MutableLiveData<String> = MutableLiveData("")
    var passwd: MutableLiveData<String> = MutableLiveData("")
    var passwdRe: MutableLiveData<String> = MutableLiveData("")

    var userName: MutableLiveData<String> = MutableLiveData("")
    var birthDay: MutableLiveData<String> = MutableLiveData("")
    var gender: MutableLiveData<String> = MutableLiveData("")
}
