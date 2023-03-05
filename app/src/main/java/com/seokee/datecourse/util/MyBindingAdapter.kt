package com.seokee.datecourse.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seokee.datecourse.adapter.RvLocationListAdapter
import com.seokee.datecourse.dto.BoardRecommendLocation

object MyBindingAdapter {

    @BindingAdapter("rvMenuListItemsUpdate")
    @JvmStatic
    fun rvMenuListItemsUpdate(recyclerView: RecyclerView, items : ArrayList<BoardRecommendLocation>){

        if(recyclerView.adapter == null){
            val adapter = RvLocationListAdapter()
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter

        }

        val myAdapter = recyclerView.adapter as RvLocationListAdapter

        myAdapter.locationList = items
        myAdapter.notifyDataSetChanged()
    }
}