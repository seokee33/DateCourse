package com.seokee.datecourse.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivityLoginBinding
import com.seokee.datecourse.login.email.signin.SignIn

class Login : AppCompatActivity() {

    val tag = "LoginActivity"
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        setObserve()
    }

    fun setObserve() {
        loginViewModel.clickLoginKakao.observe(this) {
            if (it) {
                loginViewModel.clickLoginKakao.value = false
            }
        }

        loginViewModel.clickLoginGoogle.observe(this) {
            if (it) {
                loginViewModel.clickLoginGoogle.value = false
            }
        }

        loginViewModel.clickLoginEmail.observe(this) {
            if (it) {
                finish()
                startActivity(Intent(this, SignIn::class.java))
            }
        }
    }
    // Kakao Login

    fun loginKakao() {
        Log.w(tag, "KAKAO_Login")
        loginViewModel.clickLoginKakao.value = true
    }

    // Google Login

    fun loginGoogle() {
        Log.w(tag, "Google_Login")
        loginViewModel.clickLoginGoogle.value = true
    }

    // Email Login

    fun loginEmail() {
        Log.w(tag, "Email_Login")
        loginViewModel.clickLoginEmail.value = true
    }
}
