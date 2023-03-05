package com.seokee.datecourse.view.main.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.seokee.datecourse.R
import com.seokee.datecourse.adapter.RvLocationListAdapter
import com.seokee.datecourse.databinding.FragmentListBinding

class MenuList : Fragment() {

    companion object {
        const val TAG: String = "MenuList"
        fun newInstance(): MenuList {
            return MenuList()
        }
    }

    private lateinit var binding: FragmentListBinding
    private val menuListViewModel: MenuListViewModel by viewModels()

    private lateinit var adapter: RvLocationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        Log.d(TAG, "MenuList")

        adapter = RvLocationListAdapter()
        binding.rvLocationList.adapter = adapter

        menuListViewModel.updateLocationList()


        setObserve()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = menuListViewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }


    private fun setObserve(){
        menuListViewModel.locationList.observe(viewLifecycleOwner, Observer {
            adapter.updateItem(it)
        })
    }
}
