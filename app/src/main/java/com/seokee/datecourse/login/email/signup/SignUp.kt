package com.seokee.datecourse.login.email.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.seokee.datecourse.MainActivity
import com.seokee.datecourse.R
import com.seokee.datecourse.dao.User
import com.seokee.datecourse.databinding.ActivitySignUpBinding
import com.seokee.datecourse.util.serverdb.RetrofitDBServerBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {

    val tag = "SignUpActivity"
    lateinit var binding: ActivitySignUpBinding
    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.signUpViewModel = signUpViewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }

    fun btnSignUp() {
//        val user:User = User(
//            signUpViewModel.id.value.toString(),
//            signUpViewModel.passwd.value.toString(),
//            signUpViewModel.userName.value.toString(),
//            signUpViewModel.birthDay.value.toString(),
//            signUpViewModel.gender.value.toString(),
//        )

        val user: User = User(
            signUpViewModel.id.value.toString(),
            signUpViewModel.passwd.value.toString(),
            signUpViewModel.userName.value.toString(),
            "2022-12-12",
            "MALE",
        )

        RetrofitDBServerBuilder.serverDBAPI.signupAPI(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user: User? = response.body()
                    Log.w(tag, "회원가입 성공")
                    finish()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.w(tag, "회원가입 실패")
                Log.w(tag, t.toString())
                Log.w(tag, call.toString())
            }
        })
    }
}
