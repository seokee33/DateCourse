package com.seokee.datecourse.view.main.addlocation

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.seokee.datecourse.R
import com.seokee.datecourse.adapter.RvGetAddressAdapter
import com.seokee.datecourse.model.LocationAddress
import com.seokee.datecourse.model.MapAddressApi
import com.seokee.datecourse.util.RESPONSE_STATE
import com.seokee.datecourse.util.address.RetrofitManagerAddress

class GetLocationInfoDialog(context: Context) {
    val dialog = Dialog(context)

    lateinit var adapter: RvGetAddressAdapter
    lateinit var itemList: ArrayList<LocationAddress>
    lateinit var tvSelectedAddress: TextView
    lateinit var etMoreAddress: EditText
    lateinit var btnSend: Button


    fun showDialog() {
        dialog.setContentView(R.layout.dialog_getlocationinfo)
        itemList = ArrayList<LocationAddress>()
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val etInputAddress = dialog.findViewById<EditText>(R.id.et_inputAddress)
        val btnAddLocation = dialog.findViewById<Button>(R.id.btn_AddressSearch_Dialog)
        val rv_Dialog_GetLocation = dialog.findViewById<RecyclerView>(R.id.rv_Dialog_GetLocation)
        tvSelectedAddress = dialog.findViewById<TextView>(R.id.tv_Selected_Address)
        etMoreAddress = dialog.findViewById<EditText>(R.id.et_Input_moreAddress)
        btnSend = dialog.findViewById<Button>(R.id.btn_send)

        tvSelectedAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        btnSend.setOnClickListener {
            if (tvSelectedAddress.text.toString().isNotEmpty()) {
                if (etMoreAddress.text.toString().isNotEmpty()) {
                    var value = tvSelectedAddress.text.toString() + etMoreAddress.text.toString()
                    onClickListener.onClicked(value)
                }else{
                    onClickListener.onClicked(tvSelectedAddress.text.toString())
                }
                dialog.dismiss()
            } else {
                Toast.makeText(dialog.context, "주소를 선택해주세요", Toast.LENGTH_SHORT).show()
            }

        }


        adapter = RvGetAddressAdapter(dialog.context, itemList, this)
        rv_Dialog_GetLocation.layoutManager = LinearLayoutManager(dialog.context)
        rv_Dialog_GetLocation.adapter = adapter

        btnAddLocation.setOnClickListener {
            RetrofitManagerAddress.instance.searchAddress(
                etInputAddress.text.toString(),
                completion = { responseState, responseBody ->
                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            var gson = Gson()
                            val location: MapAddressApi? =
                                gson.fromJson(responseBody, MapAddressApi::class.java)
                            Log.d("MainActivity", "API 호춣 성공 : $location")
                            Log.d(
                                "MainActivity",
                                "getDAta!!!!!!!!!!!!!!!!! : ${location.toString()}"
                            )
                            if (location != null) {
                                adapter.changeItem(location.documents)
                                adapter.notifyDataSetChanged()
                            }
                        }
                        RESPONSE_STATE.FAIL -> {
                            Toast.makeText(dialog.context, "API 호출 Error", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("MainActivity", "API 호출 Error : $responseBody")
                        }
                    }
                })
//            dialog.dismiss()
        }
        dialog.show()
    }

    fun setAddressText(value: String) {
        tvSelectedAddress.text = value
    }

    interface ButtonClickListener {
        fun onClicked(text: String)
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }
}