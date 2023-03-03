package com.seokee.datecourse.view.main.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.FragmentListBinding

class MenuList: Fragment() {

    companion object {
        const val TAG: String = "MenuList"
        fun newInstance(): MenuList {
            return MenuList()
        }

    }


    private lateinit var binding : FragmentListBinding
    private val menuListViewModel: MenuListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list,container,false)
        Log.d(TAG,"MenuList")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuListViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

    }

}