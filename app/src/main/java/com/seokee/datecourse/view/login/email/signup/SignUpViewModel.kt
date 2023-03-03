package com.seokee.datecourse.view.login.email.signup

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {

    private val tag = "SignUpViewModel"

    // Firebase
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var iEmail: MutableLiveData<String> = MutableLiveData("")
    var iPasswd: MutableLiveData<String> = MutableLiveData("")
    var iPasswdRe: MutableLiveData<String> = MutableLiveData("")

    var signUpSuccess: MutableLiveData<Boolean> = MutableLiveData(false)

    fun btnSignUp(view: View) {
        Log.w(tag, "btnSignUp")

        val inputEmail: String = iEmail.value.toString()
        val inputPasswd: String = iPasswd.value.toString()
        val inputPasswdRe: String = iPasswdRe.value.toString()

        if (inputEmail.isNotEmpty()) {
            if (inputEmail.contains("@") && inputEmail.contains(".")) {
                if (inputPasswd.isNotEmpty() && inputPasswdRe.isNotEmpty()) {
                    if (inputPasswd == inputPasswdRe) {
                        auth.createUserWithEmailAndPassword(inputEmail, inputPasswd)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    signUpSuccess.value = true
                                } else {
                                    // 중복아이디
                                }
                            }
                            .addOnFailureListener {
                                Log.w(tag, "이메일 로그인 실패 : $it")
                            }
                    } else {
                        Toast.makeText(view.context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(view.context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(view.context, "이메일 형식을 맞춰주세요", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(view.context, "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
    }
}
