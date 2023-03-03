package com.seokee.datecourse.view.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.FragmentSearchBinding

class MenuSearch: Fragment() {
    companion object {
        const val TAG: String = "MenuSearch"
        fun newInstance(): MenuSearch {
            return MenuSearch()
        }

    }


    private lateinit var binding : FragmentSearchBinding
    private val menuSearchViewModel: MenuSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)
        Log.d(TAG,"MenuSearch")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuSearchViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

    }
}