package com.seokee.datecourse.view.login.email.signin

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel : ViewModel() {
    private val tag = "SignInViewModel"
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email: MutableLiveData<String> = MutableLiveData("")
    var passwd: MutableLiveData<String> = MutableLiveData("")

    var signInSuccess: MutableLiveData<Boolean> = MutableLiveData(false)

    fun emailLogin(view: View) {
        val inputEmail: String = email.value.toString()
        val inputPasswd: String = passwd.value.toString()

        if (inputEmail.isNotEmpty() && inputPasswd.isNotEmpty()) {
            auth.signInWithEmailAndPassword(inputEmail, inputPasswd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("tag", "signInWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        Log.w(tag, "signInWithEmail:failure", task.exception)
                        Toast.makeText(view.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(view.context, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}
