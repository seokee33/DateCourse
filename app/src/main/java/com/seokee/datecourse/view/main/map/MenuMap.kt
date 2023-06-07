package com.seokee.datecourse.view.main.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.seokee.datecourse.R
import com.seokee.datecourse.adapter.LocationListViewPagerAdapter
import com.seokee.datecourse.databinding.FragmentMapBinding
import com.seokee.datecourse.util.AppData
import com.seokee.datecourse.util.MyLocation
import com.seokee.datecourse.util.datastore.CurrentLocationDataStore
import com.seokee.datecourse.view.main.addlocation.AddLocation
import com.seokee.datecourse.view.main.location.SelectLocationBottomSheet
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MenuMap : Fragment() {
    companion object {
        const val TAG: String = "MenuMap"
        fun newInstance(): MenuMap {
            return MenuMap()
        }
    }

    private lateinit var binding: FragmentMapBinding
    private val menuMapViewModel: MenuMapViewModel by viewModels()

    // KakaoMap
    lateinit var mapView: MapView
    lateinit var mapViewContainer: ViewGroup

    // 현위치 가져오기
    private var mFusedLocationProviderClient: FusedLocationProviderClient? =
        null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    private lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는


    //ViewPager =>
    private val viewPagerAdapter = LocationListViewPagerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        Log.d(TAG, "MenuMap")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = menuMapViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

        //저장된 위치 불러오기
        try{
            AppData.currentLocation = CurrentLocationDataStore(requireContext()).getLocation.toString()
            if(AppData.currentLocation!= null){
                var strvalue = AppData.currentLocation!!.split(",")
                AppData.currentAddress = strvalue[0]
            }
        }catch (e:java.lang.Exception){
            Log.i(TAG,e.toString())
        }

        if(AppData.currentAddress != null){
            menuMapViewModel.selectedLocation.value = AppData.currentAddress
        }


        setObserve()

        menuMapViewModel.getLocation()
        //viewPager
        binding.vpLocationList.adapter = viewPagerAdapter

        // Kakao Map
        // MapView 초기화
        initMapView()

        startLocationUpdates()
    }

    private fun setObserve() {
        menuMapViewModel.locationResponse.observe(viewLifecycleOwner) {
            viewPagerAdapter.submitList(menuMapViewModel.locationResponse.value?.items)
        }


    }


    /** MapView 초기화 */
    private fun initMapView() {
        // 해쉬키 얻기 : KakaoMap API HashKey
//         GetHashKey.getHashKey(requireContext())

        mapView = MapView(activity)
        mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        // 줌 인
        mapView.zoomIn(true)

        // 줌 아웃
        mapView.zoomOut(true)

        startLocationUpdates()
    }

    /**현위치 가져오기*/
    private fun startLocationUpdates() {
        Log.i(TAG, "startLocationUpdates")
        // FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = null
        mFusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            mLocationRequest = LocationRequest.create().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
            // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
            mFusedLocationProviderClient!!.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                Looper.myLooper(),
            )
        }
    }

    // 시스템으로 부터 위치 정보를 콜백으로 받음
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            locationResult.lastLocation?.let { onLocationChanged(it) }
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        mLastLocation = location
        val myLocation = LatLng(mLastLocation.latitude, mLastLocation.longitude)
        Log.w(TAG, "onLocationChanged()")
        mapView.setMapCenterPointAndZoomLevel(
            MapPoint.mapPointWithGeoCoord(
                myLocation.latitude,
                myLocation.longitude
            ), 3, true
        )
    }

    // View
    /** 현위치 버튼 클릭시*/
    fun btnMyLocation() {
        MyLocation.getMyLocation(requireContext())
        if (MyLocation.mLastLocation != null) {
            mLastLocation = MyLocation.mLastLocation!!
            mapView.setMapCenterPointAndZoomLevel(
                MapPoint.mapPointWithGeoCoord(mLastLocation.latitude, mLastLocation.longitude),
                3, true
            )
        }
    }

    /**
     * 위치 선택
     *     - BottomSheetDialog*/
    fun btnSelectLocation() {
        val bottomSheet = SelectLocationBottomSheet()
        activity?.supportFragmentManager?.let { bottomSheet.show(it, bottomSheet.tag) }
    }

    fun btnAddLocation() {
        startActivity(Intent(activity, AddLocation::class.java))
    }

}
