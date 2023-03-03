package com.seokee.datecourse.view.main.info

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.MenuInfoUserSettingBinding
import com.seokee.datecourse.util.AppData
import com.seokee.datecourse.view.login.Login

class MenuInfoUserSetting : Fragment() {
    companion object {
        const val TAG: String = "MenuInfoUserSetting"
        fun newInstance(): MenuInfoUserSetting {
            return MenuInfoUserSetting()
        }

    }

    private lateinit var binding: MenuInfoUserSettingBinding
    private val menuInfoViewModel: MenuInfoViewModel by viewModels({ requireParentFragment() })

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.menu_info_user_setting, container, false)
        Log.d(MenuInfo.TAG, "MenuInfoMain")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuInfoViewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }

    fun onClickLogout() {
        AppData.auth?.signOut()
        startActivity(Intent((parentFragment as MenuInfo).activity, Login::class.java))
        (parentFragment as MenuInfo).activity?.finish()
    }

}