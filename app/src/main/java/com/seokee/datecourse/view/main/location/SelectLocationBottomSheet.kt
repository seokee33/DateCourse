package com.seokee.datecourse.view.main.location

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seokee.datecourse.databinding.BottomsheetLocationSettingBinding
import com.seokee.datecourse.view.main.map.MenuMapViewModel


class SelectLocationBottomSheet(vm: ViewModel): BottomSheetDialogFragment() {

    lateinit var binding: BottomsheetLocationSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetLocationSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        var layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
        layoutParams.height = getWindowHeight()
        bottomSheet.setLayoutParams(layoutParams);

    }
    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    private fun selectLocationDataChange(viewModel: Any){
        when(viewModel){
            is MenuMapViewModel ->{
//                viewModel.selectedLocation.value = "????"
            }
        }
    }
}