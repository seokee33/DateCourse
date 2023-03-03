package com.seokee.datecourse.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seokee.datecourse.R
import com.seokee.datecourse.databinding.ViewpagerItemAddLocationBinding
import com.seokee.datecourse.model.RecommendLocation
import com.seokee.datecourse.view.main.addlocation.AddLocationViewModel
import com.seokee.datecourse.view.main.addlocation.GetLocationInfoDialog

class AddLocationViewPagerAdapter(val context: Context, val viewModel: AddLocationViewModel) :
    ListAdapter<RecommendLocation, AddLocationViewPagerAdapter.ItemViewHolder>(differ) {

    inner class ItemViewHolder(val binding: ViewpagerItemAddLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addLocationModel: RecommendLocation) {
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AddLocationViewPagerAdapter.ItemViewHolder {
        val binding = ViewpagerItemAddLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AddLocationViewPagerAdapter.ItemViewHolder,
        position: Int,
    ) {
        val nowPosition = position
        val first = viewModel.item.locationList.size == 1
        // 장소 이름
        holder.binding.etLocationName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.item.locationList[nowPosition].name =
                    holder.binding.etLocationName.text.toString()
            }
        })

        // 주소
        holder.binding.tvAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        holder.binding.btnAddressSearch.setOnClickListener {
            val dialog = GetLocationInfoDialog(context)
            dialog.showDialog()
            dialog.setOnClickListener(object : GetLocationInfoDialog.ButtonClickListener {
                override fun onClicked(text: String) {
                    var addressValue: String = text
                    var strvalue = addressValue.split(",")

                    viewModel.item.locationList[nowPosition].address = strvalue[0]
                    viewModel.item.locationList[nowPosition].latitude = strvalue[1]
                    viewModel.item.locationList[nowPosition].longitude = strvalue[2]

                    holder.binding.tvAddress.text = strvalue[0]
                }
            })
        }

        // 분위기
        ArrayAdapter.createFromResource(
            context,
            R.array.category_array,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.binding.spCategory.adapter = adapter
        }

        holder.binding.spCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    if (parent != null) {
                        viewModel.item.locationList[nowPosition].category =
                            parent.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        holder.binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.item.locationList[nowPosition].description =
                    holder.binding.etDescription.text.toString()
            }
        })

        // 추가 버튼
        holder.binding.btnAddLocationAdd.setOnClickListener {
            addLocation()
            notifyItemInserted(holder.adapterPosition + 1)
            holder.binding.btnThisLocationDelete.visibility = View.VISIBLE
            holder.binding.btnAddLocationAdd.visibility = View.GONE
        }

        // 빼기 버튼
        holder.binding.btnThisLocationDelete.setOnClickListener {
            removeLocation(nowPosition)
            notifyItemRemoved(holder.adapterPosition)
            holder.binding.btnThisLocationDelete.visibility = View.GONE
            holder.binding.btnAddLocationAdd.visibility = View.VISIBLE
        }
    }
    private fun addLocation() {
        var temp: RecommendLocation = RecommendLocation("", "", "", "", "", "")
        viewModel.item.locationList.add(temp)
    }

    private fun removeLocation(position: Int) {
        viewModel.item.locationList.removeAt(position)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<RecommendLocation>() {
            override fun areItemsTheSame(
                oldItem: RecommendLocation,
                newItem: RecommendLocation,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RecommendLocation,
                newItem: RecommendLocation,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
