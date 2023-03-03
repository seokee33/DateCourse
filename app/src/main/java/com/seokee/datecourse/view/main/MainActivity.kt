package com.seokee.datecourse.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ActivityMainBinding
import com.seokee.datecourse.view.main.choice.MenuChoice
import com.seokee.datecourse.view.main.info.MenuInfo
import com.seokee.datecourse.view.main.list.MenuList
import com.seokee.datecourse.view.main.map.MenuMap
import com.seokee.datecourse.view.main.search.MenuSearch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var menuMap: MenuMap
    private lateinit var menuList: MenuList
    private lateinit var menuSearch: MenuSearch
    private lateinit var menuChoice: MenuChoice
    private lateinit var menuInfo: MenuInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        initNavigationBar()
        menuMap = MenuMap.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuMap).commit()
    }

    private fun initNavigationBar() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_Map -> {
                        menuMap = MenuMap.newInstance()
                        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuMap).commit()
                    }
                    R.id.menu_List -> {
                        menuList = MenuList.newInstance()
                        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuList).commit()
                    }
                    R.id.menu_Search -> {
                        menuSearch = MenuSearch.newInstance()
                        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuSearch).commit()
                    }
                    R.id.menu_Choice -> {
                        menuChoice = MenuChoice.newInstance()
                        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuChoice).commit()
                    }
                    R.id.menu_Info -> {
                        menuInfo = MenuInfo.newInstance()
                        supportFragmentManager.beginTransaction().replace(binding.fragMain.id, menuInfo).commit()
                    }
                }
                true
            }
        }
    }
}
