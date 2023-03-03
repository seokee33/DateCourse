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
import com.seokee.datecourse.databinding.AddlocationCountBinding
import com.seokee.datecourse.view.main.list.MenuList

class AddLocationCount : Fragment() {
    companion object {
        const val TAG: String = "AddLocationCount"
        var addLocationCount: AddLocationCount? = null
        fun newInstance(): AddLocationCount {
            if (addLocationCount == null) {
                addLocationCount = AddLocationCount()
            }
            return addLocationCount as AddLocationCount
        }
    }

    private lateinit var binding: AddlocationCountBinding
    private val viewModel: AddLocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.addlocation_count, container, false)
        Log.d(MenuList.TAG, "AddLocationCount")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }
}
