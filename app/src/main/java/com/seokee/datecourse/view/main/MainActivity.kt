package com.seokee.datecourse.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naver.maps.map.MapFragment
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivityMainBinding
import com.seokee.datecourse.util.GetHashKey

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    //binding and ViewModel
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    //KakaoMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.activity = this
        binding.lifecycleOwner = this


        // 해쉬키 얻기 : KakaoMap API HashKey
        GetHashKey.getHashKey(this)


//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map_View) as MapFragment?
//            ?: MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.map_View, it).commit()
//            }

    }

    /**
     * MapView 초기화
     * */


}
