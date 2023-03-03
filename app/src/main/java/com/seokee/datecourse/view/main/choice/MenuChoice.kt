package com.seokee.datecourse.view.main.choice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.FragmentChoiceBinding

class MenuChoice: Fragment() {
    companion object {
        const val TAG: String = "MenuChoice"
        fun newInstance(): MenuChoice {
            return MenuChoice()
        }

    }


    private lateinit var binding : FragmentChoiceBinding
    private val menuChoiceViewModel: MenuChoiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choice,container,false)
        Log.d(TAG,"MenuSub")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuChoiceViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

    }
}