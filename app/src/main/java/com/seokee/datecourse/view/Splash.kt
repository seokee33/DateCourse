package com.seokee.datecourse.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.seokee.datecourse.R
import com.seokee.datecourse.model.User
import com.seokee.datecourse.util.AppData
import com.seokee.datecourse.view.login.Login
import com.seokee.datecourse.view.main.MainActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slpash)

        Handler(Looper.getMainLooper()).postDelayed({
            val auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            if(currentUser != null){
                AppData.appUserInfo = User(currentUser.uid,currentUser.displayName.toString(),"2022-12-12","MALE",currentUser.email.toString())
                AppData.auth = auth
                Log.i("Splash","${AppData.appUserInfo!!.idToken},${AppData.appUserInfo!!.name},${AppData.appUserInfo!!.gender},")
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                finish()
                startActivity(Intent(this, Login::class.java))
            }
        }, AppData.DURATION)
    }
}