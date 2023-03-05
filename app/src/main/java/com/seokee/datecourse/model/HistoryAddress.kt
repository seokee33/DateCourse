package com.seokee.datecourse.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HistoryAddress")
class HistoryAddress(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "num") val num: Int?,
    @ColumnInfo(name = "addressName") val addressName: String?,
    @ColumnInfo(name = "addressLatitude") val addressLatitude: String?,
    @ColumnInfo(name = "addressLongitude") val addressLongitude: String?,
){
    constructor(addressName: String?,addressLatitude: String?, addressLongitude: String?) : this(null,addressName,addressLatitude, addressLongitude)
}
