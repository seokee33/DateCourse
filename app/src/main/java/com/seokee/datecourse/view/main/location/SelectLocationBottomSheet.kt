package com.seokee.datecourse.view.main.location

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seokee.datecourse.adapter.RvHistoryAddress
import com.seokee.datecourse.dao.HistoryAddressDao
import com.seokee.datecourse.databinding.BottomsheetLocationSettingBinding
import com.seokee.datecourse.model.HistoryAddress
import com.seokee.datecourse.util.AppData
import com.seokee.datecourse.util.datastore.CurrentLocationDataStore
import com.seokee.datecourse.util.roomdb.RoomHelper
import com.seokee.datecourse.view.main.addlocation.GetLocationInfoDialog
import com.seokee.datecourse.view.main.map.MenuMapViewModel
import kotlinx.coroutines.launch
import java.io.IOException

class SelectLocationBottomSheet() : BottomSheetDialogFragment() {

    lateinit var binding: BottomsheetLocationSettingBinding

    //RoomDB
    lateinit var helper:RoomHelper
    lateinit var historyAddressDao: HistoryAddressDao


    //history rv
    private lateinit var historyAddressAdapter: RvHistoryAddress
    private lateinit var historyItems: ArrayList<HistoryAddress>


    val Context.currentLocationDataStore:
            DataStore<Preferences> by preferencesDataStore(name = "currentLocation")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomsheetLocationSettingBinding.inflate(inflater, container, false)

        helper = Room.databaseBuilder(requireContext(),RoomHelper::class.java,"DateCourseApp")
            .allowMainThreadQueries() //잘안씀..공부할때만??
            .build()

        historyAddressDao = helper.historyAddressDao()

        // 검색 기록
        historyAddressAdapter = RvHistoryAddress(requireContext())
        binding.rvHistory.adapter = historyAddressAdapter
        historyUpdate()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragment = this
        binding.lifecycleOwner = this

        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        var layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
        layoutParams.height = getWindowHeight()
        bottomSheet.setLayoutParams(layoutParams)
    }
    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    private fun selectLocationDataChange(viewModel: Any) {
        when (viewModel) {
            is MenuMapViewModel -> {
//                viewModel.selectedLocation.value = "????"
            }
        }
    }

    fun searchAddress(){
        Log.i("SelectLocationBottomSheet","searchAddress()")
        val dialog = GetLocationInfoDialog(requireActivity())
        dialog.showDialog()
        dialog.setOnClickListener(object : GetLocationInfoDialog.ButtonClickListener {
            override fun onClicked(text: String) {
                var addressValue: String = text
                var strvalue = addressValue.split(",")
                historyAddressDao.insert(HistoryAddress(strvalue[0],strvalue[1],strvalue[2]))
                lifecycleScope.launch {
                    CurrentLocationDataStore(requireContext()).saveCurrentLocation(addressValue)
                }
            }
        })
    }

    private fun historyUpdate(){
        historyItems = historyAddressDao.getAll() as ArrayList<HistoryAddress>
        historyAddressAdapter.addressList.clear()
        historyAddressAdapter.addressList = historyItems
        historyAddressAdapter.notifyDataSetChanged()
    }

}
