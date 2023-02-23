package com.seokee.datecourse.view.login.email.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.seokee.datecourse.view.main.MainActivity
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivitySignInBinding
import com.seokee.datecourse.view.login.email.signup.SignUp

class SignIn : AppCompatActivity() {
    val tag = "SignInActivity"

    lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()


    // Firebae
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.viewModel = signInViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        // Firebase
        auth = FirebaseAuth.getInstance()

        setObserve()
    }

    private fun setObserve(){
        signInViewModel.signInSuccess.observe(this){
            if(it){
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    fun emailSignUp() {
        finish()
        startActivity(Intent(this, SignUp::class.java))
    }
}
