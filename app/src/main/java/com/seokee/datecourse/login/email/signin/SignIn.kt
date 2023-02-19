package com.seokee.datecourse.login.email.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.seokee.datecourse.MainActivity
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivitySignInBinding
import com.seokee.datecourse.login.email.signup.SignUp

class SignIn : AppCompatActivity() {
    val tag = "SignInActivity"

    lateinit var binding: ActivitySignInBinding
    lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        binding.viewModel = signInViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        setObserve()
    }

    fun setObserve() {
        signInViewModel.btnEmailLogin.observe(this) {
            if (it) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    fun emailLogin() {
        signInViewModel.btnEmailLogin.value = true
    }

    fun emailSignUp() {
        finish()
        startActivity(Intent(this, SignUp::class.java))
    }
}
