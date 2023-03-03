package com.seokee.datecourse.view.main.info

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.FragmentInfoBinding
import com.seokee.datecourse.util.MenuInfoFragmentState

class MenuInfo(): Fragment() {
    companion object {
        const val TAG: String = "MenuInfo"
        fun newInstance(): MenuInfo {
            return MenuInfo()
        }

    }

    private lateinit var menuInfoMain: MenuInfoMain
    private lateinit var menuInfoUserSetting: MenuInfoUserSetting


    private lateinit var binding : FragmentInfoBinding
    private val menuInfoViewModel: MenuInfoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info,container,false)
        Log.d(TAG,"MenuInfo")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuInfoViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

        changeFragment(MenuInfoFragmentState.MenuInfoMain)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
    }

    private val onBackPressedCallback =  object: OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            if(menuInfoViewModel.fragmentState.value == MenuInfoFragmentState.MenuInfoMain){
                requireActivity().finish()
            }else{

            }
        }
    }

    fun changeFragment(index: MenuInfoFragmentState){
        when(index){
            MenuInfoFragmentState.MenuInfoMain -> {
                menuInfoMain = MenuInfoMain.newInstance()
                childFragmentManager.beginTransaction().replace(binding.fragmentMenuInfo.id,menuInfoMain).commit()
            }
            MenuInfoFragmentState.UserSetting -> {
                menuInfoUserSetting = MenuInfoUserSetting.newInstance()
                childFragmentManager.beginTransaction().replace(binding.fragmentMenuInfo.id,menuInfoUserSetting).commit()
            }
            else -> return
        }
    }




}