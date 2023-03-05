package com.seokee.datecourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seokee.datecourse.databinding.ItemBoardlocatoinViewpagerBinding
import com.seokee.datecourse.dto.BoardLocationList
import com.seokee.datecourse.dto.BoardRecommendLocation

class LocationListViewPagerAdapter :
    ListAdapter<BoardRecommendLocation, LocationListViewPagerAdapter.ItemViewHolder>(differ) {

    inner class ItemViewHolder(val binding: ItemBoardlocatoinViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardRecommendLocation) {
            binding.tvName.text = item.boardName
            var strPlace = ""
            for(place in item.locationList){
                strPlace+= place.name+" -> "
            }
            binding.tvLocationList.text = strPlace
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LocationListViewPagerAdapter.ItemViewHolder {
        val binding = ItemBoardlocatoinViewpagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LocationListViewPagerAdapter.ItemViewHolder,
        position: Int,
    ) {
        holder.bind(currentList[position])

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<BoardRecommendLocation>() {
            override fun areItemsTheSame(
                oldItem: BoardRecommendLocation,
                newItem: BoardRecommendLocation,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BoardRecommendLocation,
                newItem: BoardRecommendLocation,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
