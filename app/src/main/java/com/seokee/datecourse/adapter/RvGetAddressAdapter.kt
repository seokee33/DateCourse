package com.seokee.datecourse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seokee.datecourse.databinding.RvGetlocationItemBinding
import com.seokee.datecourse.model.LocationAddress
import com.seokee.datecourse.view.main.addlocation.GetLocationInfoDialog

class RvGetAddressAdapter (context: Context, private var itemList: ArrayList<LocationAddress>, private var dialog: GetLocationInfoDialog) : RecyclerView.Adapter<RvGetAddressAdapter.Holder>() {

    var context = context
    lateinit var result:String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvGetAddressAdapter.Holder {
        val binding =
            RvGetlocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: RvGetAddressAdapter.Holder, position: Int) {
        holder.bind(position)

    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    fun changeItem(item:ArrayList<LocationAddress>){
        itemList = item
    }

    fun getResultValue():String{
        return result
    }

    inner class Holder(var binding: RvGetlocationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.tvAddressValue.text = itemList[position].address.address_name
            binding.root.setOnClickListener {
                result = binding.tvAddressValue.text.toString()+","+itemList[position].address.x+","+itemList[position].address.y
                dialog.setAddressText(result)
            }
        }
    }


}