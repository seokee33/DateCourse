package com.seokee.datecourse.view.main.addlocation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.AddlocationFinishBinding
import com.seokee.datecourse.databinding.AddlocationWriteLocationBinding
import com.seokee.datecourse.view.main.list.MenuList

class AddLocationFinish: Fragment() {
    companion object {
        const val TAG: String = "AddLocationFinish"
        var addLocationFinish:AddLocationFinish? = null
        fun newInstance(): AddLocationFinish {
            if(addLocationFinish == null){
                addLocationFinish = AddLocationFinish()
            }
            return addLocationFinish as AddLocationFinish
        }
    }


    private lateinit var binding : AddlocationFinishBinding
    private val viewModel: AddLocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.addlocation_finish,container,false)
        Log.d(MenuList.TAG,"AddLocationFinish")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this

    }

}