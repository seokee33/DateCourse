package com.seokee.datecourse.view.login

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.seokee.datecourse.R

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var clickLoginKakao: MutableLiveData<Boolean> = MutableLiveData(false)

    @SuppressLint("StaticFieldLeak")
    val context = getApplication<Application>().applicationContext

    //Firebase
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    //Google
    private var googleSignInClient: GoogleSignInClient
    var googleLoginSuccess: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context,gso)

    }

    //Google Login
    fun loginGoogle(view: View){
        var i = googleSignInClient.signInIntent
        (view.context as? Login)?.googleLoginResult?.launch(i)
    }
    fun firebaseAuthWithGoogle(idToken: String?){
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if(it.isSuccessful){
                   googleLoginSuccess.value = true
                }else{
                    //아이디가 있을경우우
               }
            }
    }
}
