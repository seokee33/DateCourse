package com.seokee.datecourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seokee.datecourse.databinding.ItemRvLocationListBinding
import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.dto.BoardRecommendLocation

class RvLocationListAdapter() :
    RecyclerView.Adapter<RvLocationListAdapter.Holder>() {


    var locationList = mutableListOf<BoardRecommendLocation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRvLocationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount() = locationList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(locationList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    fun updateItem(updateList: ArrayList<BoardRecommendLocation>){
        locationList = updateList
        notifyDataSetChanged()
    }


    inner class Holder(var binding: ItemRvLocationListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardRecommendLocation) {
            binding.item = item
            var strPlaces: String = ""
            for(location in item.locationList){
                strPlaces.plus(location.name+" -> ")
            }
        }
    }
}
