package com.seokee.datecourse.view.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.seokee.datecourse.BuildConfig
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivityLoginBinding
import com.seokee.datecourse.view.login.email.signin.SignIn
import com.seokee.datecourse.view.main.MainActivity

class Login : AppCompatActivity() {

    val tag = "LoginActivity"
    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    //Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this

        // Initialize Firebase Auth
        auth = Firebase.auth

        requestPermissions()

        setObserve()
    }

    /**
     * 권환체크
     *  - Location
     * */
    private fun requestPermissions(): Boolean {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }

        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty()){
                    var isAllGranted = true
                    // 요청한 권한들 수락했는지 확인
                    for (grant in grantResults) {
                        if (grant != PackageManager.PERMISSION_GRANTED) {
                            isAllGranted = false
                            break;
                        }
                    }

                    // 허용하지 않은 권한이 있음. 필수권한/선택권한 여부에 따라서 별도 처리를 해주어야 함.
                    if (!isAllGranted) {
                        if(!ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                            || !ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.ACCESS_COARSE_LOCATION)){
                            // 다시 묻지 않기 체크하면서 권한 거부 되었음.
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                .setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID))
                            startActivity(intent)
                        } else {
                            Toast.makeText(this,"권한을 설정해주세요",Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                }
            }
        }
    }

    companion object{
        const val REQUEST_CODE = 100
        val permissions: Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    /**
     * ViewModel안에 데이터의 값이 변경되었을때
     *  - kakaotalk Login 버튼 클릭시
     *  - Google Login 버튼 클릭시
     * */
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
