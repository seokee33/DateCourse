package com.seokee.datecourse.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object MyLocation {
    private val tag = "MyLocation"
    var mLastLocation: Location? = null

    fun getMyLocation(context: Context):Location?{

        val mFusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return mLastLocation
        }else{
            mFusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if(location != null){
                        Log.w(tag, "Location : " + location.latitude + " , " + location.longitude)
                        mLastLocation = location
                        AppData.myLocation = location
                    }

                }
                .addOnFailureListener {
                    Log.w(tag,it.toString())
                }
        }
        return mLastLocation
    }
}
