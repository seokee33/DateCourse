package com.seokee.datecourse.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.seokee.datecourse.view.main.MainActivity
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivityLoginBinding
import com.seokee.datecourse.view.login.email.signin.SignIn

class Login : AppCompatActivity() {

    val tag = "LoginActivity"
    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        setObserve()
    }

    private fun setObserve() {
        loginViewModel.clickLoginKakao.observe(this) {
            if (it) {
                loginViewModel.clickLoginKakao.value = false
            }
        }

        loginViewModel.googleLoginSuccess.observe(this){
            if(it){
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
    // Kakao Login

    fun loginKakao() {
        Log.w(tag, "KAKAO_Login")
        loginViewModel.clickLoginKakao.value = true
    }

    // Google Login
    var googleLoginResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->

        val data = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        account.idToken
        loginViewModel.firebaseAuthWithGoogle(account.idToken)
    }

    // Email Login
    fun loginEmail() {
        Log.w(tag, "Email_Login")
        finish()
        startActivity(Intent(this, SignIn::class.java))
    }
}
