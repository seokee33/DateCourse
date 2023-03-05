package com.seokee.datecourse.util.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class CurrentLocationDataStore(private val context: Context) {

    companion object{
        val Context.currentLocationDataStore:
               DataStore<Preferences> by preferencesDataStore(name = "currentLocation")
        val currentLocation = stringPreferencesKey("currentLocation")

    }

    val getLocation: Flow<String> = context.currentLocationDataStore.data
        .catch {
                e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[currentLocation] ?: ""
        }

    suspend fun saveCurrentLocation(value: String){
        context.currentLocationDataStore.edit {
            it[currentLocation] = value
        }
    }
}