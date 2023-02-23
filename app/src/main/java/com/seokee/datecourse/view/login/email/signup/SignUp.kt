package com.seokee.datecourse.view.login.email.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.seokee.datecourse.view.main.MainActivity
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    val tag = "SignUpActivity"
    lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    //Firebase
    lateinit var auth: FirebaseAuth

    //Email


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.signUpViewModel = signUpViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        //Firebase
        auth = FirebaseAuth.getInstance()

        setObserve()
    }

    private fun setObserve(){
        signUpViewModel.signUpSuccess.observe(this){
            if(it){
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }
}
