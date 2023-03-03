package com.seokee.datecourse.view.main.addlocation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.seokee.datecourse.R
import com.seokee.datecourse.adapter.AddLocationViewPagerAdapter
import com.seokee.datecourse.databinding.ActivityAddLocationBinding

class AddLocation : AppCompatActivity() {

    private lateinit var binding: ActivityAddLocationBinding
    private lateinit var addLocationViewModel: AddLocationViewModel

    private lateinit var count: AddLocationCount
    private lateinit var write: AddLocationWriteLocation
    private lateinit var finish: AddLocationFinish

    // ViewPager
    private lateinit var viewPagerAdapter: AddLocationViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addLocationViewModel = ViewModelProvider(this).get(AddLocationViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_location)
        binding.activity = this
        binding.viewModel = addLocationViewModel
        binding.lifecycleOwner = this

        viewPagerAdapter = AddLocationViewPagerAdapter(this, addLocationViewModel)

        binding.viewPagerAddLocation.adapter = viewPagerAdapter
        viewPagerAdapter.submitList(addLocationViewModel.item.locationList)

//        setObserve()

//        count = AddLocationCount.newInstance()
//        supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,count).commit()
    }
//
//    private fun setObserve(){
//        addLocationViewModel.nowFragmentState.observe(this){
//            when(it){
//                AddLocationFragmentState.Count->{
//                    count = AddLocationCount.newInstance()
//                    supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,count).commit()
//                }
//                AddLocationFragmentState.Write->{
//                    write = AddLocationWriteLocation.newInstance()
//                    supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,write).commit()
//                }
//                AddLocationFragmentState.Finish->{
//                    finish = AddLocationFinish.newInstance()
//                    supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,finish).commit()
//                }
//            }
//        }
//    }
//
//    private fun changeFragmentAddLocation(what: AddLocationFragmentState){
//        when(what){
//            AddLocationFragmentState.Count->{
//                count = AddLocationCount.newInstance()
//                supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,count).commit()
//            }
//            AddLocationFragmentState.Write->{
//                write = AddLocationWriteLocation.newInstance()
//                supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,write).commit()
//            }
//            AddLocationFragmentState.Finish->{
//                finish = AddLocationFinish.newInstance()
//                supportFragmentManager.beginTransaction().replace(binding.fragAddLocation.id,finish).commit()
//            }
//        }
//
//    }
}
