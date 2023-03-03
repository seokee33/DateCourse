package com.seokee.datecourse.view.main.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.MenuInfoMainBinding
import com.seokee.datecourse.util.MenuInfoFragmentState

class MenuInfoMain : Fragment() {
    companion object {
        const val TAG: String = "MenuInfoMain"
        fun newInstance(): MenuInfoMain {
            return MenuInfoMain()
        }
    }

    private lateinit var binding: MenuInfoMainBinding
    private val menuInfoViewModel: MenuInfoViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_info_main, container, false)
        Log.d(MenuInfo.TAG, "MenuInfoMain")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuInfoViewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }

    fun clickUserInfoChange() {
        (parentFragment as MenuInfo).changeFragment(MenuInfoFragmentState.UserSetting)
    }
}
