package com.seokee.datecourse.util

import android.content.Context
import android.location.Location
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.seokee.datecourse.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

object AppData {
    // SplashActivity
    const val DURATION: Long = 3000 // 대기시간

    // MainActivity

    // UserInfo
    var appUserInfo: User? = null
    var whatLogin: String? = null
    var auth: FirebaseAuth? = null

    // Location
    var myLocation: Location? = null

    var currentLocation: String? = null
    var currentAddress: String? = null




}
object API {
    const val BASE_URL: String = "https://dapi.kakao.com/v2/local/"

    const val CLIENT_ID: String = "KakaoAK 05c7b5d256ab33e98ff86f09787e57aa"

    // 주소 검색하기
    // https://developers.kakao.com/docs/latest/ko/local/dev-guide#address-coord
    const val SEARCH_ADDRESS: String = "search/address.json"

    // 좌표로 행정구역정보 받기
    // https://developers.kakao.com/docs/latest/ko/local/dev-guide#coord-to-district
    const val SEARCH_COORD2REGIONCODE: String = "geo/coord2regioncode.json"

    // 좌표로 주소 변환하기
    // https://developers.kakao.com/docs/latest/ko/local/dev-guide#coord-to-address
    const val SEARCH_COORD2ADDRESS: String = "geo/coord2address.json"

    // 좌표계 변환하기
    // https://developers.kakao.com/docs/latest/ko/local/dev-guide#trans-coord
    const val SEARCH_TRANSCOORD: String = "geo/transcoord.json"
}
