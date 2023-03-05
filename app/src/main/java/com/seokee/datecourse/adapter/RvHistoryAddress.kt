package com.seokee.datecourse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.seokee.datecourse.dao.HistoryAddressDao
import com.seokee.datecourse.databinding.ItemRvHistoryAddressBinding
import com.seokee.datecourse.model.HistoryAddress
import com.seokee.datecourse.util.roomdb.RoomHelper

class RvHistoryAddress(context: Context) :
    RecyclerView.Adapter<RvHistoryAddress.Holder>() {
    val parentContext = context
    var addressList = ArrayList<HistoryAddress>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRvHistoryAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(addressList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class Holder(var binding: ItemRvHistoryAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryAddress) {
            binding.item = item
            binding.btnRemove.setOnClickListener {
                val helper: RoomHelper = Room.databaseBuilder(parentContext,RoomHelper::class.java,"DateCourseApp")
                    .allowMainThreadQueries() //잘안씀..공부할때만??
                    .build()
                val historyAddressDao: HistoryAddressDao = helper.historyAddressDao()
                historyAddressDao.delete(item)
            }
        }
    }
}