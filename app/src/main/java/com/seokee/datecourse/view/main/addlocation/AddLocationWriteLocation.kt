package com.seokee.datecourse.view.main.addlocation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.AddlocationWriteLocationBinding
import com.seokee.datecourse.databinding.FragmentListBinding
import com.seokee.datecourse.view.main.list.MenuList
import com.seokee.datecourse.view.main.list.MenuListViewModel

class AddLocationWriteLocation: Fragment() {
    companion object {
        const val TAG: String = "AddLocationWriteLocation"
        var addLocationWriteLocation:AddLocationWriteLocation? = null
        fun newInstance(): AddLocationWriteLocation {
            if(addLocationWriteLocation == null){
                addLocationWriteLocation = AddLocationWriteLocation()
            }
            return addLocationWriteLocation as AddLocationWriteLocation
        }
    }


    private lateinit var binding : AddlocationWriteLocationBinding
    private val viewModel: AddLocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.addlocation_write_location,container,false)
        Log.d(MenuList.TAG,"AddLocationWriteLocation")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this

    }

}